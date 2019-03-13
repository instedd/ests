package ug.co.sampletracker.app.models.soap.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/4/2018.
 */

public class CustomerVerificationRes extends Response {

    public String accountName = "";
    public String accountNumber = "";
    public String accountBalance = "";
    public String minimumBalance = "";


    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public String getMinimumBalance() {
        return minimumBalance;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setMinimumBalance(String minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
