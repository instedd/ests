package ug.co.sampletracker.app.models;

public class StReceivedSample {

    private int id;
    private String sample_id;
    private StDestination destination;

    private String sample_status;
    private String is_destination;
    private String destination_id;
    private String facility_code_id;
    private String received_status;
    private String date_received;
    private String created_at;
    private String entered_by;
    private String delivered_by;

    public String getSample_status() {
        return sample_status;
    }

    public void setSample_status(String sample_status) {
        this.sample_status = sample_status;
    }

    public String getIs_destination() {
        return is_destination;
    }

    public void setIs_destination(String is_destination) {
        this.is_destination = is_destination;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public String getFacility_code_id() {
        return facility_code_id;
    }

    public void setFacility_code_id(String facility_code_id) {
        this.facility_code_id = facility_code_id;
    }

    public String getReceived_status() {
        return received_status;
    }

    public void setReceived_status(String received_status) {
        this.received_status = received_status;
    }

    public String getDate_received() {
        return date_received;
    }

    public void setDate_received(String date_received) {
        this.date_received = date_received;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEntered_by() {
        return entered_by;
    }

    public void setEntered_by(String entered_by) {
        this.entered_by = entered_by;
    }

    public String getDelivered_by() {
        return delivered_by;
    }

    public void setDelivered_by(String delivered_by) {
        this.delivered_by = delivered_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public StDestination getDestination() {
        return destination;
    }

    public void setDestination(StDestination destination) {
        this.destination = destination;
    }
}
