package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public class RegistrationRequest {

    private String name = "";
    private String email = "";
    private String telephoneNo = "";
    private String password = "";
    private String accountType = "";

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

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

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Fields{

        NAME("NAME"),
        EMAIL("EMAIL"),
        TELEPHONE_NO("TELEPHONE_NO"),
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
