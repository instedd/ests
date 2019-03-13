package ug.co.sampletracker.app.components.auth.signup;

import java.util.HashMap;

import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.general.Validation;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/2/2018.
 */

public class SignupInteractorImpl implements SignupInteractor {

    @Override
    public HashMap<String, String> validateRequest(RegistrationRequest request) {

        HashMap<String, String> invalidFields = new HashMap<>();

        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();
        String phone = request.getTelephoneNo();

        if(!Validation.validName(name)){
            invalidFields.put(RegistrationRequest.Fields.NAME.getField(),EnumErrors.INVALID_NAME_FORMAT.getErr());
        }

        if(email != null && !email.isEmpty()){

            if(!Validation.validateEmail(email)){
                invalidFields.put(RegistrationRequest.Fields.EMAIL.getField(),EnumErrors.INVALID_EMAIL.getErr());
            }

        }

        if(!Validation.validPhoneUsername(phone)){
            invalidFields.put(RegistrationRequest.Fields.TELEPHONE_NO.getField(),EnumErrors.INVALID_REG_PHONE_NO.getErr());
        }

        if(!Validation.validPassword(password)){
            invalidFields.put(RegistrationRequest.Fields.PASSWORD.getField(),EnumErrors.INVALID_PASSWORD_FORMAT.getErr());
        }

        return invalidFields;
    }

    @Override
    public String validateField(String field, String value) {

        String err = "";

        if(field.equals(RegistrationRequest.Fields.NAME.getField())){

            err = Validation.validName(value) ? err : EnumErrors.INVALID_NAME_FORMAT.getErr();
            return  err;

        }

        if(field.equals(RegistrationRequest.Fields.EMAIL.getField()) && !value.isEmpty()){

            err = Validation.validateEmail(value) ? err : EnumErrors.INVALID_EMAIL.getErr();
            return  err;

        }

        if(field.equals(RegistrationRequest.Fields.TELEPHONE_NO.getField())){

            err = Validation.validPhoneUsername(value) ? err : EnumErrors.INVALID_REG_PHONE_NO.getErr();
            return  err;

        }

        if(field.equals(RegistrationRequest.Fields.PASSWORD.getField())){

            err = Validation.validPassword(value) ? err : EnumErrors.INVALID_PASSWORD_FORMAT.getErr();
            return  err;

        }

        return err;

    }
}
