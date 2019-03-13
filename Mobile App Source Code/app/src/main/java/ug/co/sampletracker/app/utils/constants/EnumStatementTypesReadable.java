package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public enum EnumStatementTypesReadable {


    GRAND_TOTAL("Grand total"),
    LAST_FIVE("Last 5 transactions"),
    MONTHLY("Monthly statement");
    String type;

    EnumStatementTypesReadable(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
