package ug.co.sampletracker.app.models.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public class LoginResponse extends  Response {

    public String authToken = "";
    public String accountType = "";
    public String dealerNumber = "";
    public String phone = "";
    public String name = "Timothy Kasaga";
    public String email = "tim@timo.net";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerVerificationResponse customer = new CustomerVerificationResponse();

    public CustomerVerificationResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVerificationResponse customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }
}
