package ug.co.sampletracker.app.utils.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.SpinnerItemData;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public class DataGenerator {

    public static String PACK_BULK = "Tonnage";
    public static String PACK_BAG = "Bag";


    public static HashMap<String, String> getPackagingWithMeasures() {

        HashMap<String,String> packagingWithMeasures = new HashMap<>();

        packagingWithMeasures.put(PACK_BAG,"Bags");
        packagingWithMeasures.put(PACK_BULK,"Tonnes");
        return packagingWithMeasures;

    }

    public static List<String> getStatementTypes() {

        List<String> statementTypes = new ArrayList<>();
        for (EnumStatementTypesReadable s : EnumStatementTypesReadable.values()) {
            statementTypes.add(s.getType());
        }
        return statementTypes;

    }

    public static List<String> getStmtReceptionTypes() {

        List<String> types = new ArrayList<>();
        for (EnumStmtReceiptionTypes s : EnumStmtReceiptionTypes.values()) {
            types.add(s.getType());
        }
        return types;

    }

    public static List<String> getTestStrings() {

        List<String> statementTypes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            statementTypes.add("1." + "News information at "+i);
        }
        return statementTypes;
    }

    public static List<String> dummyDealerLocations() {

        List<String> locations = new ArrayList<>();

        locations.add("Kampala");
        locations.add("Wakiso");
        locations.add("Jinja");
        locations.add("Mbarara");

        return locations;
    }

    public static List<String> paymentMethods(boolean isDealer) {

        List<String> paymentMethods = new ArrayList<>();

        paymentMethods.add(EnumPaymentTypes.MOBILE_MONEY.getType());
        paymentMethods.add(EnumPaymentTypes.CARD.getType());

        if(!isDealer){
            paymentMethods.add(EnumPaymentTypes.CASH.getType());
        }

        return paymentMethods;
    }

    public static ArrayList<SpinnerItemData> getNetworks() {
        ArrayList<SpinnerItemData> list = new ArrayList<>();
        list.add(new SpinnerItemData("MTN", R.drawable.mtn));
        list.add(new SpinnerItemData("AIRTEL",R.drawable.airtel));
        list.add(new SpinnerItemData("AFRICELL",R.drawable.africell));
        return list;
    }

    public static List<String> getReportRequestOptions() {

        List<String> options = new ArrayList<>();
        for (EnumRequestByOption option : EnumRequestByOption.values()) {
            options.add(option.getVal());
        }
        return options;

    }
}


