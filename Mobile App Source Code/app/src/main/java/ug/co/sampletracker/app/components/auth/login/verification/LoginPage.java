package ug.co.sampletracker.app.components.auth.login.verification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.auth.signin.otpverification.SignInOtpVefirication;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.auth.AuthUser;
import ug.co.sampletracker.app.models.requests.CustomerVerificationRequest;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.general.Dialogs;

public class LoginPage extends AppCompatActivity implements ReferenceVerificationView{

    private Button btnVerifyReferenceNumber;
    private EditText txvCustomerReferenceNo;
    private PreferencesDb preferencesDb;
    private ReferenceVerificationPresenter presenter;
    private MaterialDialog dialog;
    private CheckBox checkBoxRememberMe;
    private String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_reference_verification);

        initialize();
        attachListeners();
        attemptAutomaticLogin();
    }

    private void attemptAutomaticLogin() {

        if(preferencesDb.rememberMe()){
            presenter.validateCustomerReferenceNumber(preferencesDb.getAuthDealer().getReference());
        }

    }

    private void attachListeners() {
        btnVerifyReferenceNumber.setOnClickListener(btn -> handleReferenceNumberVerification());
    }

    private void handleReferenceNumberVerification() {

        String customerReferenceNo = txvCustomerReferenceNo.getText().toString();
        presenter.validateCustomerReferenceNumber(customerReferenceNo);

    }

    private void initialize() {
        setUpToolbar();
        initializeViews();
        initializeDependencies();
    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.m_service_name));

        setSupportActionBar(toolbar);
        getSupportActionBar();

    }

    private void initializeDependencies() {
        preferencesDb = new PreferencesDb(LoginPage.this);
        ReferenceVerificationInteractor interactor = new ReferenceVerificationInteractorImpl();
        presenter = new ReferenceVerificationPresenter(this,interactor);
    }

    private void initializeViews() {
        txvCustomerReferenceNo = findViewById(R.id.txvCustomerReferenceNo);
        btnVerifyReferenceNumber = findViewById(R.id.btnVerifyReferenceNumber);
        checkBoxRememberMe = (CheckBox)findViewById(R.id.checkBoxRememberMe);
    }

    @Override
    public void displayErrorMsgInvalidCustomerReferenceNo(String message) {
        displayMessage(message);
    }

    @Override
    public void verifyCustomerReferenceNumber(String customerReferenceNo) {

        attemptCustomerVerification(customerReferenceNo);

    }

    private void attemptCustomerVerification(String customerReferenceNo) {

        CustomerVerificationRequest req = new CustomerVerificationRequest();
        req.setCustomerReferenceNumber(customerReferenceNo);

        if(preferencesDb.isAuthUser()){
            //todo user is logged into, probably they need to access as a dealer agent
            AuthUser authUser = preferencesDb.getAuthUser();
            req.setPhoneNo(authUser.getPhone());
        }

        presenter.getCustomerDetails(req);

    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,LoginPage.this);
    }

    @Override
    public void displayWelcomeMessage(CustomerVerificationResponse customerVerificationRes) {

        customerVerificationRes.setRememberMe(checkBoxRememberMe.isChecked());
        txvCustomerReferenceNo.setText(null);

        //todo load the OTP Page
        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_PHONE,customerVerificationRes.getPhone());
        bundle.putString(ConstStrings.BUNDLE_CUST_REFERENCE,customerVerificationRes.getAccountNumber());

        String data = CustomerVerificationResponse.toGson(customerVerificationRes);

        Log.e(LoginPage.class.getName(),data);
        bundle.putString(ConstStrings.BUNDLE_DEALER_DATA,data);

        //todo go with the customer verification object to the next page
       // attemptToSaveCustomerDetails(customerVerificationRes);

        /*Intent intent = new Intent(LoginPage.this, SignInOtpVefirication.class);
        intent.putExtras(bundle);
        startActivity(intent);
*/
        NavHandler.navToActivityAnimatedWithBundle(LoginPage.this,SignInOtpVefirication.class,bundle);
        finish();

    }


    @Override
    public void startProgressDialog() {
        dialog = Dialogs.mdProgressDialog(LoginPage.this,"Verifying");
        dialog.show();
    }

    @Override
    public void stopProgressDialog() {
        if(dialog != null){
            dialog.dismiss();
        }
    }

    public void btnGoToLandingPage(View view) {

    }

}
