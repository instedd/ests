package ug.co.sampletracker.app.utils.interfaces;

import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public interface ServerResponseCustomerVerificationListener {

    void serverResponseError(String error);
    void serverResponseCustomerVerificationSuccess(CustomerVerificationResponse verificationResponse);

}
