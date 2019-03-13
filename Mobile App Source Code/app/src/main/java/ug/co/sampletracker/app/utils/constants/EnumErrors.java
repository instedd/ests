package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumErrors {

    INVALID_REG_PHONE_NO("Enter a valid username, e.g johndoe","E001"),
    INVALID_PASSWORD_FORMAT("Password must be 6 or more characters","E002" ),
    INVALID_NAME_FORMAT("Invalid name, name must be 3 or more characters", "E003"),
    INVALID_EMAIL("Invalid email address", "E004"),
    INVALID_OTP("Invalid one time PIN, enter the 4 digit PIN", "E004"),
    PASSWORD_MISMATCH("Password fields do not match","E005" ),
    FAILED_TO_LOAD_PHONE("Failed to load phone number", "E006"),
    SELECT_ROLE("Please select customer type","E007" ),
    SELECT_PRODT("Please select a product", "E008"),
    INVALID_QUANTITY("Enter a valid quantity, eg 10,20", "E009"),
    INVALID_AMOUNT("Enter a valid amount, e.g 10000", "E010"),
    INVALID_DEALER_NO("Enter a valid dealer number, e.g 000000", "E011"),
    SELECT_DEALER("Please select a dealer", "E008"),
    PERMISSION_DENIED("We cannot proceed without the permission", "E012"),
    SELECT_RECEIPTION_METHOD("Please select a reception method", "E013"),
    INVALID_DATE("Invalid date input","E014" ),
    SELECT_DELIVERY_LOCATION("Please select a delivery location","E015"),
    INVALID_DELIVERY_ADDRESS("Invalid delivery address","E016"),
    INVALID_INVOICE_NO("Enter a valid invoice number", "E017"),
    INVALID_DELIVERY_NOTE_NO("Enter a valid delivery note number", "E017"),
    NO_DELIVERY_NOTE("No delivery notes found", "E018"),
    NO_INVOICES("No invoices found", "E019"),
    FAILED_TO_LOAD_PHONE_LOGIN_AGAIN("Failed to load phone number, please login again and retry", "E020");

    String err;
    String code;

    EnumErrors(String err, String code) {
        this.err = err;
        this.code = code;
    }

    public String getErr() {
        return err;
    }

    public String getCode() {
        return code;
    }
}
