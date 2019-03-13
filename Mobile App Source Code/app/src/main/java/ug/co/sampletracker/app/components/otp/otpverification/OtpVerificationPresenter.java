package ug.co.sampletracker.app.components.otp.otpverification;

import ug.co.sampletracker.app.connections.dataloaders.DTVerifyOTP;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.VerifyPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.VerifyPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 8/26/2018.
 */
public class OtpVerificationPresenter {

    OtpVerificationView view;
    OtpVerificationInteractor interactor;
    PreferencesDb preferencesDb;

    public OtpVerificationPresenter(OtpVerificationView view, OtpVerificationInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public void verifyOTP(VerifyPaymentHistoryOTPRequest otpRequest) {

        view.showDialog("Verifying PIN");

        DTVerifyOTP dtVerifyOTP = new DTVerifyOTP();
        dtVerifyOTP.setResponseListener(otpVerificationListener);
        dtVerifyOTP.verifyOTP(otpRequest);

    }

    private DTVerifyOTP.ServerResponseRequestOTPListener otpVerificationListener = new DTVerifyOTP.ServerResponseRequestOTPListener() {
        @Override
        public void serverResponseDTVerifyOTPError(String error) {
            view.closeDialog();
            view.displayError(error);
        }

        @Override
        public void serverResponseVerifyOTPSuccess(VerifyPaymentHistoryOTPResponse response) {

            view.closeDialog();

            if(response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){

                view.openNextActivity();
                return;
            }

            view.displayError(response.getStatusDescription());

        }
    };

}
