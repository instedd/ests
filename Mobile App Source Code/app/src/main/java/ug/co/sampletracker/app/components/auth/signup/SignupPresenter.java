package ug.co.sampletracker.app.components.auth.signup;

import java.util.HashMap;

import ug.co.sampletracker.app.connections.dataloaders.DTRegistration;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.models.responses.RegistrationResponse;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.Validation;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public class SignupPresenter implements DTRegistration.ServerResponseRegistrationListener {

    private SignupView view;
    private SignupInteractor interactor;
    private PreferencesDb preferencesDb;

    public SignupPresenter(SignupView view, SignupInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public void validateRequest(RegistrationRequest request) {

        HashMap<String, String> invalidFields = interactor.validateRequest(request);
        if(invalidFields.isEmpty()){
            view.attemptRegistration(request);
        }else{
            view.displayValidationErrors(invalidFields);
        }

    }

    public void registerUser(RegistrationRequest request) {

        String message = "Creating Account";
        view.showDialog(message);

        DTRegistration dtRegistration = new DTRegistration();
        dtRegistration.setResponseListener(this);
        dtRegistration.registerUser(request);

    }

    @Override
    public void serverResponseRegistrationError(String error) {
        view.closeDialog();
        view.displayError(error);
    }

    @Override
    public void serverResponseRegistrationSuccess(RegistrationResponse response) {

        view.closeDialog();
        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayError(response.getStatusDescription());
            return;
        }

        preferencesDb.disableIsFirstTimeRun();
        view.displayRegistrationConfirmationMessage(response.getStatusDescription());

    }


    public String validName(String name) {

        if(!Validation.validName(name)){
            return EnumErrors.INVALID_NAME_FORMAT.getErr();
        }
        return "";

    }

    public void validateField(String field, String value,
                              FieldValidationListener validationListener) {

        String err = interactor.validateField(field,value);

        if(err.isEmpty()){
            validationListener.validationResult(false,field,err);
            return;
        }

        validationListener.validationResult(true,field,err);

    }
}
