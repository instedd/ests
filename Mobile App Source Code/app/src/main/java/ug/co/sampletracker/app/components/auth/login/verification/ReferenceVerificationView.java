package ug.co.sampletracker.app.components.auth.login.verification;

import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/27/2018.
 */

public interface ReferenceVerificationView {

    void displayErrorMsgInvalidCustomerReferenceNo(String message);
    void verifyCustomerReferenceNumber(String customerReferenceNo);
    void displayMessage(String message);
    void displayWelcomeMessage(CustomerVerificationResponse customerVerificationRes);
    void  startProgressDialog();
    void stopProgressDialog();

}
