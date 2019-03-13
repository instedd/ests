package ug.co.sampletracker.app.models.requests;

public class SampleReceiptionReq {

    private int final_destination;
    private String sample_status;
    private  int transporter;
    private String barcode;
    private int facility_code;
    private String is_destination;
    private  String dateReceived;


    public int getFinal_destination() {
        return final_destination;
    }

    public void setFinal_destination(int final_destination) {
        this.final_destination = final_destination;
    }

    public String getSample_status() {
        return sample_status;
    }

    public void setSample_status(String sample_status) {
        this.sample_status = sample_status;
    }

    public int getTransporter() {
        return transporter;
    }

    public void setTransporter(int transporter) {
        this.transporter = transporter;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getFacility_code() {
        return facility_code;
    }

    public void setFacility_code(int facility_code) {
        this.facility_code = facility_code;
    }

    public String getIs_destination() {
        return is_destination;
    }

    public void setIs_destination(String is_destination) {
        this.is_destination = is_destination;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }
}
