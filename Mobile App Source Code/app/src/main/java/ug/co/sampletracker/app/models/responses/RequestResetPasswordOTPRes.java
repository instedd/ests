package ug.co.sampletracker.app.models.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/20/2018.
 */
public class RequestResetPasswordOTPRes extends Response{

    private String phone = "";

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
