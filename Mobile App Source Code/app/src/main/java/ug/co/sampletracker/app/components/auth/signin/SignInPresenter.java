package ug.co.sampletracker.app.components.auth.signin;

import ug.co.sampletracker.app.connections.dataloaders.DLStAppData;
import ug.co.sampletracker.app.connections.dataloaders.DLStLogin;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.LoginStRequest;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 6/29/2018.
 */

public class SignInPresenter implements DLStLogin.ServerResponseLoginListener{
    SignInView view;
    SignInInteractor interactor;
    PreferencesDb preferencesDb;
    DbHandler dbHandler;

    public void setDbHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public SignInPresenter(SignInView view, SignInInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void authenticateUser(LoginStRequest request) {

        view.showDialog("Authenticating");

        DLStLogin dtLogin = new DLStLogin();
        dtLogin.setResponseListener(this);
        dtLogin.loginUser(request);

    }

    @Override
    public void serverResponseLoginError(String error) {
        view.closeDialog();
        view.displayError(error);
    }

    @Override
    public void serverResponseLoginSuccess(LoginStResponse response) {


        if(!response.getStatus().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.closeDialog();
            view.displayError(response.getMessage());
            return;
        }

        downloadAppData(response);

    }

    private void downloadAppData(LoginStResponse response) {

        /*if(response.getAccountType().equalsIgnoreCase(ConstStrings.ACCOUNT_TYPE_DEALER)){

            CustomerVerificationResponse res = response.getCustomer();
            dbHandler.saveHimaRolesAndProducts(res);
            preferencesDb.saveCustomerDetails(res);
            preferencesDb.setCustomerReferenceNumber(response.getDealerNumber());

        } */

        /*We continue to load the application data*/
        DLStAppData dlStAppData = new DLStAppData();
        dlStAppData.setLoginStResponse(response);
        dlStAppData.setResponseListener(serverRespDlAppDataListener);
        dlStAppData.getAppData();

        //preferencesDb.disableIsFirstTimeRun();
        //view.grantAccess(response);

    }

    private DLStAppData.ServerResponseAppDataListener serverRespDlAppDataListener = new DLStAppData.ServerResponseAppDataListener() {
        @Override
        public void serverResponseError(String error) {

            view.closeDialog();
            view.displayError(error);

        }

        @Override
        public void serverResponseAppDataSuccess(AppDataResponse appDataResponse, LoginStResponse loginStResponse) {

            view.closeDialog();
            view.grantAccess(loginStResponse);

        }
    };

    public void validateField(String field, String value, FieldValidationListener validationListener) {

        String err = interactor.validateField(field,value);

        if(err.isEmpty()){

            validationListener.validationResult(false,field,err);
            return;
        }

        validationListener.validationResult(true,field,err);

    }
}
