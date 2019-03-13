package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumCustomerTypes {

    DIRECT("Direct Customer"),
    DEALER("DEALER"),
    INDIVIDUAL("INDIVIDUAL"),
    OTHER("Other Customer");
    String type;

    EnumCustomerTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
