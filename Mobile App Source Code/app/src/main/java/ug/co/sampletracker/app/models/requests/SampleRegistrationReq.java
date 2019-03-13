package ug.co.sampletracker.app.models.requests;

import java.io.File;

import ug.co.sampletracker.app.models.FormData;

public class SampleRegistrationReq {

    public String initialSampleDate, finalDestinationDate;
    public int facility_code;
    public String barcode;
    public int destination;
    public int sample_type;
    public int disease;
    public int transporter;
    public String notes;
    public File uploadFile;
    public FormData formData;

}
