package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public class OrderRequest {

    public String customerReferenceNo = "";
    public double totalAmount = 0;
    public String itemName = "";
    public String packaging = "Bags";
    public String unitOfMeasure = "";
    public String quantity = "";
    public String deliverToPremises = "";
    public String expectedDate;
    public String deliveryLocation = "";

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getCustomerReferenceNo() {
        return customerReferenceNo;
    }

    public void setCustomerReferenceNo(String customerReferenceNo) {
        this.customerReferenceNo = customerReferenceNo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public enum Fields{

        CUST_REF_NO("CUST_REF_NO"),
        TOTAL_AMOUNT("TOTAL_AMOUNT"),
        ITEM_NAME("ITEM_NAME"),
        PACKAGING("PACKAGING"),
        MEASURE("MEASURE"),
        QUANTITY("QUANTITY"),
        DELIVERY_DECISION("DELIVERY_DECISION"),
        EXPECTED_DATE("EXPECTED_DATE"),
        DELIVERY_LOCATION("DELIVERY_LOCATION");

        private String name;

        Fields(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

}
