package ug.co.sampletracker.app.components.contact.offices;

import java.util.List;

import ug.co.sampletracker.app.connections.dataloaders.DTAppData;
import ug.co.sampletracker.app.models.Dealer;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/16/2018.
 */

public interface OfficesInteractor {

    List<String> getDealerLocations();
    List<Dealer> getOffices(String location, DTAppData.ServerResponseAppDataListener responseListener);

}
