package ug.co.sampletracker.app.components.auth.signup;

import java.util.HashMap;

import ug.co.sampletracker.app.models.requests.RegistrationRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public interface SignupInteractor {

    HashMap<String,String> validateRequest(RegistrationRequest request);
    String validateField(String field, String value);

}
