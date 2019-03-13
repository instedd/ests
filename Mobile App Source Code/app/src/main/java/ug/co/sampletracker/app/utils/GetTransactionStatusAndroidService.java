package ug.co.sampletracker.app.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 12/2/2017.
 */

public class GetTransactionStatusAndroidService extends Service {

    /*
    Timer variable {@code timerToRepeatMessageSync} is defined here to enable so that
    * we are able to cancel it in case the service is stopped
    * using method {@code cancelMessageSyncTimer() }
    */
    private Timer timerToRepeatMessageSync;
    public static boolean mRunning = false;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!mRunning) {
            mRunning = true;
        }

        if(intent != null){

            Bundle bundleWithReqData = intent.getExtras();
            timerToRepeatMessageSync = new Timer();

            CheckTransactionStatusThread syncMessageThread = new CheckTransactionStatusThread();

            syncMessageThread.setTimerToRepeatMessageSync(timerToRepeatMessageSync);
            syncMessageThread.setBundleWithReqBeanSyncData(bundleWithReqData);
           // syncMessageThread.setTransactionStatusListener(this);
            syncMessageThread.start();

        }else{
            onDestroy();
        }

        return START_STICKY;

    }

    @Override
    public void onDestroy() {

        mRunning = false;
        cancelMessageSyncTimer();
        super.onDestroy();
    }

    private void cancelMessageSyncTimer() {

        if(timerToRepeatMessageSync != null){
            timerToRepeatMessageSync.cancel();
            Log.e("onDestroy","Stopping timer");
        }

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
