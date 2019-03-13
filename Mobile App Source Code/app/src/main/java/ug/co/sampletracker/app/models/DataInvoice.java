package ug.co.sampletracker.app.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ug.co.sampletracker.app.utils.interfaces.GsonFormatter;

public class DataInvoice implements GsonFormatter {

    @SerializedName("invoiceNumber")
    public String invoiceNumber = "";
    @SerializedName("invoiceDate")
    public String invoiceDate = "";
    @SerializedName("invoiceAddress")
    public String invoiceAddress = "";
    @SerializedName("deliveryNo")
    public String deliveryNo = "";
    @SerializedName("deliveryPlant")
    public String deliveryPlant = "";
    @SerializedName("product")
    public String product = "";
    @SerializedName("quantity")
    public String quantity = "";
    @SerializedName("unitCost")
    public String unitCost = "";
    @SerializedName("totalAmount")
    public String totalAmount = "";
    @SerializedName("customerName")
    public String customerName = "";
    @SerializedName("customerNumber")
    public String customerNumber = "";
    @SerializedName("incoterm")
    public String incoterm = "";

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getDeliveryPlant() {
        return deliveryPlant;
    }

    public void setDeliveryPlant(String deliveryPlant) {
        this.deliveryPlant = deliveryPlant;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    @Override
    public String toGson(Object object) {

        DataInvoice invoice = (DataInvoice)object;
        Gson gson = new Gson();
        Type type = new TypeToken<DataInvoice>() {}.getType();
        return gson.toJson(invoice, type);

    }

    @Override
    public Object fromGson(String data) {

        Gson gson = new Gson();
        Type type = new TypeToken<DataInvoice>() {
        }.getType();
        return  gson.fromJson(data, type);

    }
}
