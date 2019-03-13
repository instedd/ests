package ug.co.sampletracker.app.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ug.co.sampletracker.app.utils.interfaces.GsonFormatter;

public class DataDeliveryNote implements GsonFormatter {

    @SerializedName("deliveryNoteNo")
    public String deliveryNoteNo = "";
    @SerializedName("date")
    public String date = "";
    @SerializedName("shippedTo")
    public String shippedTo = "";
    @SerializedName("customerNumber")
    public String customerNumber = "";
    @SerializedName("customerName")
    public String customerName = "";
    @SerializedName("trailerNumber")
    public String trailerNumber = "";
    @SerializedName("contactNumber")
    public String contactNumber = "";
    @SerializedName("deliveryPlant")
    public String deliveryPlant = "";
    @SerializedName("materials")
    public String materials = "";
    @SerializedName("shippingPlant")
    public String shippingPlant = "";
    @SerializedName("Incoterm")
    public String Incoterm = "";
    @SerializedName("reasonForTransaction")
    public String reasonForTransaction = "";
    @SerializedName("quantityOrdered")
    public String quantityOrdered = "";
    @SerializedName("quantityDelivered")
    public String quantityDelivered = "";
    @SerializedName("amount")
    public String amount = "";

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDeliveryNoteNo() {
        return deliveryNoteNo;
    }

    public void setDeliveryNoteNo(String deliveryNoteNo) {
        this.deliveryNoteNo = deliveryNoteNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShippedTo() {
        return shippedTo;
    }

    public void setShippedTo(String shippedTo) {
        this.shippedTo = shippedTo;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDeliveryPlant() {
        return deliveryPlant;
    }

    public void setDeliveryPlant(String deliveryPlant) {
        this.deliveryPlant = deliveryPlant;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getShippingPlant() {
        return shippingPlant;
    }

    public void setShippingPlant(String shippingPlant) {
        this.shippingPlant = shippingPlant;
    }

    public String getIncoterm() {
        return Incoterm;
    }

    public void setIncoterm(String incoterm) {
        Incoterm = incoterm;
    }

    public String getReasonForTransaction() {
        return reasonForTransaction;
    }

    public void setReasonForTransaction(String reasonForTransaction) {
        this.reasonForTransaction = reasonForTransaction;
    }

    public String getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(String quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public String getQuantityDelivered() {
        return quantityDelivered;
    }

    public void setQuantityDelivered(String quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    @Override
    public String toGson(Object object) {

        DataDeliveryNote deliveryNote = (DataDeliveryNote)object;
        Gson gson = new Gson();
        Type type = new TypeToken<DataDeliveryNote>() {}.getType();
        return gson.toJson(deliveryNote, type);

    }

    @Override
    public Object fromGson(String data) {

        Gson gson = new Gson();
        Type type = new TypeToken<DataDeliveryNote>() {
        }.getType();
        return  gson.fromJson(data, type);

    }

}
