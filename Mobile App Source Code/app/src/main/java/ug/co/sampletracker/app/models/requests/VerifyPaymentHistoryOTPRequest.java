package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/24/2018.
 */

public class VerifyPaymentHistoryOTPRequest {

    public String phone = "";
    public String OTP = "";

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}
