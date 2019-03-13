package ug.co.sampletracker.app.components.auth.signup;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.general.DataFormat;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.ViewHandler;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;

public class Signup extends AppCompatActivity implements SignupView, CompoundButton.OnCheckedChangeListener {

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhone;
    private EditText edtPassword,edtDealerNo;
    private SignupPresenter presenter;
    private PreferencesDb preferencesDb;
    private MaterialDialog dialog;
    private CheckBox chkBoxAccDealer, chkBoxAccIndividual;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private TextInputLayout edtNameWrap;
    private View focusView = null;
    private TextInputLayout edtEmailWrap;
    private TextInputLayout edtPhoneWrap;
    private TextInputLayout edtPasswordWrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_register);
        initialize();
    }

    private void initialize() {

        initializeViews();
        initializeDependencies();
        attachListeners();
        setUpToolbar();

    }

    private void attachListeners() {

        ViewHandler.setPasswordHintType(edtPassword);

        for (CheckBox cbox : checkBoxes) {
            cbox.setOnClickListener(mListener);
            cbox.setOnCheckedChangeListener(this);
        }

        EditText[] editTexts = new EditText[]{edtName,edtEmail,edtPhone,edtPassword};
        attachFocusChangeListener(editTexts);

    }

    private void initializeDependencies() {

        SignupInteractor interactor = new SignupInteractorImpl();
        presenter = new SignupPresenter(this,interactor);
        preferencesDb = new PreferencesDb(Signup.this);
        presenter.setPreferencesDb(preferencesDb);

    }

    private void initializeViews() {

        edtName = (EditText)findViewById(R.id.edtName);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtDealerNo = (EditText)findViewById(R.id.edtDealerNo);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        chkBoxAccDealer = (CheckBox)findViewById(R.id.chkBoxAccDealer);
        chkBoxAccIndividual = (CheckBox)findViewById(R.id.chkBoxAccIndividual);

        edtNameWrap = (TextInputLayout)findViewById(R.id.edtNameWrap);
        edtEmailWrap = (TextInputLayout)findViewById(R.id.edtEmailWrap);
        edtPhoneWrap = (TextInputLayout)findViewById(R.id.edtPhoneWrap);
        edtPasswordWrap = (TextInputLayout)findViewById(R.id.edtPasswordWrap);

        checkBoxes.add(chkBoxAccDealer);
        checkBoxes.add(chkBoxAccIndividual);

    }

    private void attachFocusChangeListener(EditText[] editTexts) {

        for (EditText edtText : editTexts) {
            edtText.setOnFocusChangeListener(new CustomTextWatcher(edtText));
        }

    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

    }

    public void btnCreateAccountClick(View view) {

        RegistrationRequest request = getRegistrationRequest();
        presenter.validateRequest(request);

    }

    private RegistrationRequest getRegistrationRequest() {

        String name = edtName.getText().toString();

        RegistrationRequest request = new RegistrationRequest();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String phone = edtPhone.getText().toString();

        request.setEmail(DataFormat.toLower(email));
        request.setName(DataFormat.toCapitalizeFully(name));
        request.setPassword(password);
        request.setTelephoneNo(phone);

        String accountType = chkBoxAccDealer.isChecked() ? ConstStrings.ACCOUNT_TYPE_DEALER :  ConstStrings.ACCOUNT_TYPE_INDIVIDUAL;
        request.setAccountType(accountType);

        return request;

    }

    public void btnGoToLoginClick(View view) {

        NavHandler.navToActivityAnimated(Signup.this,SignIn.class);
        finish();

    }

    @Override
    public void attemptRegistration(RegistrationRequest request) {

        presenter.registerUser(request);

    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

        if(invalidFields.containsKey(RegistrationRequest.Fields.NAME.getField())){

            edtNameWrap.setError(invalidFields.get(RegistrationRequest.Fields.NAME.getField()));
            focusView = focusView == null ? edtName : focusView;

        }else{
            edtNameWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(RegistrationRequest.Fields.EMAIL.getField())){
            edtEmailWrap.setError(invalidFields.get(RegistrationRequest.Fields.EMAIL.getField()));
            focusView = focusView == null ? edtEmail : focusView;
        }else{
            edtEmailWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(RegistrationRequest.Fields.TELEPHONE_NO.getField())){
            edtPhoneWrap.setError(invalidFields.get(RegistrationRequest.Fields.TELEPHONE_NO.getField()));
            focusView = focusView == null ? edtPhone : focusView;
        }else{
            edtPhoneWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(RegistrationRequest.Fields.PASSWORD.getField())){
            edtPasswordWrap.setError(invalidFields.get(RegistrationRequest.Fields.PASSWORD.getField()));
            focusView = focusView == null ? edtPassword : focusView;
        }else{
            edtPasswordWrap.setErrorEnabled(false);
        }

        if(focusView != null){
           // focusView.requestFocus();
        }

    }

    @Override
    public void showDialog(String title) {
        dialog = Dialogs.mdProgressDialog(Signup.this, title);
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
        Dialogs.mdConfirm(error,Signup.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,Signup.this);
    }

    @Override
    public void loadLoginForm() {
        NavHandler.navToActivityAnimated(Signup.this,SignIn.class);
        finish();
    }

    @Override
    public void displayRegistrationConfirmationMessage(String statusDescription) {

        Dialogs.mdPromptSingleBtn(Signup.this, "Operation Successful", statusDescription,
                (dialog, which) -> loadLoginForm());

    }

    private View.OnClickListener mListener = v -> {

        final int checkedId = v.getId();
        selectSingleCheckBoxFromCheckBoxGroup(checkedId, checkBoxes);

    };

    private void selectSingleCheckBoxFromCheckBoxGroup(int checkedId,List<CheckBox> checkBoxes) {

        for (final CheckBox current : checkBoxes) {
            if (current.getId() == checkedId) {
                current.setChecked(true);
            } else {
                current.setChecked(false);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        if(compoundButton.getId() == chkBoxAccDealer.getId()){

            if(checked){
                edtDealerNo.setVisibility(View.VISIBLE);
            }else{
                edtDealerNo.setVisibility(View.GONE);
            }

        }else{

            if(checked){
                edtDealerNo.setVisibility(View.GONE);
            }else{
                edtDealerNo.setVisibility(View.VISIBLE);
            }

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
                    case R.id.edtName:

                        presenter.validateField(RegistrationRequest.Fields.NAME.getField(),
                                edtName.getText().toString(),validationListener);

                        break;
                    case R.id.edtPhone:

                        presenter.validateField(RegistrationRequest.Fields.TELEPHONE_NO.getField(),
                                edtPhone.getText().toString(),validationListener);

                        break;
                    case R.id.edtEmail:

                        presenter.validateField(RegistrationRequest.Fields.EMAIL.getField(),
                                edtEmail.getText().toString(),validationListener);
                        break;
                    case R.id.edtPassword:

                        presenter.validateField(RegistrationRequest.Fields.PASSWORD.getField(),
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


}
