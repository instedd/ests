package ug.co.sampletracker.app.components.receivedsamples.receivesample;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.connections.dataloaders.DLGetReceivedSample;
import ug.co.sampletracker.app.connections.dataloaders.DLSubmitReceivedSampleSample;
import ug.co.sampletracker.app.connections.dataloaders.DTAppData;
import ug.co.sampletracker.app.connections.dataloaders.DTPaymentUnReferenced;
import ug.co.sampletracker.app.connections.dataloaders.DTRequestOTP;
import ug.co.sampletracker.app.connections.dataloaders.DTVerifyOTP;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.DistributorRole;
import ug.co.sampletracker.app.models.Packaging;
import ug.co.sampletracker.app.models.Product;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.requests.RequestPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.requests.SampleReceiptionReq;
import ug.co.sampletracker.app.models.requests.VerifyPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;
import ug.co.sampletracker.app.models.responses.ReceiveSampleRes;
import ug.co.sampletracker.app.models.responses.RequestPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.models.responses.VerifyPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumPaymentTypes;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.DataFormat;
import ug.co.sampletracker.app.utils.general.Validation;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class ReceiveSamplePresenter implements DTAppData.ServerResponseAppDataListener,
        DTPaymentUnReferenced.ServerResponsePayHimaByUnReferencedListener,
        DTRequestOTP.ServerResponseRequestOTPListener, DTVerifyOTP.ServerResponseRequestOTPListener {

    private ReceiveSampleView view;
    private ReceiveSampleInteractor interactor;
    private PreferencesDb preferencesDb;
    private DbHandler dbHandler;
    private DataFormat dataFormat;


    public ReceiveSamplePresenter(ReceiveSampleView view, ReceiveSampleInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        dataFormat = new DataFormat();
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    public void setDbHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    void loadAppData() {

        view.startProgressDialog("Loading data");


        AppDataResponse appData = new DataManager().getAppData();

        view.displayDestinations(appData.getDestination());
        view.displayTransitPointsFacilities(appData.getHealth_facilities());
        view.displayTransporters(appData.getTransporter());
        view.displayReceivedSamples(appData.getReceivedSamples());

        view.stopProgressDialog();

        /*DLGetReceivedSample dl = new DLGetReceivedSample();
        dl.setResponseListener(serverResponseGetReceivedSamplesListener);
        dl.getReceivedSamples();*/

    }

    private DLGetReceivedSample.ServerResponseGetReceivedSamplesListener serverResponseGetReceivedSamplesListener = new DLGetReceivedSample.ServerResponseGetReceivedSamplesListener() {
        @Override
        public void serverResponseError(String error) {

            view.stopProgressDialog();

        }

        @Override
        public void serverResponseGetReceivedSamplesSuccess(List<StReceivedSample> receivedSamples) {

            view.stopProgressDialog();

            /*AppDataResponse appData = new DataManager().getAppData();
            appData.setReceivedSamples(receivedSamples);

            new DataManager().saveAppData(appData);*/

//            view.displayReceivedSamples(appData.getReceivedSamples());
            view.displayReceivedSamples(receivedSamples);

        }
    };

    @Override
    public void serverResponseDTRequestOTPError(String error) {

        view.stopProgressDialog();
        view.displayMessageDialog(error);

    }

    @Override
    public void serverResponseDTVerifyOTPError(String error)
    {
        view.stopProgressDialog();
        view.displayMessageDialog(error);
    }

    @Override
    public void serverResponseVerifyOTPSuccess(VerifyPaymentHistoryOTPResponse response) {

        view.stopProgressDialog();

        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayMessageDialog(response.getStatusDescription());
            return;
        }


        preferencesDb.setCustomerReferenceNoOtherCustomer(response.getPhone());
        view.openPaymentsHistoryPage();

    }

    @Override
    public void serverResponseRequestOTPSuccess(RequestPaymentHistoryOTPResponse response) {

        view.stopProgressDialog();
        view.displayMessageDialog(response.getStatusDescription());

        if(response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.enabledVerifyOTPFields(true);
        }

    }

    @Override
    public void serverResponsePayHimaByUnReferencedSuccess(PaymentUnreferencedResponse response) {

        view.stopProgressDialog();

        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){

            view.displayMessageDialog(response.getStatusDescription());
            return;
        }

        if(response.getPaymentMethod().equalsIgnoreCase(EnumPaymentTypes.MOBILE_MONEY.getType())){
            view.displaySuccessfullPaymentsAndStartWaitingDialog(response);
        }else{
            view.displayPaymentSuccessfulMessage(response);
        }


    }

    @Override
    public void serverResponseError(String error) {

        view.stopProgressDialog();
        view.displayMessageFailedToLoadAppData(error);

    }

    @Override
    public void serverResponseAppDataSuccess(AppDataResponse appDataResponse) {


        if(!appDataResponse.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayMessageFailedToLoadAppData(appDataResponse.getStatusDescription());
            return;
        }

        handleDistributorRoles(appDataResponse.getDistributorRoles());
        handleCementTypes(appDataResponse.getProducts());
        handlePackaging(appDataResponse.getPackagings());
        view.displayRegions(dbHandler.regions());

        view.stopProgressDialog();

    }

    private void handleCementTypes(List<Product> products) {

        interactor.updateCementTypes(dbHandler,products);

        List<String> cementTypes = new ArrayList<>();
        for (Product product : products) {
            cementTypes.add(product.itemName);
        }

        view.displayCementTypes(cementTypes);

    }

    private void handleDistributorRoles(List<DistributorRole> distributorRoles) {

        interactor.updateDistributorRoles(dbHandler,distributorRoles);

        List<String> roles = new ArrayList<>();
        for (DistributorRole role : distributorRoles) {
            roles.add(role.itemName);
        }

        view.displayDistributorRoles(roles);

    }

    private void handlePackaging(List<Packaging> packagings) {

        // todo Store or update Packaging
        dbHandler.saveHimaPackagings(packagings);

        List<String> packagingNames = new ArrayList<>();
        List<String> packagingUnits = new ArrayList<>();

        for (Packaging packaging : packagings) {

            packagingNames.add(packaging.getPackaging());
            packagingUnits.add(packaging.getUnitOfMeasure());

        }

        view.displayPackagings(packagingNames);
        view.displayPackagingsUnits(packagingUnits);

    }

    void validatePaymentRequest(OrderUnrefRequest request) {

        List<String> invalidFields = interactor.validateFields(dbHandler,request);

        if(!invalidFields.isEmpty()){

            view.displayValidationErrors(invalidFields);
            return;

        }

        view.confirmPayment(request);

    }

    public void initiatePaymentRequest(OrderUnrefRequest request) {

        view.startProgressDialog("Processing payment");

        DTPaymentUnReferenced dataloader = new DTPaymentUnReferenced();
        dataloader.setResponseListener(this);
        dataloader.payHimaByUnReferenced(request);

    }

    public void initiatePaymentRequest(OrderUnrefRequest request,
                                       DTPaymentUnReferenced.ServerResponsePayHimaByUnReferencedListener responseListener) {

        view.startProgressDialog("Processing payment");

        DTPaymentUnReferenced dataloader = new DTPaymentUnReferenced();
        dataloader.setResponseListener(responseListener);
        dataloader.payHimaByUnReferenced(request);

    }

    void calculateTotalAmount(String cementType, String quantity, String region, String customerType, boolean tobeDelivered) {

        Validation validation = new Validation();
        if(cementType == null || !validation.isDouble(quantity)){
            return;
        }

        Product product = new Product();
       // Product product = dbHandler.findCementTypeByName(cementType);
        product = dbHandler.findRegionProductPrice(region, cementType,customerType,tobeDelivered);

        if(product == null || !validation.isDouble(product.itemAmount)){
            return;
        }

        try {

            double dQuantity = Double.parseDouble(quantity);
            double dCost = Double.parseDouble(product.itemAmount);
            double totalAmount = dQuantity * dCost;
            view.displayTotalAmount(dataFormat.formatDouble(totalAmount));

        }catch (NumberFormatException ex){

            Log.d(ReceiveSamplePresenter.class.getName(),ex.getMessage());

        }

    }

    public void verifyOTP(VerifyPaymentHistoryOTPRequest otpRequest) {

        view.startProgressDialog("Verifying PIN");

        DTVerifyOTP dtVerifyOTP = new DTVerifyOTP();
        dtVerifyOTP.setResponseListener(this);
        dtVerifyOTP.verifyOTP(otpRequest);

    }

    public void requestForOTP(RequestPaymentHistoryOTPRequest request) {

        view.startProgressDialog("Requesting for PIN");

        DTRequestOTP dtRequestOTP = new DTRequestOTP();
        dtRequestOTP.setResponseListener(this);
        dtRequestOTP.requestOTP(request);

    }

    public String getPaymentCompletedMessage(PaymentUnreferencedResponse response) {

        if(response.getPaymentMethod().equalsIgnoreCase(EnumPaymentTypes.MOBILE_MONEY.getType())){
            return  ConstStrings.MSG_TXN_INITIATED;
        }else{
            return "Payment successful, Transaction ID: "+response.getChannelTxnId();
        }

    }

    public void validateField(String field, String value, FieldValidationListener validationListener) {


        String err = interactor.validateField(dbHandler, field,value);

        if(err.isEmpty()){
            validationListener.validationResult(false,field,err);
            return;
        }

        validationListener.validationResult(true,field,err);

    }

    public List<String> loadProductList(String region, String customerType) {

        return dbHandler.regionProducts(region,customerType);

    }

    public String getRegionCode(String regionName) {
        return dbHandler.findRegionCodeByRegionName(regionName);
    }

    public void submitReceivedSample(SampleReceiptionReq req) {

        view.startProgressDialog("Submitting sample");

        DLSubmitReceivedSampleSample dl = new DLSubmitReceivedSampleSample();
        dl.setResponseListener(serverResponseListener);
        dl.submitReceivedSample(req);

    }

    private DLSubmitReceivedSampleSample.ServerResponseSubmitReceivedSampleListener serverResponseListener =
            new DLSubmitReceivedSampleSample.ServerResponseSubmitReceivedSampleListener() {
        @Override
        public void serverResponseError(String error) {

            view.stopProgressDialog();
            view.displayMessageDialog(error);

        }

        @Override
        public void serverResponseSubmitReceivedSampleSuccess(ReceiveSampleRes response) {

            view.stopProgressDialog();

            if(!response.getStatus().equalsIgnoreCase(StatusCodes.SUCCESS)) {
                view.displayMessageDialog(response.getMessage());
                return;
            }

            view.clearFields();
            view.displayMessageDialog(response.getMessage());
        }
    };

}
