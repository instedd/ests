package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/4/2017.
 */

public class ConstStrings {

    public static final String CONFIRM_TRANSACTION = "Confirm Transaction";

    public static final String STATUS_SUCCESS = "Success";
    public static final String STATUS_BOOKED = "Booked";
    public static final String STATUS_FAILED = "Failed";
    public static final String STATUS_PENDING = "Pending";
    public static final String APP_PREFS = "ug.co.lafarge.mservices.preferences_db";
    public static final String PREF_CUSTOMER_REFERENCE_NO = "pref_customer_reference_no";
    public static final String PREF_DEFAULT_CUST_REF_NO = "#000000000#";

    public static final String PREF_CUSTOMER_REF_NO_OTHER_CUST = "CUSTOMER_REF_NO_OTHER_CUST";
    public static final String PREF_DEFAULT_CUST_REF_NO_OTHER_CUST = "#070*****#";

    public static final String PREF_CUSTOMER_TYPE = "CUSTOMER_TYPE";
    public static final String PREF_DEFAULT_CUST_TYPE = EnumCustomerTypes.OTHER.getType();

    public static final String PREF_ACCOUNT_NAME = "pref_account_name";
    public static final String PREF_DEFAULT_ACCOUNT_NAME = "Hima Client";

    public static final String PREF_CUST_NAME = "customer_name";
    public static final String PREF_CUST_EMAIL = "customer_email";
    public static final String PREF_CUST_PHONE = "customer_phone";

    public static final String PREF_REMEMBER_ME = "pref_remember_me";

    public static final String CLOSE_WEB_VIEW_SIGNAL_STRING = "APPEND=TRUE";
    public static final String CLOSE_WEB_VIEW_SIGNAL_STRING_AFTER_3D_SECURE_CHANGE = "AppEnd=True";

    public static final String PREF_CUST_DETAIL_NAME = "CUST_DETAIL_NAME";
    public static final String PREF_CUST_DETAIL_CUST_NO = "CUST_DETAIL_CUST_NO";
    public static final String PREF_CUST_DETAIL_CREDIT_LIMIT = "CUST_DETAIL_CREDIT_LIMIT";
    public static final String PREF_CUST_DETAIL_CREDIT_USED = "CUST_DETAIL_CREDIT_USED";
    public static final String PREF_CUST_DETAIL_AVAILABLE_BALANCE = "CUST_DETAIL_AVAILABLE_BALANCE";

    public static final String OTP_SMS_START_TEXT = "Your Hima Cement App One Time Password is ";
    public static final String SERVICE_PROVIDER_NAME = "SMSINFO";
    public static final String BUNDLE_PHONE = "phone";
    public static final String BUNDLE_CUST_REFERENCE = "customer_reference";
    public static final String BUNDLE_DEALER = "dealer";
    public static final String BUNDLE_DEALER_DATA = "BUNDLE_DEALER_DATA";
    public static final String BUNDLE_TXN_TYPE = "BUNDLE_TXN_TYPE";
    public static final String CONFIRM_ORDER = "Confirm Order";
    public static final boolean DEBUG_MODE = false;
    public static String BUNDLE_ORDER = "order_Data";
    public static String BUNDLE_PAYMENT = "BUNDLE_PAYMENT";
    public static String BUNDLE_DELIVERY_NOTE = "BUNDLE_DELIVERY_NOTE";
    public static String BUNDLE_INVOICE = "BUNDLE_INVOICE";

    public static String BUNDLE_OTP_VERIFICATION = "OTP_VERIFICATION";

    public static String DATE_FORMAT = "dd-MM-yyyy"; // h\\:mm\\:ss\\.fff yyyy-MM-dd HH:mm:ss.SSS
    public static String DATE_FORMAT_TXN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String DATE_FORMAT_TXN_SHORT = "yyyy-MM-dd";
    public static String DATE_FORMAT_DESC = "MMMM dd, yyyy";

    public static String NOT_AVAILABLE = "Not available";
    public static String PROMPT_OPTION = "Select option";
    public static String PROMPT_FEEDBACK_TYPE = "Select feedback type";

    public static String MSG_TXN_INITIATED = "The transaction has been initiated please wait for confirmation prompt";

    public static String ORDER_SEARCH_FILTER_INVOICE = "INVOICE_NUMBER";
    public static String ORDER_SEACRH_FILTER_DATE = "DATE";


    public static String UNITS_LABEL = "(Units)";
    public static String BUNDLE_TRANS_NO = "bundle_trans_no";
    public static String PREF_IS_FIRST_TIME_RUN = "PREF_IS_FIRST_TIME_RUN";
    public static String ACCOUNT_TYPE_DEALER = "DEALER";
    public static String ACCOUNT_TYPE_INDIVIDUAL = "INDIVIDUAL";

    public static final String PREF_AUTH_DEALER_IS_SET = "AUTH_DEALER_IS_SET";
    public static final String PREF_AUTH_DEALER_REFERENCE = "AUTH_DEALER_REFERENCE";
    public static final String PREF_AUTH_DEALER_NAME = "AUTH_DEALER_NAME";
    public static final String PREF_AUTH_DEALER_PHONE = "AUTH_DEALER_PHONE";
    public static final String PREF_AUTH_DEALER_BALANCE = "AUTH_DEALER_BALANCE";
    public static final String PREF_AUTH_DEALER_CREDIT_LIMIT = "AUTH_DEALER_CREDIT_LIMIT";
    public static final String PREF_AUTH_DEALER_CREDIT_USED = "AUTH_DEALER_CREDIT_USED";

    public static final String PREF_AUTH_USER_IS_SET = "AUTH_USER_IS_SET";
    public static final String PREF_AUTH_USER_PHONE = "AUTH_USER_PHONE";
    public static final String PREF_AUTH_USER_NAME = "AUTH_USER_NAME";
    public static final String PREF_AUTH_USER_EMAIL = "AUTH_USER_EMAIL";
    public static final String PREF_AUTH_USER_PASSWORD = "AUTH_USER_PASSWORD";

    public static final String PREF_LAST_PHONE_NO_INPUT_TIME = "LAST_PHONE_NO_INPUT_TIME";


}
