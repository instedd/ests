package ug.co.sampletracker.app.models;

public class StNotification {

    private String id = "";
    private String message = "";
    private String date_created = "";
    private String expires_on = "";
    private String created_by = "";
    private String accessible_by = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getExpires_on() {
        return expires_on;
    }

    public void setExpires_on(String expires_on) {
        this.expires_on = expires_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getAccessible_by() {
        return accessible_by;
    }

    public void setAccessible_by(String accessible_by) {
        this.accessible_by = accessible_by;
    }
}
