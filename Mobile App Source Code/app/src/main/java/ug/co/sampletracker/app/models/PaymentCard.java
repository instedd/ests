package ug.co.sampletracker.app.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/26/2018.
 */
public class PaymentCard {

    @SerializedName("cardNo")
    private String cardNo;
    @SerializedName("expirationYear")
    private String expirationYear;
    @SerializedName("expirationMonth")
    private String expirationMonth;
    @SerializedName("cvv")
    private String cvv;
    @SerializedName("mobileNumber")
    private String mobileNumber;

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
