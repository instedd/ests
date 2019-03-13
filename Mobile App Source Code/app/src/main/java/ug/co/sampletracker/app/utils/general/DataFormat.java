package ug.co.sampletracker.app.utils.general;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DecimalFormat;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/14/2018.
 */

public class DataFormat {


    public static String toLower(String value) {

        return isNullOrEmpty(value) ? value : value.toLowerCase();

    }

    public static String toUpper(String value) {

        return isNullOrEmpty(value) ? value : value.toUpperCase();

    }

    public static String toCapitalize(String value) {

        return isNullOrEmpty(value) ? value : WordUtils.capitalize(value);

    }

    public static String toCapitalizeFully(String value) {

        return isNullOrEmpty(value) ? value : WordUtils.capitalizeFully(value);

    }


    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

   /* public String formatDouble(double value){

        NumberFormat formatter = new DecimalFormat("#0.000");
        return formatter.format(value);

    }*/

    public String formatDouble(double value){

        DecimalFormat formatter = new DecimalFormat("#0.0");
        formatter.applyPattern("###,###.0");
        return formatter.format(value);

    }

    public static String formatToCurrency(String value){

        if(new Validation().isDouble(value)){
            return new DataFormat().formatDouble(Double.parseDouble(value));
        }else{
            return value;
        }
    }

    public static String appendStringWithSpaceBtn(String label, String value){
        return label + " " + value;
    }

}
