package ug.co.sampletracker.app.models.requests;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ug.co.sampletracker.app.models.PaymentCard;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class OrderUnrefRequest {

    @SerializedName("expectedDate")
    public String expectedDate = "";
    @SerializedName("deliveryAddress")
    public String deliveryAddress = "";
    @SerializedName("deliverToPremises")
    public String deliverToPremises = "";
    @SerializedName("regionCode")
    public String regionCode = "";
    @SerializedName("regionName")
    public String regionName = "";
    @SerializedName("phone")
    public String phone = "";
    @SerializedName("amount")
    public String amount = "";
    @SerializedName("role")
    public String role = "";
    @SerializedName("distributorNo")
    public String distributorNo = "";
    @SerializedName("noOfBags")
    public String noOfBags = "";
    @SerializedName("itemName")
    public String itemName = "";
    @SerializedName("packaging")
    public String packaging = "";
    @SerializedName("quantity")
    public String quantity = "";
    @SerializedName("unitsOfMeasure")
    public String unitsOfMeasure = "";
    @SerializedName("dealerName")
    public String dealerName = "";
    @SerializedName("paymentMode")
    public String paymentMode = "";
    @SerializedName("card")
    public PaymentCard card;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliverToPremises() {
        return deliverToPremises;
    }

    public void setDeliverToPremises(String deliverToPremises) {
        this.deliverToPremises = deliverToPremises;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

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

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDistributorNo() {
        return distributorNo;
    }

    public void setDistributorNo(String distributorNo) {
        this.distributorNo = distributorNo;
    }

    public String getNoOfBags() {
        return noOfBags;
    }

    public void setNoOfBags(String noOfBags) {
        this.noOfBags = noOfBags;
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

    public String getUnitsOfMeasure() {
        return unitsOfMeasure;
    }

    public void setUnitsOfMeasure(String unitsOfMeasure) {
        this.unitsOfMeasure = unitsOfMeasure;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public enum Fields{

        PHONE("PHONE"),
        AMOUNT("AMOUNT"),
        ROLE("ROLE"),
        PACKAGING("PACKAGING"),
        DISTRIBUTOR_NO("DISTRIBUTOR_NO"),
        ITEM_NAME("ITEM_NAME"),
        QUANTITY("QUANTITY"),
        EXPECTED_DATE("EXPECTED_DATE"),
        DELIVERY_ADDRESS("DELIVERY_ADDRESS"),
        UNIT_OF_MEASURE("UNIT_OF_MEASURE");

        private String name;

        Fields(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public static String toGson(OrderUnrefRequest orderData){

        Gson gson = new Gson();
        Type type = new TypeToken<OrderUnrefRequest>() {}.getType();
        return gson.toJson(orderData, type);

    }

    public static OrderUnrefRequest fromGson(String data){

        OrderUnrefRequest orderData = new OrderUnrefRequest();
        Gson gson = new Gson();

        Type type = new TypeToken<OrderUnrefRequest>() {
        }.getType();
        orderData = gson.fromJson(data, type);

        return  orderData;

    }

}
