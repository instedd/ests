package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumDeliveryTypes {

    SELF_COLLECT("SELF_COLLECT"),
    DELIVERED("DELIVERED");
    String type;

    EnumDeliveryTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
