package ug.co.sampletracker.app.models.auth;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/21/2018.
 */
public class AuthUser {

    private String phone;
    private String name;
    private String email;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
