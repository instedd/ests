package ug.co.sampletracker.app.components.contact.offices;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.connections.dataloaders.DTAppData;
import ug.co.sampletracker.app.models.Dealer;
import ug.co.sampletracker.app.models.requests.AppDataRequest;
import ug.co.sampletracker.app.utils.constants.DataGenerator;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/16/2018.
 */

public class OfficesInteractorImpl implements OfficesInteractor {
    @Override
    public List<String> getDealerLocations() {

        //todo fetch dealer locations from Database
        return DataGenerator.dummyDealerLocations();

    }

    @Override
    public List<Dealer> getOffices(String location, DTAppData.ServerResponseAppDataListener responseListener) {

        DTAppData dtAppData = new DTAppData();
        dtAppData.setResponseListener(responseListener);
        dtAppData.getAppData(new AppDataRequest());

        return new ArrayList<>();
    }
}
