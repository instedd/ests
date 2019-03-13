package ug.co.sampletracker.app.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/16/2018.
 */

public class Dealer extends SugarRecord {

    @SerializedName("dealerName")
    private String dealerName;
    @SerializedName("location")
    private String location;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("email")
    private String email;
    @SerializedName("dealerNumber")
    private String dealerNumber;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("latitude")
    private double latitude;

    public Dealer() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public static String toGson(Dealer dealer){

        Gson gson = new Gson();
        Type type = new TypeToken<Dealer>() {}.getType();
        return gson.toJson(dealer, type);

    }

    public static Dealer fromGson(String data){

        Dealer dealer = new Dealer();
        Gson gson = new Gson();

        Type type = new TypeToken<Dealer>() {
        }.getType();
        dealer = gson.fromJson(data, type);

        return  dealer;

    }


}
