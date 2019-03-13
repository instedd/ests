package ug.co.sampletracker.app.models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class TableRegionalPrices extends SugarRecord {

    private String regionCode = "";
    private String regionName = "";
    private String productName = "";
    private String productPrice = "";
    private String deliveryType = "";
    private String customerType = "";

    public TableRegionalPrices() {
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public static List<TableRegionalPrices> getRegionalPricesData(List<RegionalPrices> regionalPrices){

        List<TableRegionalPrices> tableRegionalPrices = new ArrayList<>();

        /* todo load the regional prices data */

        for (RegionalPrices regionalPrices1 : regionalPrices) {

            tableRegionalPrices.addAll(retrieveRegionsProducts(regionalPrices1));

        }

        return tableRegionalPrices;
    }

    private static List<TableRegionalPrices> retrieveRegionsProducts(RegionalPrices regionalPrices) {

        List<TableRegionalPrices> tableRegionalPrices = new ArrayList<>();
        String regionName = regionalPrices.getRegionName();
        String regionCode = regionalPrices.getRegionCode();

        for (RegionProdt regionProdt : regionalPrices.getRegionProdts()) {

            TableRegionalPrices dataRegionalPrices = new TableRegionalPrices();
            dataRegionalPrices.regionCode =regionCode;
            dataRegionalPrices.regionName =regionName;
            dataRegionalPrices.productName = regionProdt.name;
            dataRegionalPrices.productPrice = regionProdt.price;
            dataRegionalPrices.customerType = regionProdt.customerType;
            dataRegionalPrices.deliveryType = regionProdt.deliveryType;

            tableRegionalPrices.add(dataRegionalPrices);

        }

        return tableRegionalPrices;

    }

}
