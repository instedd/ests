package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public class LoginStRequest {

    private String txt_username = "";
    private String txt_password = "";


    public String getTxt_password() {
        return txt_password;
    }

    public void setTxt_password(String txt_password) {
        this.txt_password = txt_password;
    }

    public String getTxt_username() {
        return txt_username;
    }

    public void setTxt_username(String txt_username) {
        this.txt_username = txt_username;
    }

    public enum Fields{

        NAME("USERNAME"),
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
