package ug.co.sampletracker.app.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public class Balance extends SugarRecord {

    @SerializedName("creditLimit")
    public String creditLimit = "";
    @SerializedName("creditUsed")
    public String creditUsed = "";
    @SerializedName("availableBalance")
    public String availableBalance = "";
    @Ignore
    public String statusCode = "";
    @Ignore
    public String statusDescription = "";

    public Balance() {
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCreditUsed() {
        return creditUsed;
    }

    public void setCreditUsed(String creditUsed) {
        this.creditUsed = creditUsed;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
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
