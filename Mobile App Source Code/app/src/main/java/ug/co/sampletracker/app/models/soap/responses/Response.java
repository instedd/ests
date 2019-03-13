package ug.co.sampletracker.app.models.soap.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/4/2018.
 */

public class Response {

    public String statusCode = "";
    public String statusDescription = "";

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
