package ug.co.sampletracker.app.components.auth.login.verification;

import android.os.AsyncTask;

import org.apache.commons.lang3.text.WordUtils;

import ug.co.sampletracker.app.connections.dataloaders.DTCustomerVerification;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.models.requests.CustomerVerificationRequest;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.models.soap.requests.CustomerVerificationReq;
import ug.co.sampletracker.app.models.soap.responses.CustomerVerificationRes;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.interfaces.ServerResponseCustomerVerificationListener;
import ug.co.sampletracker.app.utils.soap.RequestMapper;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/27/2018.
 */

public class ReferenceVerificationPresenter implements ServerResponseCustomerVerificationListener {

    private ReferenceVerificationView view;
    private ReferenceVerificationInteractor interactor;

    public ReferenceVerificationPresenter(ReferenceVerificationView view, ReferenceVerificationInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void validateCustomerReferenceNumber(String customerReferenceNo) {

        boolean valid = interactor.validateCustomerReferenceNumber(customerReferenceNo);
        if(!valid){
            view.displayErrorMsgInvalidCustomerReferenceNo(EnumErrors.INVALID_DEALER_NO.getErr());
            return;
        }

        view.verifyCustomerReferenceNumber(customerReferenceNo);

    }

    public void getCustomerDetails(CustomerVerificationRequest req) {

        view.startProgressDialog();
        DTCustomerVerification dataLoader = new DTCustomerVerification();
        dataLoader.setResponseListener(this);
        dataLoader.fetchCustomerDetails(req);

    }

    @Override
    public void serverResponseError(String error) {
        view.stopProgressDialog();
        view.displayMessage(error);
    }

    @Override
    public void serverResponseCustomerVerificationSuccess(CustomerVerificationResponse verificationResponse) {
        view.stopProgressDialog();
        processCustomerDetailsResponse(verificationResponse);
    }

    private void processCustomerDetailsResponse(CustomerVerificationResponse customerVerificationRes) {

        if(!customerVerificationRes.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){

            String message = customerVerificationRes.statusDescription.replace("_", " ");
            view.displayMessage(WordUtils.capitalizeFully(message));
            return;

        }

        view.displayWelcomeMessage(customerVerificationRes);
        saveDefaultInformation(customerVerificationRes);

    }

    private void saveDefaultInformation(CustomerVerificationResponse customerVerificationRes) {

        //start thread to save roles and products
        DbHandler dbHandler = new DbHandler();
        dbHandler.saveHimaRolesAndProducts(customerVerificationRes);

    }

    private class GetCustomerDetailsAsyncTask extends AsyncTask<Void,Void,CustomerVerificationRes>{

        private CustomerVerificationReq req;

        GetCustomerDetailsAsyncTask(CustomerVerificationReq req) {
            this.req = req;
        }

        @Override
        protected CustomerVerificationRes doInBackground(Void... voids) {

            if (req != null) {

                RequestMapper mapper = new RequestMapper();
                return mapper.verifyCustomer(req);

            }
            return null;

        }

        @Override
        protected void onPostExecute(CustomerVerificationRes customerVerificationRes) {
            super.onPostExecute(customerVerificationRes);

            view.stopProgressDialog();

            if(customerVerificationRes == null){
                view.displayMessage("Oops, something went wrong while we were verifying the reference number");
                return;
            }

            if(!customerVerificationRes.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){

                view.displayMessage(customerVerificationRes.statusDescription);
                return;
            }

         //   view.displayWelcomeMessage(customerVerificationRes);

        }


    }

}
