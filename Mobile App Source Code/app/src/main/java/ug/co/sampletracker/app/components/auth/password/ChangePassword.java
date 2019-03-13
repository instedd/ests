package ug.co.sampletracker.app.components.auth.password;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.Validators;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.connections.dataloaders.DTUpdatePassword;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.models.responses.RegistrationResponse;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.Dialogs;

/**
 * Created by Banada ismael on 16/11/2015.
 */

/*
* Used to change user's login Password
* */
public class ChangePassword extends AppCompatActivity implements DTUpdatePassword.ServerResponseUpdatePasswordListener {
    private SharedPreferences.Editor preferences_editor;
    Button btnChangePassword;
    NavHandler navigation_handler;

    private EditText edtOldPassword;
    private EditText edtNewPassword;
    private EditText edtConfirmNewPassword;


    String super_agent_mail=null;

    private MaterialDialog progress_dialog;
    private MaterialDialog message_dialog;
    SharedPreferences sharedPreferences;
    private Validators validators;
    private Toolbar toolbar;
    private View focusedView;
    private PreferencesDb preferencesDb;
    private MaterialDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initialise();

    }

    /* Get references to UI widgets and instantiate objects*/
    private void initialise() {

        intializeViews();
        initializeDependencies();
        setUpToolbar();
        attachListeners();

    }

    private void initializeDependencies() {
        preferencesDb = new PreferencesDb(ChangePassword.this);
    }

    private void attachListeners() {

        btnChangePassword.setOnClickListener(btn->{
            btnChangePasswordClick();
        });

    }

    private void btnChangePasswordClick() {

        String oldPassword = edtOldPassword.getText().toString();
        String newPassword = edtNewPassword.getText().toString();
        String confirmNewPassword = edtConfirmNewPassword.getText().toString();

        if(registrationFailedValidationMsg(oldPassword,newPassword) != null){
            focusedView.requestFocus();
            return;
        }

        if(!newPassword.equalsIgnoreCase(confirmNewPassword)){
            edtConfirmNewPassword.setError("New password fields do not match");
            return;
        }

        RegistrationRequest request = new RegistrationRequest();
        request.setEmail(preferencesDb.getAuthUser().getEmail());
        request.setTelephoneNo(preferencesDb.getAuthUser().getPhone());
        request.setPassword(newPassword);

        updateUserPasswordWithServer(request);

    }

    private void updateUserPasswordWithServer(RegistrationRequest request) {

        showDialog();

        DTUpdatePassword dtUpdatePassword = new DTUpdatePassword();
        dtUpdatePassword.setResponseListener(this);
        dtUpdatePassword.UpdatePassword(request);

    }

    private void showDialog() {

        dialog = Dialogs.mdProgressDialog(ChangePassword.this,"Updating Password");
        dialog.show();

    }

    private void intializeViews() {

        edtOldPassword = (EditText)findViewById(R.id.old_password_txt_field);
        edtNewPassword = (EditText)findViewById(R.id.new_password_txt_field);
        edtConfirmNewPassword =(EditText)findViewById(R.id.confirm_new_password_txt_field);
        btnChangePassword = (Button)findViewById(R.id.change_password_btn);

    }

    private void setUpToolbar() {

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(ChangePassword.this.getResources().getString(R.string.module_account));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.over_all_settings, menu);
   //     getMenuInflater().inflate(R.menu.over_all_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }

        return  true;
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        finish();

    }

    /*
    *  Used to validate user input
    * */
    public String registrationFailedValidationMsg(String old_password, String new_password) {

        String ErrorMessage = null;
        focusedView = null;

        if (old_password.length() < 6) {


            edtOldPassword.setError("Old Password must be 6 or more characters");
            ErrorMessage = "Old Password must be 6 or more characters";
            setFocusedView(edtOldPassword);

        } else if(new_password.length() < 6)
        {

            edtNewPassword.setError("New Password must be 6 or more characters");
            ErrorMessage = "New Password must be 6 or more characters";
            setFocusedView(edtNewPassword);

        }else
        {
        //    ErrorMessage = "Please Check your input fields and try again";
        }

        return ErrorMessage;

    }

    private void setFocusedView(View view) {
        if(focusedView == null){
            focusedView = view;
        }
    }


    @Override
    public void serverResponseUpdatePasswordError(String error) {

        stopDialog();
        displayMessage(error);

    }

    private void displayMessage(String message) {
        Dialogs.mdConfirm(message,ChangePassword.this);
    }

    private void stopDialog() {
        if(dialog != null){
            dialog.dismiss();
        }
    }

    @Override
    public void serverResponseUpdatePasswordSuccess(RegistrationResponse response) {
        stopDialog();
        displayMessage(response.getStatusDescription());
        if(response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            clearFields();
        }
    }

    private void clearFields() {

        edtNewPassword.setText(null);
        edtConfirmNewPassword.setText(null);
        edtOldPassword.setText(null);

    }

}