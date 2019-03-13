package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum  EnumDrawerItemsIdentifiers {

    HOME(1),
    PAYMENTS(2),
    REGISTER_SAMPLES(3),
    BALANCE(4),
    RECEIVE_SAMPLE(5),
    MO_KASH(6),
    VIEW_ACCOUNT(7),
    PAYMENTS_UNREFERENCE(8),
    LOGOUT(9),
    LOGIN(10),
    CONTACT_US(11),
    HELP(13),
    PASSWORD_CHANGE(14),
    DEALERS(15),
    DEALER_REPORTS(16),
    DEALER_REPORTS_TONNAGE(17),
    DEALER_REPORTS_INVOICE(18),
    DEALER_REPORTS_DELIVERY_NOTE(19),
    NOTIFICATION(20);

    int identifier;

    EnumDrawerItemsIdentifiers(int identifier) {
        this.identifier = identifier;
    }

    public int getId() {
        return identifier;
    }

}
