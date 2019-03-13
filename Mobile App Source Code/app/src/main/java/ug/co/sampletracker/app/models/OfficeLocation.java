package ug.co.sampletracker.app.models;

import com.orm.SugarRecord;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/19/2018.
 */

public class OfficeLocation extends SugarRecord {

    private String district;
    private String officeName;
    private String physicalAddress;
    private String phone;
    private String email;
    private double latitude;
    private double longitude;
    private String poBox;

    public static OfficeLocation  headOfficeLocation(){

        OfficeLocation location = new OfficeLocation();

        location.setOfficeName("Hima Cement Limited- Corporate Office");
        location.setPhysicalAddress("Twed Towers, 6th Floor. Plot 10, Kafu Road, Nakasero");
        location.setPoBox("P.O Box 7230, Kampala, Uganda");
        location.setPhone("+256312213200");
        location.setEmail("hima.kampala@lafargeholcim.com");
        location.setDistrict("Kampala");
        location.setLatitude(0.327158);
        location.setLongitude(32.583826);

        return location;

    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
