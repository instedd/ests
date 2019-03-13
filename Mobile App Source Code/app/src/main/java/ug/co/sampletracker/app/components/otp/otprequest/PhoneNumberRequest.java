package ug.co.sampletracker.app.components.otp.otprequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.dashboard.DashboardPage;
import ug.co.sampletracker.app.components.otp.otpverification.OtpVerification;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.OtpVerificationData;
import ug.co.sampletracker.app.models.requests.RequestPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.Validation;

public class PhoneNumberRequest extends AppCompatActivity implements PhoneNumberRequestView {

    private OtpVerificationData otpVerification = new OtpVerificationData();
    private MaterialDialog dialog;
    private PhoneNumberRequestPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_request);

        initialize();

    }

    private void initialize() {

        setUpToolbar();
        initializeDependencies();
        extractOtpVerificationData();

    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(PhoneNumberRequest.this.getResources().getString(R.string.m_service_name));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        PreferencesDb preferencesDb = new PreferencesDb(PhoneNumberRequest.this);
        DrawerUtil.getDrawer(this,toolbar,preferencesDb);

    }


    private void initializeDependencies() {

        PhoneNumberRequestInteractor interactor = new PhoneNumberRequestInteractorImpl();
        presenter = new PhoneNumberRequestPresenter(this,interactor);
        presenter.setPreferencesDb(new PreferencesDb(PhoneNumberRequest.this));

    }

    /**
     * This object at this instant has only the nextActivity value, after capturing the phoneNumber we add it to
     */
    private void extractOtpVerificationData() {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String data = bundle.getString(ConstStrings.BUNDLE_OTP_VERIFICATION);
            otpVerification = (OtpVerificationData) otpVerification.fromGson(data);
        }

    }

    public void btnCancelClick(View view) {

//        onBackPressed();
        startActivity(new Intent(PhoneNumberRequest.this,DashboardPage.class));
        finish();

    }

    public void btnConfirm(View view) {

        String phone = ((EditText)findViewById(R.id.edtPhone)).getText().toString();

        if(Validation.validPhoneUsername(phone)){

            RequestPaymentHistoryOTPRequest otpRequest = new RequestPaymentHistoryOTPRequest();
            otpRequest.setPhone(phone);
            presenter.requestOTP(otpRequest);

        }else{
            displayError(EnumErrors.INVALID_REG_PHONE_NO.getErr());
        }



    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {

        dialog = Dialogs.mdProgressDialog(PhoneNumberRequest.this, title);
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
        Dialogs.mdConfirm(error,PhoneNumberRequest.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,PhoneNumberRequest.this);
    }

    @Override
    public void openOTPVerificationPage() {

        String phone = ((EditText)findViewById(R.id.edtPhone)).getText().toString();

        otpVerification.setPhoneNumber(phone);
        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_OTP_VERIFICATION,otpVerification.toGson(otpVerification));

        Intent intent = new Intent(PhoneNumberRequest.this,OtpVerification.class);
        intent.putExtras(bundle);
        startActivity(intent);

        finish();

    }
}
