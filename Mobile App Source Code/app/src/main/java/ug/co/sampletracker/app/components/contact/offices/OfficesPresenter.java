package ug.co.sampletracker.app.components.contact.offices;

import ug.co.sampletracker.app.connections.dataloaders.DTAppData;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/16/2018.
 */

public class OfficesPresenter {

    private OfficesView view;
    private OfficesInteractor interactor;
    private PreferencesDb preferencesDb;
    private DbHandler dbHandler;

    public OfficesPresenter(OfficesView view, OfficesInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public void setDbHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void loadOffices() {

        view.showDialog("Loading Offices");
        interactor.getOffices("", responseAppDataListener);

    }

    private DTAppData.ServerResponseAppDataListener responseAppDataListener = new DTAppData.ServerResponseAppDataListener() {
        @Override
        public void serverResponseError(String error) {

            view.closeDialog();
            view.displayError(error);
        }

        @Override
        public void serverResponseAppDataSuccess(AppDataResponse appDataResponse) {

            view.closeDialog();

            if (!appDataResponse.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)) {
                view.displayError(appDataResponse.getStatusDescription());
                return;
            }

            dbHandler.saveOffices(appDataResponse.getOffices());
            view.displayOffices(appDataResponse.getOffices());

        }
    };

}
