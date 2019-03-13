package ug.co.sampletracker.app.utils;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ug.co.sampletracker.app.components.progressbar.DialogProgressBar;
import ug.co.sampletracker.app.connections.dataloaders.DTGetTransactionStatus;
import ug.co.sampletracker.app.models.requests.GetTransactionStatusRequest;
import ug.co.sampletracker.app.models.responses.GetTransactionStatusResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 12/2/2017.
 */

public class CheckTransactionStatusThread extends Thread {

    private Date startTime = new Date();

    private DialogProgressBar dialogProgressBar;
    private Timer timerToRepeatMessageSync;
    private Bundle bundleWithReqBeanSyncData;
    private TransactionStatusListener transactionStatusListener;

    public void setTransactionStatusListener(TransactionStatusListener transactionStatusListener) {
        this.transactionStatusListener = transactionStatusListener;
    }

    public void setDialogProgressBar(DialogProgressBar dialogProgressBar) {
        this.dialogProgressBar = dialogProgressBar;
    }

    public void setTimerToRepeatMessageSync(Timer timerToRepeatMessageSync) {
        this.timerToRepeatMessageSync = timerToRepeatMessageSync;
    }

    public void setBundleWithReqBeanSyncData(Bundle bundleWithReqBeanSyncData) {
        this.bundleWithReqBeanSyncData = bundleWithReqBeanSyncData;
    }

    @Override
    public void run() {

        super.run();
        long DELAY_FOR_CHECKING_DB_FOR_MESSAGES_MILLIS = 3000;
        timerToRepeatMessageSync.scheduleAtFixedRate(new CheckTransactionStatusTimerTask(),
                0, DELAY_FOR_CHECKING_DB_FOR_MESSAGES_MILLIS);

    }

    class CheckTransactionStatusTimerTask extends TimerTask implements DTGetTransactionStatus.ServerResponseGetTransStatusListener {

        @Override
        public void run() {

            if (bundleWithReqBeanSyncData != null) {

                String transactionNo = bundleWithReqBeanSyncData.getString(ConstStrings.BUNDLE_TRANS_NO);
                GetTransactionStatusRequest request = new GetTransactionStatusRequest();
                request.setTransactionId(transactionNo);

                DTGetTransactionStatus dataLoader = new DTGetTransactionStatus();
                dataLoader.setResponseListener(this);
                dataLoader.getTransactionStatus(request);

            } else {
                Log.e(CheckTransactionStatusTimerTask.class.getName(), "#CheckTransactionStatusTimerTask Null Bundle");
            }

        }

        @Override
        public void serverResponseTransStatusError(String error) {

            stopQueryingForTransactionStatus("Failed to process payment with error, "+error);

        }

        @Override
        public void serverResponseTransStatusSuccess(GetTransactionStatusResponse response) {

            if(isWaitPeriodExceeded(startTime)){

                stopQueryingForTransactionStatus("Operation took so long, if payment prompt does not appear 5 minutes, please contact support");
                return;

            }

            String message = "";
            if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS) ){

                message = "Failed to process payment with error, "+response.getStatusDescription();
                stopQueryingForTransactionStatus(message);
                return;

            }

            String transactionStatus = response.getTransactionStatus();
            if(transactionStatus.equalsIgnoreCase(ConstStrings.STATUS_SUCCESS) ||
                    transactionStatus.equalsIgnoreCase(ConstStrings.STATUS_FAILED)){

                message = transactionStatus.equalsIgnoreCase(ConstStrings.STATUS_SUCCESS) ?
                        "Payment successful. Thank you for using our services" :
                        "Failed to process payment";

                stopQueryingForTransactionStatus(message);

            }

        }

    }

    private void stopQueryingForTransactionStatus(String message) {

        timerToRepeatMessageSync.cancel();
        transactionStatusListener.stopWaitingForTransactionPrompt(dialogProgressBar,message);

    }

    public  interface  TransactionStatusListener{
        void stopWaitingForTransactionPrompt(DialogProgressBar dialogProgressBar, String message);
    }

    public boolean isWaitPeriodExceeded(Date previousDate){

        long diff = new Date().getTime() - previousDate.getTime();
        return  diff > 2L * 60 * 1000;

    }

}
