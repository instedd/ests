package ug.co.sampletracker.app.utils.receivers;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/28/2018.
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import ug.co.sampletracker.app.utils.constants.ConstStrings;

/**
 * A broadcast receiver who listens for incoming SMS
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsBroadcastReceiver";

    private String serviceProviderNumber = "HIMA";
    private String serviceProviderSmsCondition = ConstStrings.OTP_SMS_START_TEXT;

    private Listener listener;

    public SmsBroadcastReceiver() {
    }

    public SmsBroadcastReceiver(String serviceProviderNumber, String serviceProviderSmsCondition) {
        this.serviceProviderNumber = serviceProviderNumber;
        this.serviceProviderSmsCondition = serviceProviderSmsCondition;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "Receiving broadcasts");

        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

            Log.e(TAG, "Receiving broadcasts SMS_RECEIVED_ACTION");
            String smsSender = "";
            String smsBody = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsSender = smsMessage.getDisplayOriginatingAddress();
                    smsBody += smsMessage.getMessageBody();
                }
            } else {
                Bundle smsBundle = intent.getExtras();
                if (smsBundle != null) {
                    Object[] pdus = (Object[]) smsBundle.get("pdus");
                    if (pdus == null) {
                        // Display some error to the user
                        Log.e(TAG, "SmsBundle had no pdus key");
                        return;
                    }
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < messages.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        smsBody += messages[i].getMessageBody();
                    }
                    smsSender = messages[0].getOriginatingAddress();
                    Log.e(TAG, "SmsSender : "+smsSender);
                }
            }

            Log.e(TAG, "Receiving broadcasts smsBody, smsSender "+smsBody+", "+smsSender);
            Log.e(TAG, "Receiving broadcasts smsNo Comp, smsCondt Comp "+serviceProviderNumber+", "+serviceProviderSmsCondition);

            if (smsSender.contains(serviceProviderNumber) && smsBody.contains(serviceProviderSmsCondition)) {
                if (listener != null) {
                    Log.e(TAG, "Receiving broadcasts NOT NULL LISTENER");
                    listener.onTextReceived(smsBody);
                }else{
                    Log.e(TAG, "Receiving broadcasts NULL LISTENER");
                }
            }
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onTextReceived(String text);
    }
}
