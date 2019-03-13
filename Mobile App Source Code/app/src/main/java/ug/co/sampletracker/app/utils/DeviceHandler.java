package ug.co.sampletracker.app.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class DeviceHandler {

    public static String userMobileNumber(Context context) {

        try{

            TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                return "";

            }

            return tMgr.getLine1Number();

        }catch (Exception exception){
            return  "";
        }

    }


}
