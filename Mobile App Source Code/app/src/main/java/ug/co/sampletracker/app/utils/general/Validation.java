
package ug.co.sampletracker.app.utils.general;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ug.co.sampletracker.app.utils.constants.ConstNumbers;


public class Validation {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /* Validating phone number */
    public static boolean validatePhoneNumber(String phoneNo){

        if(phoneNo == null || phoneNo.isEmpty()){
            return false;
        }
    
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")){
            return true;
        }
        //validate phone numbers of format +256701081899
        if (phoneNo.matches("\\+\\d{12}")){
            return true;
        }
        //validate phone numbers of format 256701081899
        if (phoneNo.matches("\\d{12}")){
            return true;
        }

        //return false if nothing matches the input
        else {
            return false;
        }

    }

    /* Validating phone number */
    public static boolean validPhoneUsername(String phoneNo){

        if(phoneNo == null || phoneNo.isEmpty()){
            return false;
        }

        return phoneNo.length() >= 3;
        //validate phone numbers of format "0701081899"
      //  return phoneNo.matches("\\d{10}");

    }
    
    /*
    Validating the email address
    */   
    public static boolean validateEmail(String email){

        if(email == null || email.isEmpty())
            return false;

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher; matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validOTP(String otp) {

        if(otp == null)
            return false;

        return otp.length() == 4;

    }

    public static boolean validQty(String quantity) {
        return quantity != null && !quantity.isEmpty() && new Validation().isPositiveNo(quantity);
    }

    public static boolean validInvoiceNo(String documentNo) {
        return documentNo != null && !documentNo.isEmpty() && documentNo.length() <= ConstNumbers.MAX_INVOICE_NO_LENGTH;
    }

    public static boolean validDeliveryNoteNo(String deliveryNoteNo) {
        return deliveryNoteNo != null && !deliveryNoteNo.isEmpty() && deliveryNoteNo.length() <= ConstNumbers.MAX_DELIVERY_NOTE_NO_LENGTH;
    }

    /*
    validating integers and floats
    */
    public boolean isDouble(String value) {

        try{
            double res  = Double.parseDouble(value);
            return true;
        }catch (Exception ex){
            return  false;
        }
      //  return value != null && !value.isEmpty() && (value.matches("^(?=.*\\d)\\d*(?:\\.\\d*)?$"));
    }

    public static boolean validName(String name){
        return name != null &&
                name.length() >= ConstNumbers.MIN_NAME_LENGTH
                && name.length() <= ConstNumbers.MAX_NAME_LENGTH;
    }

    public static boolean validUsername(String username) {
        return username != null &&
                username.length() >= ConstNumbers.MIN_USERNAME_LENGTH
                && username.length() <= ConstNumbers.MAX_NAME_LENGTH;
    }

    public static boolean validPassword(String password) {
        return password != null &&
                password.length() >= ConstNumbers.MIN_PASSWORD_LENGTH;
    }

    public boolean isPositiveNo(String value) {
        return isDouble(value) && (Double.parseDouble(value) > 0);
    }
}