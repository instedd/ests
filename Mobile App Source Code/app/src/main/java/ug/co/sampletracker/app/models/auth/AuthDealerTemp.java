package ug.co.sampletracker.app.models.auth;

public class AuthDealerTemp {

    public static  boolean isLoggedOn  =  false;
    public static String reference = "";
    public static String name = "";
    public static String balance = "";
    public static String creditUsed = "";
    public static String creditLimit = "";
    public static String phone = "";

    public static void clear(){

       isLoggedOn = false;
       reference = "";
       name = "";
       balance = "";
       creditUsed = "";
       creditLimit = "";
       phone = "";

    }

}
