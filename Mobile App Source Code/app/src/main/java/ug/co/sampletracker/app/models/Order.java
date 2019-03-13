package ug.co.sampletracker.app.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.lang.reflect.Type;

import ug.co.sampletracker.app.utils.general.Validation;
import ug.co.sampletracker.app.utils.interfaces.GsonFormatter;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class Order extends SugarRecord implements GsonFormatter{

    @SerializedName("customerId")
    public String customerId;
    @SerializedName("amount")
    public String amount;
    @SerializedName("status")
    public String status;
    @SerializedName("creationDate")
    public String creationDate;
    @SerializedName("itemName")
    public String itemName;
    @SerializedName("packaging")
    public String packaging;
    @SerializedName("quantity")
    public String quantity;
    @SerializedName("deliverToPremises")
    public String deliverToPremises;
    @SerializedName("invoiceNumber")
    public String invoiceNumber;

    @Override
    public String toGson(Object object) {

        Order order = (Order)object;
        Gson gson = new Gson();
        Type type = new TypeToken<Order>() {}.getType();
        return gson.toJson(order, type);

    }

    @Override
    public Object fromGson(String data) {

        Gson gson = new Gson();
        Type type = new TypeToken<Order>() {
        }.getType();
        return  gson.fromJson(data, type);

    }


    public Order() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {

        if(status.toLowerCase().contains("success")){
            return "SUCCESS";
        }

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDeliverToPremises() {
        return deliverToPremises;
    }

    public void setDeliverToPremises(String deliverToPremises) {
        this.deliverToPremises = deliverToPremises;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getUnitCost() {

        if(new Validation().isDouble(quantity) && new Validation().isDouble(amount)){

            double dQty = Double.parseDouble(quantity);
            double dAmount = Double.parseDouble(amount);

            if(dQty == 0){
                return  "";
            }

            return String.valueOf(dAmount/dQty);

        }

        return "";

    }
}
