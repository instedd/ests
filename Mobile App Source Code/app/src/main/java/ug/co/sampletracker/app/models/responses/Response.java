package ug.co.sampletracker.app.models.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public class Response
{

    @SerializedName("statusCode")
    public String statusCode = "";
    @SerializedName("statusDescription")
    public String statusDescription = "";

    @SerializedName("status")
    public String status = "";
    @SerializedName("message")
    public String message = "";
    @SerializedName("error")
    public String error = "";


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


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
