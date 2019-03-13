package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/19/2018.
 */
public enum EnumExtendMenuLabels {

    TONNAGE_REPORT("Tonnage Reports"),
    DELIVERIES("Confirm Notifications"),
    INVOICE_INQUIRY("Request Invoices"),
    INVOICE_DETAILS("Invoice Details"),
    NOTIFICATION("Notifications");
    String item;

    EnumExtendMenuLabels(String item) {
    this.item = item;
    }

    public String getItem() {
        return item;
    }

}
