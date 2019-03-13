package ug.co.sampletracker.app.components.contact.offices;

import java.util.List;

import ug.co.sampletracker.app.models.OfficeLocation;
import ug.co.sampletracker.app.utils.interfaces.CustomView;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/16/2018.
 */

public interface OfficesView extends CustomView{

    void displayOfficeLocations(List<String> dealerLocations);
    void displayOffices(List<OfficeLocation> dealers);

}
