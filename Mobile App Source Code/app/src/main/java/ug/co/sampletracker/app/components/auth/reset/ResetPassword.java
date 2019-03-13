package ug.co.sampletracker.app.components.auth.reset;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.connections.dataloaders.DTResetPassword;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.RequestResetPasswordOTPReq;
import ug.co.sampletracker.app.models.requests.ResetPasswordReq;
import ug.co.sampletracker.app.models.responses.ResetPasswordRes;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.Validation;
import ug.co.sampletracker.app.utils.general.ViewHandler;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;

public class ResetPassword extends AppCompatActivity implements ResetPasswordView {

    private EditText edtPhone;
    private PreferencesDb preferencesDb;
    private RequestResetPasswordOTPPresenter presenter;
    private MaterialDialog dialog;
    private ProgressBar progressBar;
    private EditText reset_edtOTP;
    private EditText reset_edtPassword;
    private EditText reset_edtConfirmPassword;
    private MaterialDialog dialogConfirmPasswordReset;
    private TextInputLayout edtPhoneWrap;
    private TextInputLayout edtOTPWrap;
    private TextInputLayout edtPasswordWrap;
    private TextInputLayout edtConfirmPasswordWrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_reset_password);

        initialize();

    }

    private void initialize() {

        initializeViews();
        initializeDependencies();
        attachListeners();
        setUpToolbar();

    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

    }

    private void attachListeners() {

    }

    private void initializeDependencies() {

        ResetPasswordInteractor interactor = new ResetPasswordInteractorImpl();
        presenter = new RequestResetPasswordOTPPresenter(this,interactor);
        preferencesDb = new PreferencesDb(ResetPassword.this);
        presenter.setPreferencesDb(preferencesDb);

    }

    private void initializeViews() {

        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtPhoneWrap = (TextInputLayout)findViewById(R.id.edtPhoneWrap);

        edtPhoneWrap.setOnFocusChangeListener(new CustomTextWatcher(edtPhoneWrap));

    }

    private class  CustomTextWatcher implements View.OnFocusChangeListener {

        private View view1;
        private CustomTextWatcher(View view) {
            this.view1 = view;
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {

            if(!hasFocus){

                switch (view1.getId()) {

                    case R.id.edtPhone:

                        if(!validInput()){
                            edtPhoneWrap.setError(EnumErrors.INVALID_REG_PHONE_NO.getErr());
                        }else{
                            edtPhoneWrap.setErrorEnabled(false);
                        }

                        break;

                }

            }

        }

    }


    FieldValidationListener validationListener = (hasError, field, errorMessage) -> {

        HashMap<String, String> invalidFields = new HashMap<>();

        if(hasError) {
            invalidFields.put(field,errorMessage);
        }

        displayValidationErrors(invalidFields);

    };

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {

        if(dialog == null){
            dialog = Dialogs.mdProgressDialog(ResetPassword.this, title);
        }else{
            dialog.setTitle(title);
        }

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

        Dialogs.mdConfirm(error,ResetPassword.this);

    }

    @Override
    public void displayMessage(String message) {

    }

    public void btnGoToLoginClick(View view) {

        goToLogin();

    }

    private void goToLogin() {
        NavHandler.navToActivityAnimated(ResetPassword.this,SignIn.class);
        finish();
    }

    public void btnResetPasswordClick(View view) {

        if(validInput()){

            edtPhoneWrap.setErrorEnabled(false);

            String phoneNo = edtPhone.getText().toString();
            RequestResetPasswordOTPReq req = new RequestResetPasswordOTPReq();
            req.setPhone(phoneNo);
            presenter.resetPassword(req);

        }else{
            edtPhoneWrap.setError(EnumErrors.INVALID_REG_PHONE_NO.getErr());
        }

    }

    private boolean validInput() {

        return Validation.validPhoneUsername(edtPhone.getText().toString());

    }

    @Override
    public void displayOTPDialog(String phone) {

        View view = (ResetPassword.this).getLayoutInflater().inflate(R.layout.dialog_reset_password,null);

        Button btnReset = view.findViewById(R.id.btnResetPassword);
        progressBar = view.findViewById(R.id.progressBar);

        reset_edtOTP = (EditText)view.findViewById(R.id.edtOTP);
        reset_edtPassword = (EditText)view.findViewById(R.id.edtPassword);
        reset_edtConfirmPassword = (EditText)view.findViewById(R.id.edtConfirmPassword);

        edtOTPWrap = (TextInputLayout)view.findViewById(R.id.edtOTPWrap);
        edtPasswordWrap = (TextInputLayout)view.findViewById(R.id.edtPasswordWrap);
        edtConfirmPasswordWrap = (TextInputLayout)view.findViewById(R.id.edtConfirmPasswordWrap);

        ViewHandler.setPasswordHintType(reset_edtPassword);
        ViewHandler.setPasswordHintType(reset_edtConfirmPassword);

        btnReset.setOnClickListener(btn->{

            performActualPasswordReset(phone);

        });

        dialogConfirmPasswordReset = Dialogs.mdCustomDialogNoButtons(view, ResetPassword.this, "Password Reset");
        dialogConfirmPasswordReset.setCanceledOnTouchOutside(true);
        dialogConfirmPasswordReset.show();

    }

    private void performActualPasswordReset(String phone) {

        ResetPasswordReq req = getResetPasswordReq(phone);
        if(!validPasswordResetDialogInput(req)){
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        DTResetPassword dtResetPassword = new DTResetPassword();
        dtResetPassword.setResponseListener(new DTResetPassword.ServerResponseResetPasswordRequestOTPListener() {
            @Override
            public void serverResponseResetPasswordError(String error) {

                progressBar.setVisibility(View.INVISIBLE);
                displayError(error);

            }

            @Override
            public void serverResponseResetPasswordSuccess(ResetPasswordRes response) {

                progressBar.setVisibility(View.INVISIBLE);
                if(response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
                    showPasswordResetConfirmation(response.statusDescription);
                }else{
                    displayError(response.getStatusDescription());
                }

            }
        });

        dtResetPassword.resetPassword(req);


    }

    private boolean validPasswordResetDialogInput(ResetPasswordReq req) {

        if(!Validation.validOTP(req.getOtp())){

            edtOTPWrap.setError(EnumErrors.INVALID_OTP.getErr());
            return false;
        }else{
            edtOTPWrap.setErrorEnabled(false);
        }

        if(!Validation.validPassword(req.getPassword())){
            edtPasswordWrap.setError(EnumErrors.INVALID_PASSWORD_FORMAT.getErr());
            return false;
        }else{
            edtPasswordWrap.setErrorEnabled(false);
        }

        if(!req.getPassword().equalsIgnoreCase(reset_edtConfirmPassword.getText().toString())){
            edtConfirmPasswordWrap.setError(EnumErrors.PASSWORD_MISMATCH.getErr());
            return false;
        }else{
            edtConfirmPasswordWrap.setErrorEnabled(false);
        }

        if(req.getPhone() == null || req.getPhone().isEmpty()){
            Dialogs.toast(ResetPassword.this,EnumErrors.FAILED_TO_LOAD_PHONE.getErr());
            return false;
        }

        return true;
    }

    private void showPasswordResetConfirmation(String message) {
        Dialogs.mdPrompt(ResetPassword.this, "Password Reset", message+"\nProceed to Login?", (dialog, which) -> goToLogin());
        dialogConfirmPasswordReset.dismiss();
    }

    private ResetPasswordReq getResetPasswordReq(String phone) {

        ResetPasswordReq req = new ResetPasswordReq();
        req.setPhone(phone);
        req.setOtp(reset_edtOTP.getText().toString());
        req.setPassword(reset_edtPassword.getText().toString());

        return req;
    }
}
