package ug.co.sampletracker.app.components.progressbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.utils.CheckTransactionStatusThread;
import ug.co.sampletracker.app.utils.constants.ConstNumbers;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.general.Dialogs;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/4/2017.
 */

public class DialogProgressBar extends DialogFragment
        implements CheckTransactionStatusThread.TransactionStatusListener {

    private static FragmentActivity context;
    private static String transactionId;
    private static Timer timerToRepeatMessageSync;
    private View rootView;
    private Activity mActivity;
    private Button btnDoneWaitingForPrompt;
    private Dialog dialog;
    private DialogProgressBar dialogProgressBar;
    private TextView txvTimeLeftMessage;
    private CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.dialog_progress_bar, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* Make the fragment layout appear below the Status Bar */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            /*if (getView() != null ) {
                getView().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                getView().setPadding(0, (int)convertDpToPixel(25f, mActivity), 0, 0);
                getView().requestLayout();
            }*/

        }

        initialise(rootView);

    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    public void setToolbar() {

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.module_payment);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

        setHasOptionsMenu(true);

    }

    private void initialise(View view) {

        setToolbar();
        initialiseViews(view);
        initiateCountDownTimerForWaitingForPrompt();

    }

    private void initiateCountDownTimerForWaitingForPrompt() {

        countDownTimer = new CountDownTimer(ConstNumbers.TIME_TO_WAIT_FOR_PAYMENT_PROMPT, 1000) {

            public void onTick(long millisUntilFinished) {
                String messageTimeLeft = (millisUntilFinished / 1000) + " seconds left ...";
                txvTimeLeftMessage.setText(messageTimeLeft);
            }

            public void onFinish() {

                try{

                    if(mActivity != null){

                        txvTimeLeftMessage.setText("Done");
                        stopQueryingForStatusAndroidService();

                        dismiss();
                        /*
                        String title = mActivity.getResources().getString(R.string.app_name);
                        String message = "Payment has been initiated, if confirmation prompt does not come in 3 minutes please contact our support team";
                        Dialogs.mdPromptSingleBtn(mActivity, title, message, (dialog, which) -> dismiss()); */

                    }

                }catch (Exception ex){

                    //todo log exception

                }

            }
        }.start();

    }


    private void initialiseViews(View view) {

        btnDoneWaitingForPrompt = (Button)view.findViewById(R.id.btnDoneWaitingForPrompt);
        txvTimeLeftMessage = (TextView)view.findViewById(R.id.txvTimeLeftMessage);
        btnDoneWaitingForPrompt.setOnClickListener(btn->{
            stopQueryingForStatusAndroidService();
            this.dismiss();
        });

    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showDialog(FragmentActivity context, String transactionId) {

        DialogProgressBar.context = context;
        DialogProgressBar.transactionId = transactionId;

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        dialogProgressBar = new DialogProgressBar();

        boolean mIsLargeLayout = false;
        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            dialogProgressBar.show(fragmentManager, "dialog");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, dialogProgressBar)
                    .addToBackStack(null).commit();
        }

        startSyncUsingAndroidService(transactionId);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_dialog_create, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            cancelCountDownTimer();
            stopQueryingForStatusAndroidService();
            dismiss();
            return true;
        } else if (id == R.id.action_close) {
            cancelCountDownTimer();
            stopQueryingForStatusAndroidService();
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cancelCountDownTimer() {
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity)context;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        timerToRepeatMessageSync.cancel();
        cancelCountDownTimer();
        super.onDismiss(dialog);
    }

    @Override
    public void stopWaitingForTransactionPrompt(DialogProgressBar dialogProgressBar, String message) {

        cancelCountDownTimer();

        if(dialogProgressBar != null){
            dialogProgressBar.showToast(message);
            dialogProgressBar.dismiss();
        }

    }

    private void showToast(String message) {
        Dialogs.toast(mActivity,message);
    }

    public void startSyncUsingAndroidService(String transactionId) {

        Log.e("Transaction Id", transactionId);

        Bundle bundleWithData = new Bundle();
        bundleWithData.putString(ConstStrings.BUNDLE_TRANS_NO, transactionId);

        CheckTransactionStatusThread syncMessageThread = new CheckTransactionStatusThread();

        timerToRepeatMessageSync = new Timer();

        syncMessageThread.setTimerToRepeatMessageSync(timerToRepeatMessageSync);
        syncMessageThread.setBundleWithReqBeanSyncData(bundleWithData);
        syncMessageThread.setTransactionStatusListener(this);
        syncMessageThread.setDialogProgressBar(dialogProgressBar);
        syncMessageThread.start();

    }

    public static void stopQueryingForStatusAndroidService(){

        if(timerToRepeatMessageSync != null){
            timerToRepeatMessageSync.cancel();

        }

    }

}
