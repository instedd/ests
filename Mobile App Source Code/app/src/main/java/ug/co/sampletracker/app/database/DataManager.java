package ug.co.sampletracker.app.database;

import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.models.StRegisteredSamplePojo;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;

public class DataManager {

    public static AppDataResponse appData = new AppDataResponse();

    public static int findFacilityIdByName(String selectedFacility) {

        for (StFacility item : appData.getHealth_facilities()) {
            if(item.name == selectedFacility){
                return Integer.parseInt(item.id);
            }
        }

        return 0;
    }

    public static int findDestIdByName(String selectedDestination) {

        for (StDestination item : appData.getDestination()) {
            if(item.name == selectedDestination){
                return Integer.parseInt(item.id);
            }
        }

        return 0;
    }

    public static String findDestNameById(String destId) {

        for (StDestination item : appData.getDestination()) {
            if(item.id.equalsIgnoreCase(destId)){
                return item.name;
            }
        }

        return ConstStrings.NOT_AVAILABLE;
    }

    public static int findSepcimenIdByName(String selectedSpecimenType) {

        for (StSpecimen item : appData.getSpecimen()) {
            if(item.name == selectedSpecimenType){
                return Integer.parseInt(item.id);
            }
        }
        return 0;
    }

    public static int findDiseaseIdByName(String selectedDisease) {

        for (StDisease item : appData.getDiseases()) {
            if(item.name == selectedDisease){
                return Integer.parseInt(item.id);
            }
        }
        return 0;
    }

    public static int findTransporterIdByName(String selectedTransporter) {

        for (StTransporter item : appData.getTransporter()) {
            if(item.name == selectedTransporter){
                return Integer.parseInt(item.id);
            }
        }
        return 0;
    }

    public static String findTransporterNameByTransporterId(String id) {

        for (StTransporter item : appData.getTransporter()) {
            if(item.id.equalsIgnoreCase(id)){
                return item.name;
            }
        }
        return ConstStrings.NOT_AVAILABLE;
    }

    public static StRegisteredSamplePojo findSampleToReceiveInRegisteredSamplesBySampleId(String sampleId) {

        //todo Check if it is in registered samples

        for (StRegisteredSamplePojo item : appData.getRegisteredSamples()) {

            if(item.getSample_id().equalsIgnoreCase(sampleId)){
                return item;
            }

        }

        return null;

    }

    public static StReceivedSample findSampleToReceiveInReceivedSamplesBySampleId(String sampleId) {

        //todo Check if it is in received samples

        for (StReceivedSample item : appData.getReceivedSamples()) {

            //ID does not match so we the iteration
            if(! item.getSample_id().equalsIgnoreCase(sampleId)){
                continue;
            }

            //ID matches so we check if it is at the destination or not.
            //If it is not at the destination, then the person can proceed to receive it so we send it to them
            if(item.getIs_destination().equalsIgnoreCase("NO")){
                return  item;
            }

        }

        return null;

    }

    public void saveAppData(AppDataResponse appData){
        DataManager.appData = appData;
    }

    public AppDataResponse getAppData(){
        return DataManager.appData;
    }

}
