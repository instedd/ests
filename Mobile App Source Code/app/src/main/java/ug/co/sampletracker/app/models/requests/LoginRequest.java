package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public class LoginRequest {

    private String phone = "";
    private String email = "";
    private String password = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public enum Fields{

        NAME("NAME"),
        PHONE("PHONE"),
        PASSWORD("PASSWORD");

        String field;

        Fields(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }
    }

}
