package ug.co.sampletracker.app.models.requests;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ug.co.sampletracker.app.models.PaymentCard;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class PaymentRequest {

    @SerializedName("customerReferenceNo")
    public String customerReferenceNo = "";
    @SerializedName("amount")
    public String amount = "";
    @SerializedName("phone")
    public String phone = "";
    @SerializedName("paymentMode")
    public String paymentMode = "";
    @SerializedName("card")
    public PaymentCard card;

    public PaymentCard getCard() {
        return card;
    }
    public void setCard(PaymentCard card) {
        this.card = card;
    }
    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public String getCustomerReferenceNo() {
        return customerReferenceNo;
    }

    public void setCustomerReferenceNo(String customerReferenceNo) {
        this.customerReferenceNo = customerReferenceNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static String toGson(PaymentRequest payment){

        Gson gson = new Gson();
        Type type = new TypeToken<PaymentRequest>() {}.getType();
        return gson.toJson(payment, type);

    }

    public static PaymentRequest fromGson(String data){

        PaymentRequest payment = new PaymentRequest();
        Gson gson = new Gson();

        Type type = new TypeToken<PaymentRequest>() {
        }.getType();
        payment = gson.fromJson(data, type);

        return  payment;

    }

}
