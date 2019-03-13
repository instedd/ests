package ug.co.sampletracker.app.database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.auth.AuthDealer;
import ug.co.sampletracker.app.models.auth.AuthDealerTemp;
import ug.co.sampletracker.app.models.auth.AuthUser;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.utils.constants.ConstNumbers;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumCustomerTypes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 11/20/2017.
 */

public class PreferencesDb {

    private SharedPreferences mSharedPref;
    private Context mContext;

    public PreferencesDb(Context context) {
        mSharedPref = context.getSharedPreferences(ConstStrings.APP_PREFS, Context.MODE_PRIVATE);
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public String customerReferenceNumber(){

        return  mSharedPref.getString(ConstStrings.PREF_CUSTOMER_REFERENCE_NO,ConstStrings.PREF_DEFAULT_CUST_REF_NO);
    }


    /**
     * @param customerReferenceNo sets the CustomerPhoneNumber as the CustomerReferenceNo for Individual Account Users
     */
    public void setCustomerReferenceNoOtherCustomer(String customerReferenceNo){

        setcustomerType(EnumCustomerTypes.OTHER.getType());

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUSTOMER_REF_NO_OTHER_CUST, customerReferenceNo);
        editor.putLong(ConstStrings.PREF_LAST_PHONE_NO_INPUT_TIME,new Date().getTime());
        editor.apply();

    }

    public String customerReferenceNoOtherCustomer(){

        return  mSharedPref.getString(ConstStrings.PREF_CUSTOMER_REF_NO_OTHER_CUST,
                ConstStrings.PREF_DEFAULT_CUST_REF_NO_OTHER_CUST);
    }

