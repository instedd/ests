package ug.co.sampletracker.app.components.auth.reset;

import ug.co.sampletracker.app.connections.dataloaders.DTRequestResetPasswordOTP;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.requests.RequestResetPasswordOTPReq;
import ug.co.sampletracker.app.models.responses.RequestResetPasswordOTPRes;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/20/2018.
 */
public class RequestResetPasswordOTPPresenter implements DTRequestResetPasswordOTP.ServerResponseRequestResetPasswordOTPRequestOTPListener {

    private ResetPasswordView view;
    private ResetPasswordInteractor interactor;
    private PreferencesDb preferencesDb;

    public RequestResetPasswordOTPPresenter(ResetPasswordView view, ResetPasswordInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public void resetPassword(RequestResetPasswordOTPReq req) {

        view.showDialog("Resetting Password");
        DTRequestResetPasswordOTP dtRequestResetPasswordOTP = new DTRequestResetPasswordOTP();
        dtRequestResetPasswordOTP.setResponseListener(this);
        dtRequestResetPasswordOTP.resetPassword(req);

    }

    @Override
    public void serverResponseRequestResetPasswordOTPError(String error) {

        view.closeDialog();
        view.displayError(error);

    }

    @Override
    public void serverResponseRequestResetPasswordOTPSuccess(RequestResetPasswordOTPRes response) {

        view.closeDialog();
        if(response.statusCode.equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayOTPDialog(response.getPhone());
        }else{
            view.displayError(response.getStatusDescription());
        }

    }
}
