package ug.co.sampletracker.app.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 8/26/2018.
 */
public class OtpVerificationData {

    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("nextActivity")
    private String nextActivity;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(String nextActivity) {
        this.nextActivity = nextActivity;
    }

    public String toGson(OtpVerificationData otpVerificationData) {

        Gson gson = new Gson();
        Type type = new TypeToken<OtpVerificationData>() {}.getType();
        return gson.toJson(otpVerificationData, type);

    }

    public Object fromGson(String data) {

        Gson gson = new Gson();
        Type type = new TypeToken<OtpVerificationData>() {
        }.getType();
        return  gson.fromJson(data, type);

    }

}
