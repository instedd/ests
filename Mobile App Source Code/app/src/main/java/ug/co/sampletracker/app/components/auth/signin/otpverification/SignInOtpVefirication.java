package ug.co.sampletracker.app.components.auth.signin.otpverification;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.dashboard.DashboardPage;
import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.components.auth.signin.SignInInteractor;
import ug.co.sampletracker.app.components.auth.signin.SignInInteractorImpl;
import ug.co.sampletracker.app.components.auth.signin.SignInPresenter;
import ug.co.sampletracker.app.components.auth.signin.SignInView;
import ug.co.sampletracker.app.components.auth.signup.Signup;
import ug.co.sampletracker.app.connections.dataloaders.DTVerifyOTP;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.auth.AuthDealer;
import ug.co.sampletracker.app.models.requests.VerifyPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.models.responses.LoginResponse;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.models.responses.VerifyPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.Dialogs;

public class SignInOtpVefirication extends AppCompatActivity implements SignInView, DTVerifyOTP.ServerResponseRequestOTPListener {

    private EditText edtOTP;
    private EditText edtPassword;
    private SignInPresenter presenter;
    private MaterialDialog dialog;
    private PreferencesDb preferencesDb;
    private String phone = "";
    private String customerReference = "";
    private CustomerVerificationResponse customerVerificationResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_otp_verification);

        retrieveDataFromBundle();
        initialize();

    }

    private void retrieveDataFromBundle() {

        if(getIntent().getExtras() != null){

            phone = getIntent().getStringExtra(ConstStrings.BUNDLE_PHONE);
            customerReference = getIntent().getStringExtra(ConstStrings.BUNDLE_CUST_REFERENCE);

            String data = getIntent().getStringExtra(ConstStrings.BUNDLE_DEALER_DATA);
            customerVerificationResponse = CustomerVerificationResponse.fromGson(data);

            Log.e(SignInOtpVefirication.class.getName(),data);

        }
    }

    private void initialize() {

        setUpToolbar();
        initializeViews();
        initiatilizeDependencies();

    }

    private void initiatilizeDependencies() {

        DbHandler dbHandler = new DbHandler();
        preferencesDb = new PreferencesDb(SignInOtpVefirication.this);

        SignInInteractor interactor = new SignInInteractorImpl();
        presenter = new SignInPresenter(this,interactor);

        presenter.setPreferencesDb(preferencesDb);
        presenter.setDbHandler(dbHandler);

    }

    private void initializeViews() {

        edtOTP = (EditText)findViewById(R.id.edtOTP);

    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(SignInOtpVefirication.this.getResources().getString(R.string.m_service_name));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

    }

    public void btnConfirmOtp(View view) {

        VerifyPaymentHistoryOTPRequest request = getOTPVerificationRequest();
        if(validRequest(request)){

            startProgressDialog("Verifying PIN");

            DTVerifyOTP dtVerifyOTP = new DTVerifyOTP();
            dtVerifyOTP.setResponseListener(this);
            dtVerifyOTP.verifyOTP(request);

        }

    }

    private void startProgressDialog(String title) {
        showDialog(title);
    }

    private boolean validRequest(VerifyPaymentHistoryOTPRequest request) {

        if(request.getOTP() == null || request.getOTP().isEmpty()){
            displayMessage(EnumErrors.INVALID_OTP.getErr());
            return false;
        }

        if(phone == null || phone.isEmpty()){
            Dialogs.mdConfirm("Failed to retrieve Dealer phone number, please contact support",SignInOtpVefirication.this);
            return false;
        }

        return true;

    }

    public void btnGoToSignUpClick(View view) {

        NavHandler.navToActivityAnimated(SignInOtpVefirication.this,Signup.class);

    }

    public VerifyPaymentHistoryOTPRequest getOTPVerificationRequest() {
        VerifyPaymentHistoryOTPRequest request = new VerifyPaymentHistoryOTPRequest();
        request.setOTP(edtOTP.getText().toString());
        request.setPhone(phone);
        return request;
    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {

        dialog = Dialogs.mdProgressDialog(SignInOtpVefirication.this, title);
        dialog.show();

    }

    @Override
    public void closeDialog() {
        dialog.dismiss();
    }

    @Override
    public void displayError(String error) {
        Dialogs.mdConfirm(error,SignInOtpVefirication.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,SignInOtpVefirication.this);
    }

    @Override
    public void grantAccess(LoginResponse response) {

        Dialogs.toast(SignInOtpVefirication.this,response.getStatusDescription());

        if(!preferencesDb.rememberMe()){
            preferencesDb.clear();
        }

     //   launchAccountDashboard();

    }

    @Override
    public void grantAccess(LoginStResponse response) {

    }


    private void launchAccountDashboard() {

        NavHandler.navToActivityAnimated(this, DashboardPage.class);
        finish();

    }

    @Override
    public void serverResponseDTVerifyOTPError(String error) {

        closeDialog();
        displayError(error);

    }

    @Override
    public void serverResponseVerifyOTPSuccess(VerifyPaymentHistoryOTPResponse response) {

        closeDialog();
        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            displayError(response.getStatusDescription());
            return;
        }

        //OTP Verified proceed to Login store the Customer Reference,
        attemptToSaveCustomerDetails(customerVerificationResponse);
        preferencesDb.setCustomerReferenceNumber(customerReference);
        launchAccountDashboard();

    }

    public void btnCancelClick(View view) {

        goBackToSignInPage();

    }

    private void goBackToSignInPage() {

        NavHandler.navToActivityAnimated(SignInOtpVefirication.this, SignIn.class);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBackToSignInPage();
    }

    private void attemptToSaveCustomerDetails(CustomerVerificationResponse customerVerificationRes) {

        AuthDealer authDealer = new AuthDealer();
        authDealer.setName(customerVerificationRes.getAccountName());
        authDealer.setReference(customerVerificationRes.getAccountNumber());
        authDealer.setPhone(customerVerificationRes.getPhone());
        authDealer.setBalance(customerVerificationRes.getBalanceResponse().getAvailableBalance());
        authDealer.setCreditLimit(customerVerificationRes.getBalanceResponse().getCreditLimit());
        authDealer.setCreditUsed(customerVerificationRes.getBalanceResponse().getCreditUsed());

        preferencesDb.setAuthDealer(authDealer);
        preferencesDb.setRememberMe(customerVerificationRes.isRememberMe());

    }

}
