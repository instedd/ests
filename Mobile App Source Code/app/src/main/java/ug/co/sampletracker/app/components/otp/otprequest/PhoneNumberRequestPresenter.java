package ug.co.sampletracker.app.components.otp.otprequest;

import ug.co.sampletracker.app.connections.dataloaders.DTRequestOTP;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.RequestPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.RequestPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 8/26/2018.
 */
public class PhoneNumberRequestPresenter {

    PhoneNumberRequestView view;
    PhoneNumberRequestInteractor interactor;
    PreferencesDb preferencesDb;

    public PhoneNumberRequestPresenter(PhoneNumberRequestView view, PhoneNumberRequestInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public void requestOTP(RequestPaymentHistoryOTPRequest otpRequest) {

        view.showDialog("Requesting for PIN");

        DTRequestOTP dtRequestOTP = new DTRequestOTP();
        dtRequestOTP.setResponseListener(otpRequestListener);
        dtRequestOTP.requestOTP(otpRequest);

    }

    private DTRequestOTP.ServerResponseRequestOTPListener otpRequestListener = new DTRequestOTP.ServerResponseRequestOTPListener() {
        @Override
        public void serverResponseDTRequestOTPError(String error) {

            view.closeDialog();
            view.displayError(error);
        }

        @Override
        public void serverResponseRequestOTPSuccess(RequestPaymentHistoryOTPResponse response) {
            view.closeDialog();

            if(response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){

                view.openOTPVerificationPage();
                return;
            }

            view.displayError(response.getStatusDescription());

        }
    };

}
