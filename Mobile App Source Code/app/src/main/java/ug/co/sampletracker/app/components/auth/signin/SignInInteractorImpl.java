package ug.co.sampletracker.app.components.auth.signin;

import ug.co.sampletracker.app.models.requests.LoginRequest;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.general.Validation;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 6/29/2018.
 */

public class SignInInteractorImpl implements SignInInteractor {
    @Override
    public String validateField(String field, String value) {


        String err = "";

        if(field.equals(LoginRequest.Fields.PHONE.getField())){

            err = Validation.validPhoneUsername(value) ? err : EnumErrors.INVALID_REG_PHONE_NO.getErr();
            return  err;

        }

        if(field.equals(LoginRequest.Fields.PASSWORD.getField())){

            err = Validation.validateEmail(value) ? err : EnumErrors.INVALID_PASSWORD_FORMAT.getErr();
            return  err;

        }

        return err;

    }
}
