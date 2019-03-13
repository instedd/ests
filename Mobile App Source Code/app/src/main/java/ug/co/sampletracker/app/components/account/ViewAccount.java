package ug.co.sampletracker.app.components.account;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.auth.AuthUser;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.general.DataFormat;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.Validation;

public class ViewAccount extends AppCompatActivity implements ViewAccountView {

    private Toolbar toolbar;
    private ViewAccountPresenter presenter;
    private TextView txvBalanceCreditLimit;
    private TextView txvBalanceCreditUsed;
    private TextView txvBalanceAvailableBalance;
    private MaterialDialog progressDialog;
    private PreferencesDb preferencesDb;
    private Button btnResetPassword;
    private CardView paneBalances;
    private EditText edtEmail;
    private EditText edtName;
    private EditText edtPhone;
    private View focusView;
    private CardView paneGeneralInfo;
    private View txvExitDealerAccount;
    private CardView panePasswordReset;
    private TextInputLayout edtEmailWrap;
    private TextInputLayout edtNameWrap;
    private TextInputLayout edtPhoneWrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);

        initialize();
        showUserGeneralInformation();

    }

    private void showUserGeneralInformation() {

        AuthUser user = preferencesDb.getAuthUser();
        edtEmail.setText(user.getEmail());
        edtName.setText(user.getName());
        edtPhone.setText(user.getPhone());

    }

    private void initialize() {

        initializeViews();
        initializeDependencies();
        setUpToolbar();
        attachListeners();
        DrawerUtil.getDrawer(this,toolbar,preferencesDb);


    }

    private void attachListeners() {

    }

    private void initializeDependencies() {

        DbHandler dbHandler = new DbHandler();
        preferencesDb = new PreferencesDb(ViewAccount.this);
        ViewAccountInteractor interactor = new ViewAccountInteractorImpl();
        presenter = new ViewAccountPresenter(this,interactor);
        presenter.setDbHandler(dbHandler);
        presenter.setPreferencesDb(preferencesDb);

    }

    private void initializeViews() {

        txvBalanceCreditLimit = findViewById(R.id.txvBalanceCreditLimit);
        txvBalanceCreditUsed = findViewById(R.id.txvBalanceCreditUsed);
        txvBalanceAvailableBalance = findViewById(R.id.txvBalanceAvailableBalance);

        txvExitDealerAccount = findViewById(R.id.txvExitDealerAccount);



        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);

        edtEmailWrap = (TextInputLayout)findViewById(R.id.edtEmailWrap);
        edtNameWrap = (TextInputLayout)findViewById(R.id.edtNameWrap);
        edtPhoneWrap = (TextInputLayout)findViewById(R.id.edtPhoneWrap);

        btnResetPassword = (Button)findViewById(R.id.btnResetPassword);
        paneBalances = (CardView) findViewById(R.id.paneBalances);
        paneGeneralInfo = (CardView) findViewById(R.id.paneGeneralInfo);
        panePasswordReset = (CardView) findViewById(R.id.panePasswordReset);

    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(ViewAccount.this.getResources().getString(R.string.module_account));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    @Override
    public void displayCreditBalances(Balance balance) {

        String creditLimit = DataFormat.formatToCurrency(balance.getCreditLimit());
        txvBalanceCreditLimit.setText(DataFormat.appendStringWithSpaceBtn("Limit:",creditLimit));
        String creditUsed = DataFormat.formatToCurrency(balance.getCreditUsed());
        txvBalanceCreditUsed.setText(DataFormat.appendStringWithSpaceBtn("Used:",creditUsed));
        String creditBalance = DataFormat.formatToCurrency(balance.getAvailableBalance());
        txvBalanceAvailableBalance.setText(DataFormat.appendStringWithSpaceBtn("Balance:",creditBalance));

    }


    @Override
    public void displayMessage(String error) {
        Dialogs.mdConfirm(error,ViewAccount.this);
    }

    @Override
    public void startDialog(String title) {

        if(progressDialog == null){
            progressDialog = Dialogs.mdProgressDialog(ViewAccount.this,title);
        }

        progressDialog.setTitle(title);
        progressDialog.show();

    }

    @Override
    public void stopDialog() {

        if(progressDialog != null){
            progressDialog.dismiss();
        }

    }

    public void refreshBalances(View view) {
        presenter.loadCreditBalances();
    }

    public void updateAccountDetails(View view) {

        RegistrationRequest request = getRegistrationRequest();

        if(!validInput(request)){

            focusView.requestFocus();
            return;

        }

        //todo update data with server
        presenter.updateGeneralAccountInfo(request);

    }

    private boolean validInput(RegistrationRequest request) {

        boolean valid = true;
        focusView = null;

        if(!Validation.validName(request.getName())){

            edtNameWrap.setError(EnumErrors.INVALID_NAME_FORMAT.getErr());
            valid = false;
            setViewWithFocus(edtName);
        }else{
            edtNameWrap.setErrorEnabled(false);
        }

        if(!Validation.validPhoneUsername(request.getTelephoneNo())){

            edtPhoneWrap.setError(EnumErrors.INVALID_REG_PHONE_NO.getErr());
            valid = false;
            setViewWithFocus(edtPhone);

        }else{
            edtPhoneWrap.setErrorEnabled(false);
        }

        if(!request.getEmail().isEmpty() && !Validation.validateEmail(request.getEmail())){

            edtEmailWrap.setError(EnumErrors.INVALID_EMAIL.getErr());
            valid = false;
            setViewWithFocus(edtPhone);

        }else{
            edtEmailWrap.setErrorEnabled(false);
        }

        return valid;

    }

    private void setViewWithFocus(View view) {

        if(focusView == null){
            focusView = view;
        }

    }

    private RegistrationRequest getRegistrationRequest() {

        RegistrationRequest request = new RegistrationRequest();
        request.setEmail(edtEmail.getText().toString());
        request.setName(edtName.getText().toString());
        request.setTelephoneNo(edtPhone.getText().toString());

        return  request;
    }

    @Override
    protected void onResume() {
        super.onResume();
       // customizePage();
    }
}
