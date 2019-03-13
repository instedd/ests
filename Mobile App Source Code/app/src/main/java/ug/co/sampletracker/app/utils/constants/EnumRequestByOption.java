package ug.co.sampletracker.app.utils.constants;

public enum EnumRequestByOption {

    INVOICE_NUMBER("Invoice Number"),
    DELIVERY_NOTE("Delivery Note Number"),
    DATE("Date");
    String val;

    EnumRequestByOption(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

}