    public void setcustomerType(String customerType){

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUSTOMER_TYPE, customerType);
        editor.apply();

    }

    public String customerType(){

        return  mSharedPref.getString(ConstStrings.PREF_CUSTOMER_TYPE,
                ConstStrings.PREF_DEFAULT_CUST_TYPE);
    }


    public void setCustomerReferenceNumber(String customerReferenceNo){

        setcustomerType(EnumCustomerTypes.DIRECT.getType());

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUSTOMER_REFERENCE_NO, customerReferenceNo);
        editor.apply();

    }


    public void clear(){

        boolean isFirstRun = isFirstTimeRun(); //monitor is first instance app run

        mSharedPref.edit().clear().apply();
        if(!isFirstRun){
            disableIsFirstTimeRun();
        }

    }


    public boolean isSignedIn() {

        return !customerReferenceNumber().equalsIgnoreCase(ConstStrings.PREF_DEFAULT_CUST_REF_NO);

    }

    public void setAccountName(String accountName) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_ACCOUNT_NAME, accountName);
        editor.apply();

    }

    public String accountName(){
        return  mSharedPref.getString(ConstStrings.PREF_ACCOUNT_NAME,ConstStrings.PREF_DEFAULT_ACCOUNT_NAME);
    }

    public void setRememberMe(boolean status) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(ConstStrings.PREF_REMEMBER_ME, status);
        editor.apply();

    }

    public boolean rememberMe(){
        return  mSharedPref.getBoolean(ConstStrings.PREF_REMEMBER_ME,false);
    }

    public void saveCustomerDetails(CustomerVerificationResponse customer) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUST_DETAIL_NAME, customer.getAccountName());
        editor.putString(ConstStrings.PREF_CUST_DETAIL_CUST_NO, customer.getAccountNumber());
        editor.putString(ConstStrings.PREF_CUST_DETAIL_CREDIT_LIMIT, customer.getBalanceResponse().getCreditLimit());
        editor.putString(ConstStrings.PREF_CUST_DETAIL_AVAILABLE_BALANCE, customer.getBalanceResponse().getAvailableBalance());
        editor.putString(ConstStrings.PREF_CUST_DETAIL_CREDIT_USED, customer.getBalanceResponse().getCreditUsed());
        editor.apply();

    }

    public CustomerVerificationResponse customerDetails(){

        CustomerVerificationResponse customer = new CustomerVerificationResponse();
        customer.setAccountName( mSharedPref.getString(ConstStrings.PREF_CUST_DETAIL_NAME,""));
        customer.setAccountNumber( mSharedPref.getString(ConstStrings.PREF_CUST_DETAIL_NAME,""));

        Balance balance = new Balance();
        balance.setCreditLimit(mSharedPref.getString(ConstStrings.PREF_CUST_DETAIL_CREDIT_LIMIT,""));
        balance.setCreditUsed(mSharedPref.getString(ConstStrings.PREF_CUST_DETAIL_CREDIT_USED,""));
        balance.setAvailableBalance(mSharedPref.getString(ConstStrings.PREF_CUST_DETAIL_AVAILABLE_BALANCE,""));

        customer.setBalanceResponse(balance);

        return customer;

    }

    public String customerReferenceBasedOnCustomerType(){

        if(isAuthDealer()){
            return getAuthDealer().getReference();
        }

        if(isAuthUser()){
            return getAuthUser().getPhone();
        }

        return customerReferenceNoOtherCustomer();

    }

    public boolean isFirstTimeRun(){
        return mSharedPref.getBoolean(ConstStrings.PREF_IS_FIRST_TIME_RUN, true);
    }

    public void disableIsFirstTimeRun(){
        mSharedPref.edit().putBoolean(ConstStrings.PREF_IS_FIRST_TIME_RUN,false).apply();
    }

    public void setCustomerEmail(String email) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUST_EMAIL, email);
        editor.apply();

    }

    public String getCustomerEmail(){
        return  mSharedPref.getString(ConstStrings.PREF_CUST_EMAIL,null);
    }


    public void setCustomerPhone(String customerPhone) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUST_PHONE, customerPhone);
        editor.apply();

    }

    public String getCustomerPhone(){
        return  mSharedPref.getString(ConstStrings.PREF_CUST_PHONE,null);
    }


    public void setCustomerName(String customerPhone) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_CUST_NAME, customerPhone);
        editor.apply();

    }

    public String getCustomerName(){
        return  mSharedPref.getString(ConstStrings.PREF_CUST_NAME,null);
    }

    public void removeKeyData(String key) {

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.remove(key);
        editor.apply();

    }

    /**
     * @return true if user is logged into their mobile account else false
     */
    public boolean isAuthUser(){
        return  mSharedPref.getBoolean(ConstStrings.PREF_AUTH_USER_IS_SET,false);
    }

    /**
     * @param isSet set whether a user is logged into their mobile account
     */
    private void setIsAuthUser(boolean isSet){

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(ConstStrings.PREF_AUTH_USER_IS_SET, isSet);
        editor.apply();

    }

    /**
     * @return true if user is logged into their Hima Dealer Account else false
     */
    public boolean isAuthDealer(){

        return rememberMe() ?  mSharedPref.getBoolean(ConstStrings.PREF_AUTH_DEALER_IS_SET,false) : AuthDealerTemp.isLoggedOn;
    }

    /**
     * @param isSet  set whether a user is logged into their  Hima Dealer Account
     */
    public void setIsAuthDealer(boolean isSet){

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(ConstStrings.PREF_AUTH_DEALER_IS_SET, isSet);
        editor.apply();

    }

    public void clearAuthUser(){
        setIsAuthUser(false);
    }

    public void clearAuthDealer(){
        setIsAuthDealer(false);
    }

    public void setAuthUser(AuthUser authUser){

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_AUTH_USER_PHONE, authUser.getPhone());
        editor.putString(ConstStrings.PREF_AUTH_USER_NAME, authUser.getName());
        editor.putString(ConstStrings.PREF_AUTH_USER_EMAIL, authUser.getEmail());
        editor.putString(ConstStrings.PREF_AUTH_USER_PASSWORD, authUser.getPassword());
        editor.apply();

        setIsAuthUser(true);
    }

    public AuthUser getAuthUser(){

        AuthUser authUser = new AuthUser();
        authUser.setPhone(mSharedPref.getString(ConstStrings.PREF_AUTH_USER_PHONE, ""));
        authUser.setName(mSharedPref.getString(ConstStrings.PREF_AUTH_USER_NAME, ""));
        authUser.setEmail(mSharedPref.getString(ConstStrings.PREF_AUTH_USER_EMAIL, ""));
        authUser.setPassword(mSharedPref.getString(ConstStrings.PREF_AUTH_USER_PASSWORD, ""));

        return authUser;

    }

    public AuthDealer getAuthDealer(){

        AuthDealer authDealer = new AuthDealer();

        if(!rememberMe()){

            authDealer.setReference(AuthDealerTemp.reference);
            authDealer.setName(AuthDealerTemp.name);
            authDealer.setCreditLimit(AuthDealerTemp.creditLimit);
            authDealer.setCreditUsed(AuthDealerTemp.creditUsed);
            authDealer.setBalance(AuthDealerTemp.balance);
            authDealer.setPhone(AuthDealerTemp.phone);
            return authDealer;

        }

        authDealer.setReference(mSharedPref.getString(ConstStrings.PREF_AUTH_DEALER_REFERENCE, ""));
        authDealer.setName(mSharedPref.getString(ConstStrings.PREF_AUTH_DEALER_NAME, ""));
        authDealer.setCreditLimit(mSharedPref.getString(ConstStrings.PREF_AUTH_DEALER_CREDIT_LIMIT, ""));
        authDealer.setCreditUsed(mSharedPref.getString(ConstStrings.PREF_AUTH_DEALER_CREDIT_USED, ""));
        authDealer.setBalance(mSharedPref.getString(ConstStrings.PREF_AUTH_DEALER_BALANCE, ""));
        authDealer.setPhone(mSharedPref.getString(ConstStrings.PREF_AUTH_DEALER_PHONE, ""));

        return authDealer;

    }

    public void setAuthDealer(AuthDealer authDealer){

        if(!rememberMe()){

            AuthDealerTemp.isLoggedOn = true;
            AuthDealerTemp.reference = authDealer.getReference();
            AuthDealerTemp.name = authDealer.getName();
            AuthDealerTemp.phone= authDealer.getPhone();
            AuthDealerTemp.balance = authDealer.getBalance();
            AuthDealerTemp.creditLimit = authDealer.getCreditLimit();
            AuthDealerTemp.creditUsed = authDealer.getCreditUsed();

            return;

        }

        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(ConstStrings.PREF_AUTH_DEALER_REFERENCE, authDealer.getReference());
        editor.putString(ConstStrings.PREF_AUTH_DEALER_NAME, authDealer.getName());
        editor.putString(ConstStrings.PREF_AUTH_DEALER_CREDIT_LIMIT, authDealer.getCreditLimit());
        editor.putString(ConstStrings.PREF_AUTH_DEALER_CREDIT_USED, authDealer.getCreditUsed());
        editor.putString(ConstStrings.PREF_AUTH_DEALER_BALANCE, authDealer.getBalance());
        editor.putString(ConstStrings.PREF_AUTH_DEALER_PHONE, authDealer.getPhone());
        editor.apply();

        setIsAuthDealer(true);

    }

    public boolean validStoredPhone(){

        long lastPhoneNoInputTime = mSharedPref.getLong(ConstStrings.PREF_LAST_PHONE_NO_INPUT_TIME, 0);

        if(lastPhoneNoInputTime == 0){
            return false;
        }

        long timeNow = new Date().getTime();
        long diff = (timeNow - lastPhoneNoInputTime)/(60*1000);

        if(diff > ConstNumbers.EXPIRE_TIME_FOR_STORED_PHONE_IN_MINS){

            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.remove(ConstStrings.PREF_LAST_PHONE_NO_INPUT_TIME);
            editor.remove(ConstStrings.PREF_CUSTOMER_REF_NO_OTHER_CUST);
            editor.apply();
            return false;
        }

        return true;

    }

}
