package ug.co.sampletracker.app.components.otp.otpverification;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.dashboard.DashboardPage;
import ug.co.sampletracker.app.components.registeredsamples.view.ViewRegisteredSamplesSamples;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.OtpVerificationData;
import ug.co.sampletracker.app.models.requests.VerifyPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.EnumModules;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.Validation;

public class OtpVerification extends AppCompatActivity implements OtpVerificationView {

    private OtpVerificationData otpVerification = new OtpVerificationData();
    private EditText edtPIN;
    private TextView txvPinInfo;
    private MaterialDialog dialog;
    private OtpVerificationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        initialize();
    }

    private void initialize() {

        setUpToolbar();
        initializeViews();
        initializeDependencies();
        extractOtpVerificationData();

    }

    private void initializeDependencies() {

        OtpVerificationInteractor interactor = new OtpVerificationInteractorImpl();
        presenter = new OtpVerificationPresenter(this,interactor);
        presenter.setPreferencesDb(new PreferencesDb(OtpVerification.this));

    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(OtpVerification.this.getResources().getString(R.string.m_service_name));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        DrawerUtil.getDrawer(OtpVerification.this,toolbar,new PreferencesDb(OtpVerification.this));

    }

    private void initializeViews() {

        txvPinInfo = (TextView)findViewById(R.id.txvPinInfo);
        edtPIN = (EditText) findViewById(R.id.edtPIN);

    }

    /**
     * This object at this instant has only the nextActivity value, after capturing the phoneNumber we add it to
     */
    private void extractOtpVerificationData() {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String data = bundle.getString(ConstStrings.BUNDLE_OTP_VERIFICATION);
            otpVerification = (OtpVerificationData) otpVerification.fromGson(data);
            showPINSentMessage(otpVerification.getPhoneNumber());
        }

    }

    private void showPINSentMessage(String phoneNumber) {

        String message = "A PIN has been sent to "+phoneNumber+", please enter the PIN below to complete verification";
        txvPinInfo.setText(message);

    }

    public void btnConfirm(View view) {

        String otp = edtPIN.getText().toString();

        if(!validOTP(otp)){
            return;
        }

        VerifyPaymentHistoryOTPRequest otpRequest = new VerifyPaymentHistoryOTPRequest();
        otpRequest.setPhone(otpVerification.getPhoneNumber());
        otpRequest.setOTP(otp);

        presenter.verifyOTP(otpRequest);

    }

    private boolean validOTP(String otp) {

        if(!Validation.validOTP(otp)){
            displayError(EnumErrors.INVALID_OTP.getErr());
            return false;
        }
        return true;
    }

    public void btnCancelClick(View view) {

//        onBackPressed();
        startActivity(new Intent(OtpVerification.this,DashboardPage.class));
        finish();

    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {

        dialog = Dialogs.mdProgressDialog(OtpVerification.this, title);
        dialog.show();

    }

    @Override
    public void closeDialog() {

        if(dialog != null){
            dialog.dismiss();
        }

    }

    @Override
    public void displayError(String error) {
        Dialogs.mdConfirm(error,OtpVerification.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,OtpVerification.this);
    }

    @Override
    public void openNextActivity() {

        //todo save the Customer reference number
        new PreferencesDb(OtpVerification.this).setCustomerReferenceNoOtherCustomer(otpVerification.getPhoneNumber());

        if(otpVerification.getNextActivity() != null){

            String nextActivity = otpVerification.getNextActivity();

            if(nextActivity.equalsIgnoreCase(EnumModules.ORDER_HISTORY_UNREF.getMod())){
            }else{
                startActivity(new Intent(OtpVerification.this, ViewRegisteredSamplesSamples.class));
                finish();
            }


        }

    }

}
