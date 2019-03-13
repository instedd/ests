package ug.co.sampletracker.app.components.auth.signin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.auth.reset.ResetPassword;
import ug.co.sampletracker.app.components.auth.signup.Signup;
import ug.co.sampletracker.app.components.dashboard.DashboardPage;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.LoginRequest;
import ug.co.sampletracker.app.models.requests.LoginStRequest;
import ug.co.sampletracker.app.models.responses.LoginResponse;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.Validation;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;

public class SignIn extends AppCompatActivity implements SignInView {

    private EditText edtPhone;
    private EditText edtPassword;
    private SignInPresenter presenter;
    private MaterialDialog dialog;
    private PreferencesDb preferencesDb;
    private TextInputLayout edtPhoneWrap;
    private TextInputLayout edtPasswordWrap;
    private View focusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);
        initialize();

    }

    private void initialize() {

        //setUpToolbar();
        initializeViews();
        initiatilizeDependencies();
        setPasswordHintType();
        attachListeners();

    }

    private void attachListeners() {

        EditText[] editTexts = new EditText[]{edtPhone,edtPassword};
        attachFocusChangeListener(editTexts);

    }

    private void attachFocusChangeListener(EditText[] editTexts) {

        for (EditText edtText : editTexts) {
            edtText.setOnFocusChangeListener(new CustomTextWatcher(edtText));
        }

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

                        presenter.validateField(LoginRequest.Fields.PHONE.getField(),
                                edtPhone.getText().toString(),validationListener);

                        break;

                    case R.id.edtPassword:

                        presenter.validateField(LoginRequest.Fields.PASSWORD.getField(),
                                edtPassword.getText().toString(),validationListener);
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

    private void setPasswordHintType() {
        edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    private void initiatilizeDependencies() {

        DbHandler dbHandler = new DbHandler();
        preferencesDb = new PreferencesDb(SignIn.this);

        SignInInteractor interactor = new SignInInteractorImpl();
        presenter = new SignInPresenter(this,interactor);

        presenter.setPreferencesDb(preferencesDb);
        presenter.setDbHandler(dbHandler);

    }

    private void initializeViews() {

        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtPassword = (EditText)findViewById(R.id.edtPassword);

        edtPhoneWrap = (TextInputLayout) findViewById(R.id.edtPhoneWrap);
        edtPasswordWrap = (TextInputLayout) findViewById(R.id.edtPasswordWrap);

    }


    public void btnLoginClick(View view) {

        LoginStRequest request = getLoginRequest();

        if(validRequest(request)){

            presenter.authenticateUser(request);

        }

    }

    private boolean validRequest(LoginStRequest request) {

        boolean valid = true;

        HashMap<String, String> hashMap = new HashMap<>();

        if(!Validation.validPhoneUsername(request.getTxt_username())){


            hashMap.put(LoginStRequest.Fields.NAME.getField(),EnumErrors.INVALID_REG_PHONE_NO.getErr());
            valid = false;
        }

        if(!Validation.validPassword(request.getTxt_password())){

            hashMap.put(LoginStRequest.Fields.PASSWORD.getField(),EnumErrors.INVALID_PASSWORD_FORMAT.getErr());
            valid = false;
        }


        displayValidationErrors(hashMap);
        return valid;

    }

    public void btnGoToSignUpClick(View view) {

        NavHandler.navToActivityAnimated(SignIn.this,Signup.class);

    }

    public LoginStRequest getLoginRequest() {
        LoginStRequest request = new LoginStRequest();
        request.setTxt_username(edtPhone.getText().toString());
        request.setTxt_password(edtPassword.getText().toString());
        return request;
    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

        if(invalidFields.containsKey(LoginRequest.Fields.PHONE.getField())){

            edtPhoneWrap.setError(invalidFields.get(LoginRequest.Fields.PHONE.getField()));
            focusView = focusView == null ? edtPhone : focusView;

        }else{
            edtPhoneWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(LoginRequest.Fields.PASSWORD.getField())){

            edtPasswordWrap.setError(invalidFields.get(LoginRequest.Fields.PASSWORD.getField()));
            focusView = focusView == null ? edtPassword : focusView;

        }else{
            edtPasswordWrap.setErrorEnabled(false);
        }

    }


    @Override
    public void showDialog(String title) {

        dialog = Dialogs.mdProgressDialog(SignIn.this, title);
        dialog.show();

    }

    @Override
    public void closeDialog() {
        dialog.dismiss();
    }

    @Override
    public void displayError(String error) {
        Dialogs.mdConfirm(error,SignIn.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,SignIn.this);
    }

    @Override
    public void grantAccess(LoginResponse response) {

    }

    @Override
    public void grantAccess(LoginStResponse response) {

        Dialogs.toast(SignIn.this,response.getMessage());

        NavHandler.navToActivityAnimated(SignIn.this, DashboardPage.class);
        finish();

    }


    public void btnForgotPasswordClick(View view) {

        NavHandler.navToActivityAnimated(SignIn.this,ResetPassword.class);

    }

    public void btnAccessAsGuest(View view) {

    }

}
