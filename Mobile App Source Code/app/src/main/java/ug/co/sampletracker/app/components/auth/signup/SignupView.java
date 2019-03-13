package ug.co.sampletracker.app.components.auth.signup;

import java.util.HashMap;

import ug.co.sampletracker.app.models.requests.RegistrationRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public interface SignupView {

    void attemptRegistration(RegistrationRequest request);
    void displayValidationErrors(HashMap<String, String> invalidFields);
    void showDialog(String title);
    void closeDialog();
    void displayError(String error);
    void displayMessage(String message);
    void loadLoginForm();
    void displayRegistrationConfirmationMessage(String statusDescription);

}
