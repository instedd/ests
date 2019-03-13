package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumStmtReceiptionTypes {

    MOBILE_APP("Mobile App"),
    EMAIL("Email");
    String type;

    EnumStmtReceiptionTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
