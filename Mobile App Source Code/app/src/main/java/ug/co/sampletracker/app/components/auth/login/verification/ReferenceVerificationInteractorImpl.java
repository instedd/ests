package ug.co.sampletracker.app.components.auth.login.verification;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/27/2018.
 */

public class ReferenceVerificationInteractorImpl implements ReferenceVerificationInteractor {
    @Override
    public boolean validateCustomerReferenceNumber(String customerReferenceNo) {
        return customerReferenceNo != null && !customerReferenceNo.isEmpty();
    }
}
