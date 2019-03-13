package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumTransactionTypes {

    ORDER("Order"),
    PAYMENT("Payment");
    String type;

    EnumTransactionTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
