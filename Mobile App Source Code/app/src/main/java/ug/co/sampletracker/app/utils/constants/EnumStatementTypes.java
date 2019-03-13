package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumStatementTypes {

    GRAND_TOTAL("GRAND_TOTAL"),
    LAST_FIVE("LAST_FIVE_TRANSACTIONS"),
    MONTHLY("MONTHLY");
    String type;

    EnumStatementTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
