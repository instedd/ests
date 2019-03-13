package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumPaymentTypes {

    MOBILE_MONEY("Mobile Money Payment"),
    CARD("Bank Card Payment"),
    CASH("Cash Payment");
    String type;

    EnumPaymentTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
