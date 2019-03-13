package ug.co.sampletracker.app.models.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/25/2018.
 */

public class VerifyPaymentHistoryOTPResponse extends Response {
    public String phone = "";

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
