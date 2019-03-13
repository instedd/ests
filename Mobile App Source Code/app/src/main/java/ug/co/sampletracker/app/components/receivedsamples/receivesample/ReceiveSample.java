package ug.co.sampletracker.app.components.receivedsamples.receivesample;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.otp.otprequest.PhoneNumberRequest;
import ug.co.sampletracker.app.components.progressbar.DialogProgressBar;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.OtpVerificationData;
import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.models.StRegisteredSamplePojo;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.requests.SampleReceiptionReq;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.DataGenerator;
import ug.co.sampletracker.app.utils.constants.EnumCustomerTypes;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.EnumModules;
import ug.co.sampletracker.app.utils.constants.EnumPaymentTypes;
import ug.co.sampletracker.app.utils.general.DataFormat;
import ug.co.sampletracker.app.utils.general.DateHandler;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.SpinnerHandler;
import ug.co.sampletracker.app.utils.general.ViewHandler;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;
import ug.co.sampletracker.app.utils.receivers.SmsBroadcastReceiver;

public class ReceiveSample extends AppCompatActivity implements
        ReceiveSampleView, AdapterView.OnItemSelectedListener, SmsBroadcastReceiver.Listener, DatePickerDialog.OnDateSetListener {

    private static final int SMS_PERMISSION_CODE = 9291;
    private static final int CAMERA_PERMISSION_CODE = 120;
    private Toolbar toolbar;
    private Button btnSubmit;
    private PreferencesDb preferencesDb;
    private EditText edtPaymentUnrefDistributorNo;
    private TextView txvUnitOfMeasure;
    private MaterialSpinner spinnerSampleStatus;

    private EditText edtPaymentUnrefQuantity;
    private EditText edtPaymentUnrefAmount;
    private ReceiveSamplePresenter presenter;
    private MaterialDialog dialog;
    private ArrayAdapter<String> packagingsAdapter;
    private ArrayAdapter<String> rolesAdapter;
    private ArrayAdapter<String> measureUnitsAdapter;
    private ArrayAdapter<String> cementTypesAdapter;
    private EditText edtPaymentUnrefPhone;

    private SmsBroadcastReceiver smsBroadcastReceiver;
    private TextInputLayout edtPaymentUnrefDistributorNoWrap;
    private TextInputLayout edtPaymentUnrefQuantityWrap;
    private TextInputLayout edtPaymentUnrefAmountWrap;
    private TextInputLayout edtPaymentUnrefPhoneWrap;
    private ArrayAdapter<String> spinnerRegionsAdapter;
    private MaterialSpinner spinnerDestinations;
    private EditText edtDateReceived;
    private EditText edtAddress;
    private RadioGroup rgDeliver;
    private TextInputLayout edtExpectedDateWrap;
    private TextInputLayout edtAddressWrap;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1000;
    private ArrayAdapter<String> specimenAdapter;
    private ArrayAdapter<String> transitPointsAdapter;
    private ArrayAdapter<String> transportersAdapters;
    private ArrayAdapter<String> destinationAdapters;
    private ArrayAdapter<String> diseasesAdapter;
    private MaterialSpinner spinnerIsFinalDestination;
    private MaterialSpinner spinnerTransporter;
    private MaterialSpinner spinnerSampleId;
    private MaterialSpinner spinnerTransitPoint;
    private ArrayAdapter<String> receivedSamplesAdapter;
    private EditText edtSampleId;
    private Button btnScanCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_receive_sample);

        initialize();
        populateFields();

        Bundle bundle = getIntent().getExtras();
        autofillData(bundle);

    }

    /**
     * If we are coming from Dealer Selection @{@link  class} the BUNDLE_ORDER has
     * the data we moved with that class, now we use this method to retrieve and repopulate that data
     * @param bundle
     */
    private void autofillData(Bundle bundle) {

        Log.e(ReceiveSample.class.getName(),"#mtd#autofillData Called");

        if(bundle != null){

            String data = bundle.getString(ConstStrings.BUNDLE_ORDER);
            OrderUnrefRequest order = OrderUnrefRequest.fromGson(data);
            Log.e(ReceiveSample.class.getName(),data);

            edtPaymentUnrefPhone.setText(order.getPhone());
            edtPaymentUnrefAmount.setText(order.getAmount());
            edtPaymentUnrefDistributorNo.setText(order.getDistributorNo());
            edtPaymentUnrefQuantity.setText(order.getQuantity());
            edtDateReceived.setText(order.getExpectedDate());
            edtAddress.setText(order.getDeliveryAddress());

            if(order.getDeliverToPremises().equalsIgnoreCase(Boolean.TRUE.toString())){
                rgDeliver.check(R.id.rbtnDeliverYes);
            }else{
                rgDeliver.check(R.id.rbtnDeliverNo);
            }

            SpinnerHandler.selectValue(spinnerDestinations,spinnerRegionsAdapter,order.getRegionName());
            SpinnerHandler.selectValue(spinnerSampleStatus,cementTypesAdapter, order.getItemName());

        }

    }

    private void populateFields() {

        presenter.loadAppData();

    }

    private void initialize() {

        initializeViews();
        setUpToolbar();
        initializeDependencies();
        DrawerUtil.getDrawer(this,toolbar,preferencesDb);
        attachListeners();

    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(ReceiveSample.this);
        DbHandler dbHandler = new DbHandler();
        ReceiveSampleInteractor interactor = new ReceiveSampleInteractorImpl();
        presenter = new ReceiveSamplePresenter(this,interactor);

        presenter.setPreferencesDb(preferencesDb);
        presenter.setDbHandler(dbHandler);

    }

    private void attachListeners() {

        btnSubmit.setOnClickListener(btn-> btnSubmitReceivedSampleDetailsClicked());
        btnScanCode.setOnClickListener(btn-> btnScanCodeClicked());
        edtDateReceived.setOnClickListener(btn->showDatePicker());

        spinnerSampleId.setOnItemSelectedListener(this);
        spinnerSampleStatus.setOnItemSelectedListener(this);
        spinnerDestinations.setOnItemSelectedListener(this);

        edtPaymentUnrefQuantity.addTextChangedListener(quantityTextWatcher);
        edtAddress.setOnClickListener(edt->locationPlaceAutoCompleteActivity());
        edtAddress.setFocusable(false);

        EditText[] editTexts = new EditText[]{edtPaymentUnrefPhone,edtPaymentUnrefAmount,edtPaymentUnrefDistributorNo,edtPaymentUnrefQuantity};
      //  attachFocusChangeListener(editTexts);

        rgDeliver.setOnCheckedChangeListener(onDeliveryCheckListener);
        rgDeliver.check(R.id.rbtnDeliverNo);

    }

    private void btnScanCodeClicked() {
        scanQRCode();
    }

    public void scanQRCode() {

        if (!isCameraPermissionGranted()) {
            requestCameraPermission();
            return;
        }

        IntentIntegrator integrator = new IntentIntegrator(ReceiveSample.this);
        integrator.initiateScan();

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean arePermissionsEnabled(String[] permissions){
        for(String permission : permissions){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }


    /**
     * Check if we have Camera permission
     */
    public boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request runtime Camera permission
     */
    private void requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // You may display a non-blocking explanation here, read more in the documentation:
            // https://developer.android.com/training/permissions/requesting.html
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);

    }

    private void locationPlaceAutoCompleteActivity() {

        try {

            AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(Place.TYPE_COUNTRY)
                    .setCountry("UG")
                    .build();

            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(autocompleteFilter)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }

    private void showDatePicker() {
        Dialogs.showDatePicker(ReceiveSample.this,this);
    }

    RadioGroup.OnCheckedChangeListener onDeliveryCheckListener = (radioGroup, i) -> {

        int showDeliveryDetailsFields = getDeliverOption().equalsIgnoreCase(Boolean.TRUE.toString()) ? View.VISIBLE : View.GONE;
        LinearLayout layoutDeliveryDetails = findViewById(R.id.layoutDeliveryDetails);
        layoutDeliveryDetails.setVisibility(showDeliveryDetailsFields);

        calculateTotalAmount();

    };

    private void attachFocusChangeListener(EditText[] editTexts) {

        for (EditText edtText : editTexts) {
            edtText.setOnFocusChangeListener(new CustomTextWatcher(edtText));
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        GregorianCalendar calendar = new GregorianCalendar(year,month,dayOfMonth);
        String date = new DateHandler().getHumanReadableDate(calendar.getTime(), ConstStrings.DATE_FORMAT_TXN_SHORT);
        edtDateReceived.setText(date);

    }

    private class  CustomTextWatcher implements View.OnFocusChangeListener {

        private View view1;

        private CustomTextWatcher(View view) {
            this.view1 = view;
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {

            if(!hasFocus){

                switch (view1.getId()) {

                    case R.id.edtPaymentUnrefQuantity:

                        presenter.validateField(OrderUnrefRequest.Fields.QUANTITY.getName(),
                                edtPaymentUnrefQuantity.getText().toString(),validationListener);
                        break;
                    case R.id.edtPaymentUnrefAmount:

                        presenter.validateField(OrderUnrefRequest.Fields.AMOUNT.getName(),
                                edtPaymentUnrefAmount.getText().toString(),validationListener);
                        break;
                    case R.id.edtPaymentUnrefPhone:

                        presenter.validateField(OrderUnrefRequest.Fields.PHONE.getName(),
                                edtPaymentUnrefPhone.getText().toString(),validationListener);
                        break;
                    case R.id.edtPaymentUnrefDistributorNo:

                        presenter.validateField(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName(),
                                edtPaymentUnrefDistributorNo.getText().toString(),validationListener);
                        break;

                }

            }

        }

    }


    FieldValidationListener validationListener = (hasError, field, errorMessage) -> {

        HashMap<String, String> invalidFields = new HashMap<>();

        if(hasError) {
            invalidFields.put(field,errorMessage);
        }

        displayValidationErrors(invalidFields);

    };

    private void displayValidationErrors(HashMap<String, String> invalidFields) {

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.ITEM_NAME.getName())){

            spinnerSampleStatus.setError(invalidFields.get(OrderUnrefRequest.Fields.ITEM_NAME.getName()));

        }else{
            spinnerSampleStatus.setError(null);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.QUANTITY.getName())){

            edtPaymentUnrefQuantityWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.QUANTITY.getName()));

        }else{
            edtPaymentUnrefQuantityWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.AMOUNT.getName())){

            edtPaymentUnrefAmountWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.AMOUNT.getName()));

        }else{
            edtPaymentUnrefAmountWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.PHONE.getName())){

            if(preferencesDb.isAuthUser()){
                displayMessageDialog(invalidFields.get(OrderUnrefRequest.Fields.PHONE.getName()));
            }
            edtPaymentUnrefPhoneWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.PHONE.getName()));

        }else{
            edtPaymentUnrefPhoneWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.EXPECTED_DATE.getName())){

            edtExpectedDateWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.EXPECTED_DATE.getName()));

        }else{
            edtExpectedDateWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.DELIVERY_ADDRESS.getName())){

            edtAddressWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.DELIVERY_ADDRESS.getName()));

        }else{
            edtAddressWrap.setErrorEnabled(false);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                edtAddress.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                displayMessageDialog(status.getStatusMessage().toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }else{

            handleBarcodeScanResult(requestCode, resultCode, data);

        }

    }

    private void navigateToPhoneNumberRequestPage() {

        OtpVerificationData otpVerificationData = new OtpVerificationData();
        otpVerificationData.setNextActivity(EnumModules.ORDER_HISTORY_UNREF.getMod());

        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_OTP_VERIFICATION,otpVerificationData.toGson(otpVerificationData));

        Intent intent = new Intent(ReceiveSample.this, PhoneNumberRequest.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void handlePhoneNumberRequest() {

        if(isSmsPermissionGranted()){

            navigateToPhoneNumberRequestPage();
            return;

        }

        String message = getResources().getString(R.string.message_request_sms_permission);
        Dialogs.mdPrompt(ReceiveSample.this,"SMS Permission", message, (dialog, which) -> {
            requestReadAndSendSmsPermission();
        });

    }

    /**
     * Check if we have SMS permission
     */
    public boolean isSmsPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request runtime SMS permission
     */
    private void requestReadAndSendSmsPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            // You may display a non-blocking explanation here, read more in the documentation:
            // https://developer.android.com/training/permissions/requesting.html
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case SMS_PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    navigateToPhoneNumberRequestPage();

                } else {
                    displayMessageDialog("We cannot proceed to payment history without the permission");
                }
                break;
            }
        }
    }

    @Override
    public void onTextReceived(String text) {

        String code = text.replace(ConstStrings.OTP_SMS_START_TEXT,"").trim();
        unregisterReceiver(smsBroadcastReceiver);

    }

    private void btnSubmitReceivedSampleDetailsClicked() {

        SampleReceiptionReq req = getSampleRegistrationReq();
        presenter.submitReceivedSample(req);

      //  OrderUnrefRequest request = getPaymentUnreferencedRequest();

        /*First clear existing errors*/
//        clearErrors();
//        presenter.validatePaymentRequest(request);

    }

    private SampleReceiptionReq getSampleRegistrationReq() {

        SampleReceiptionReq req = new SampleReceiptionReq();

        /* Here we pick the spinner values for the items where we need the ID not the name */
        String selectedFinalDestination = SpinnerHandler.spinnerValue(spinnerDestinations);
        String selectedTransporter = SpinnerHandler.spinnerValue(spinnerTransporter);
        String selectedFacilityCode = SpinnerHandler.spinnerValue(spinnerTransitPoint);


        int final_destination = getFinalDestination(selectedFinalDestination);
        String sample_status = SpinnerHandler.spinnerValue(spinnerSampleStatus);
        int transporter = getTransporterId(selectedTransporter);
        String barcode = edtSampleId.getText().toString();//SpinnerHandler.spinnerValue(spinnerSampleId);
        int facility_code = getTransitPoint(selectedFacilityCode);
        String is_destination = SpinnerHandler.spinnerValue(spinnerIsFinalDestination);
        String dateReceived = edtDateReceived.getText().toString();

        req.setFinal_destination(final_destination);
        req.setSample_status(sample_status);
        req.setTransporter(transporter);
        req.setBarcode(barcode);
        req.setFacility_code(facility_code);
        req.setIs_destination(is_destination);
        req.setDateReceived(dateReceived);

        return req;

    }

    private int getTransitPoint(String selectedFacilityCode) {

        for(StFacility facility : DataManager.appData.getHealth_facilities()) {

            if (facility.name.equalsIgnoreCase(selectedFacilityCode)) {
                return Integer.parseInt(facility.id);
            }

        }
        return 0;
    }

    private int getTransporterId(String selectedTransporter) {

        for(StTransporter transporter : DataManager.appData.getTransporter()) {

            if (transporter.name.equalsIgnoreCase(selectedTransporter)) {
                return Integer.parseInt(transporter.id);
            }

        }
        return 0;
    }

    private int getFinalDestination(String selectedFinalDestination) {

        for(StDestination dest : DataManager.appData.getDestination()){

            if(dest.name.equalsIgnoreCase(selectedFinalDestination)){
                return Integer.parseInt(dest.id);
            }

        }

        return 0;
    }

    private OrderUnrefRequest getPaymentUnreferencedRequest() {

        OrderUnrefRequest request = new OrderUnrefRequest();

        String phone = edtPaymentUnrefPhone.getText().toString();
        String amount = edtPaymentUnrefAmount.getText().toString();
        String distributorNo = edtPaymentUnrefDistributorNo.getText().toString();
        String quantity = edtPaymentUnrefQuantity.getText().toString();
        String itemName = getSelectedSpinnerValue(spinnerSampleStatus);
        String unitsOfMeasure = txvUnitOfMeasure.getText().toString();
        String deliveryAddress = edtAddress.getText().toString();
        String expectedDate = edtDateReceived.getText().toString();
        String region = getSelectedSpinnerValue(spinnerDestinations);

        request.setPhone(phone);
        request.setAmount(amount.replace(",",""));
        request.setDistributorNo(distributorNo);
        request.setQuantity(quantity);
        request.setNoOfBags(quantity);
        request.setItemName(itemName);
        request.setPackaging("Bags");
        request.setUnitsOfMeasure(unitsOfMeasure);
        request.setDeliverToPremises(getDeliverOption());
        request.setDeliveryAddress(deliveryAddress);
        request.setExpectedDate(expectedDate);
        request.setRegionName(region);
        request.setRegionCode(presenter.getRegionCode(region));

        return request;
    }

    private String getSelectedSpinnerValue(MaterialSpinner spinner) {

        if(spinner != null && spinner.getSelectedItem() != null){

            return spinner.getSelectedItem().toString();

        }
        return ConstStrings.PROMPT_OPTION;
    }

    private String getDeliverOption() {

        return rgDeliver.getCheckedRadioButtonId() == R.id.rbtnDeliverYes ?
                Boolean.TRUE.toString() : Boolean.FALSE.toString();

    }

    private void initializeViews() {

        btnSubmit = findViewById(R.id.btnSubmit);

        spinnerSampleId = findViewById(R.id.spinnerSampleId);
        spinnerSampleStatus = findViewById(R.id.spinnerSampleStatus);
        spinnerIsFinalDestination = findViewById(R.id.spinnerIsFinalDestination);
        spinnerTransitPoint = findViewById(R.id.spinnerTransitPoint);
        spinnerDestinations = findViewById(R.id.spinnerDestination);
        spinnerTransporter = findViewById(R.id.spinnerTransporter);
        edtDateReceived = findViewById(R.id.edtDateReceived);

        edtSampleId = (EditText)findViewById(R.id.edtSampleId);
        btnScanCode = (Button)findViewById(R.id.btnScanCode);

        txvUnitOfMeasure = findViewById(R.id.txvUnitOfMeasure);
        edtPaymentUnrefQuantity = findViewById(R.id.edtPaymentUnrefQuantity);
        edtPaymentUnrefAmount = findViewById(R.id.edtPaymentUnrefAmount);
        edtPaymentUnrefPhone = findViewById(R.id.edtPaymentUnrefPhone);
        edtAddress = findViewById(R.id.edtAddress);
        rgDeliver = findViewById(R.id.rgDeliver);
        edtPaymentUnrefDistributorNoWrap = findViewById(R.id.edtPaymentUnrefDistributorNoWrap);
        edtPaymentUnrefQuantityWrap = findViewById(R.id.edtPaymentUnrefQuantityWrap);
        edtPaymentUnrefAmountWrap = findViewById(R.id.edtPaymentUnrefAmountWrap);
        edtPaymentUnrefPhoneWrap = findViewById(R.id.edtPaymentUnrefPhoneWrap);
        edtExpectedDateWrap = findViewById(R.id.edtExpectedDateWrap);
        edtAddressWrap = findViewById(R.id.edtAddressWrap);

    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(ReceiveSample.this.getResources().getString(R.string.module_receive_sample));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    @Override
    public void displayMessageFailedToLoadAppData(String error) {

        displayCementTypes(Collections.emptyList());
        displayDistributorRoles(Collections.emptyList());
        displayPackagings(Collections.emptyList());
        displayPackagingsUnits(Collections.emptyList());

        //todo show prompt to reload data or go back to home page
        String message = "Failed to update app data with error: "+error+" click YES to try again.";
        String title = "Operation Failed";
        Dialogs.mdPrompt(ReceiveSample.this, title, message, (dialog, which) -> presenter.loadAppData());

    }

    @Override
    public void displayDistributorRoles(List<String> distributorRoles) {

    }

    @Override
    public void displayPackagings(List<String> packagings) {

    }

    @Override
    public void displayPackagingsUnits(List<String> packagingUnits) {

        measureUnitsAdapter = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, packagingUnits);
//        SpinnerHandler.populateSpinner(txvUnitOfMeasure,measureUnitsAdapter);

    }

    @Override
    public void displayCementTypes(List<String> cementTypes) {

        cementTypesAdapter = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, cementTypes);
        SpinnerHandler.populateSpinner(spinnerSampleStatus,cementTypesAdapter);

    }

    @Override
    public void displayValidationErrors(List<String> invalidFields) {

        clearErrorFlags();

        HashMap<String, String> invalid = new HashMap<>();

        if(invalidFields.contains(OrderUnrefRequest.Fields.PHONE.getName())){

            String error = preferencesDb.isAuthUser() ? EnumErrors.FAILED_TO_LOAD_PHONE_LOGIN_AGAIN.getErr(): EnumErrors.INVALID_REG_PHONE_NO.getErr();
            invalid.put(OrderUnrefRequest.Fields.PHONE.getName(),error);

        }
        if(invalidFields.contains(OrderUnrefRequest.Fields.ROLE.getName())){
            invalid.put(OrderUnrefRequest.Fields.ROLE.getName(),EnumErrors.SELECT_ROLE.getErr());
        }
        if(invalidFields.contains(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName())){
            invalid.put(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName(),EnumErrors.SELECT_DEALER.getErr());
        }
        if(invalidFields.contains(OrderUnrefRequest.Fields.ITEM_NAME.getName())){
            invalid.put(OrderUnrefRequest.Fields.ITEM_NAME.getName(),EnumErrors.SELECT_PRODT.getErr());
        }

        if(invalidFields.contains(OrderUnrefRequest.Fields.QUANTITY.getName())){
            invalid.put(OrderUnrefRequest.Fields.QUANTITY.getName(),EnumErrors.INVALID_QUANTITY.getErr());
        }
        if(invalidFields.contains(OrderUnrefRequest.Fields.AMOUNT.getName())){
            invalid.put(OrderUnrefRequest.Fields.AMOUNT.getName(),EnumErrors.INVALID_AMOUNT.getErr());
        }

        if(invalidFields.contains(OrderUnrefRequest.Fields.EXPECTED_DATE.getName())){
            invalid.put(OrderUnrefRequest.Fields.EXPECTED_DATE.getName(),EnumErrors.INVALID_DATE.getErr());
        }

        if(invalidFields.contains(OrderUnrefRequest.Fields.DELIVERY_ADDRESS.getName())){
            invalid.put(OrderUnrefRequest.Fields.DELIVERY_ADDRESS.getName(),EnumErrors.INVALID_DELIVERY_ADDRESS.getErr());
        }

        displayValidationErrors(invalid);

    }

    private void clearErrorFlags() {

        EditText[] editTexts = {edtPaymentUnrefQuantity,edtPaymentUnrefAmount,edtPaymentUnrefPhone,edtPaymentUnrefDistributorNo};
        ViewHandler.clearErrors(editTexts);

    }

    @Override
    public void displayMessageDialog(String message) {
        Dialogs.mdConfirm(message,ReceiveSample.this);
    }

    @Override
    public void displayPaymentSuccessfulMessage(PaymentUnreferencedResponse response) {

        //todo confirm response sent from server
        clearFields();

        String message = presenter.getPaymentCompletedMessage(response);
        Dialogs.mdConfirm(message,ReceiveSample.this);

    }

    @Override
    public void setUnitOfMeasureSelection(String unitOfMeasure) {

        txvUnitOfMeasure.setText(unitOfMeasure);

    }

    @Override
    public void displayTotalAmount(String amount) {

        edtPaymentUnrefAmount.setText(amount);

    }

    @Override
    public void confirmPayment(OrderUnrefRequest request) {


        //showPaymentConfirmationDialog(request);
        Dialogs.mdSingleChoiceSelectionDialog(ReceiveSample.this,"Payment Method",
                DataGenerator.paymentMethods(preferencesDb.isAuthDealer()), listCallBackSingleChoiceListener);

    }

    MaterialDialog.ListCallbackSingleChoice listCallBackSingleChoiceListener = (dialog, itemView, which, text) -> {

        OrderUnrefRequest request = getPaymentUnreferencedRequest();

        switch (which){
            case 0:{

                break;
            }
            case 1:{
                break;
            }
            case 2:{
                //todo here it's a cash payment for only individuals
                request.setPaymentMode(EnumPaymentTypes.CASH.getType());
                showPaymentConfirmationDialog(request);
                break;
            }
        }

        return false;
    };

    private void showPaymentConfirmationDialog(OrderUnrefRequest request) {

        View view = getLayoutInflater().inflate(R.layout.dialog_payment_confirmation,null);
        TextView txvPaymentAmount = view.findViewById(R.id.txvPaymentConfirmation);
        TextView lblPhone = view.findViewById(R.id.lblPhone);
        TextView txvPaymentPhone = view.findViewById(R.id.txvPaymentConfirmationPhone);
        TextView txvDeliveryDate = view.findViewById(R.id.txvDeliveryDate);

        RelativeLayout layoutDeliveryDate = view.findViewById(R.id.layoutDeliveryDate);
        txvPaymentAmount.setText(new DataFormat().formatDouble(Double.parseDouble(request.getAmount().replace(",",""))));

        /*Since this shown here only for Cash, we don't have Phone hence below*/
        lblPhone.setText("Dealer name");
        txvPaymentPhone.setText(request.getDealerName());

        layoutDeliveryDate.setVisibility(View.GONE);

        MaterialDialog dialog = Dialogs.mdCustomDialog(view, ReceiveSample.this,  ConstStrings.CONFIRM_TRANSACTION, (dialog1, which) -> {
            dialog1.dismiss();
            presenter.initiatePaymentRequest(request);
        });

        dialog.show();
    }

    @Override
    public void openPaymentsHistoryPage() {

        if(dialog != null){
            dialog.dismiss();
        }

    }

    @Override
    public void enabledVerifyOTPFields(boolean enabled) {
    }

    @Override
    public void displaySuccessfullPaymentsAndStartWaitingDialog(PaymentUnreferencedResponse response) {

        clearErrors();
        new DialogProgressBar().showDialog(ReceiveSample.this,response.getTransactionNo());

    }

    @Override
    public void displayRegions(List<String> regions) {

        spinnerRegionsAdapter = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, regions);
        SpinnerHandler.populateSpinner(spinnerDestinations, spinnerRegionsAdapter);

    }

    @Override
    public void displayDiseases(List<StDisease> diseases) {

    }

    @Override
    public void displayDestinations(List<StDestination> list) {

        List<String> names = new ArrayList<>();
        for (StDestination destination : list ) {
            names.add(destination.name);
        }

        destinationAdapters = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerDestinations, destinationAdapters);

    }

    @Override
    public void displayTransitPointsFacilities(List<StFacility> health_facilities) {

        List<String> names = new ArrayList<>();
        for (StFacility item : health_facilities ) {
            names.add(item.name);
        }
        transitPointsAdapter = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerTransitPoint, transitPointsAdapter);
    }

    @Override
    public void displaySpecimen(List<StSpecimen> specimenList) {

    }

    @Override
    public void displayTransporters(List<StTransporter> transporters) {

        List<String> names = new ArrayList<>();
        for (StTransporter item : transporters ) {
            names.add(item.name);
        }
        transportersAdapters = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerTransporter, transportersAdapters);
    }

    @Override
    public void displayReceivedSamples(List<StReceivedSample> receivedSamples) {

        List<String> names = new ArrayList<>();
        for (StReceivedSample item : receivedSamples ) {
            names.add(item.getSample_id());
        }
        receivedSamplesAdapter = new ArrayAdapter<>(ReceiveSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerSampleId, receivedSamplesAdapter);

    }

    private void clearErrors() {
        EditText[] edts = {edtPaymentUnrefDistributorNo,edtPaymentUnrefQuantity,edtPaymentUnrefPhone,
                edtPaymentUnrefAmount,edtAddress, edtDateReceived};
        TextInputLayout[] edtWraps = {edtPaymentUnrefDistributorNoWrap,edtPaymentUnrefQuantityWrap,edtPaymentUnrefPhoneWrap,
                edtPaymentUnrefAmountWrap,edtAddressWrap,edtExpectedDateWrap};

        ViewHandler.clearErrors(edts);
        ViewHandler.clearErrors(edtWraps);
    }

    @Override
    public void clearFields() {

        EditText[] edts = {edtDateReceived,edtSampleId};
        MaterialSpinner[] spinners = {spinnerSampleId, spinnerSampleStatus,
                spinnerIsFinalDestination,spinnerTransitPoint,spinnerDestinations,spinnerTransporter};

        SpinnerHandler.clearSpinners(spinners);

        for (EditText editText : edts) {
            editText.setText(null);
        }

    }

    @Override
    public void stopProgressDialog() {
        dialog.dismiss();
    }

    @Override
    public void startProgressDialog(String message) {

        dialog = Dialogs.mdProgressDialog(ReceiveSample.this,message);
        dialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//        int spinnerId = parent.getId();

        /*if(spinnerId == R.id.spinnerSampleStatus){

            if(spinnerSampleStatus == null || parent.getItemAtPosition(position) == null){
                return;
            }
            calculateTotalAmount();

        } else if(spinnerId == R.id.spinnerRegion){

            if(spinnerDestinations == null || parent.getItemAtPosition(position) == null){
                return;
            }

            reloadTheProductsList(SpinnerHandler.spinnerValue(spinnerDestinations));
            calculateTotalAmount();
            resetRegionDependentFields();

        }*/

    }

    private void resetRegionDependentFields() {

        spinnerSampleStatus.setSelection(0);
        edtPaymentUnrefQuantity.setText(null);
        edtPaymentUnrefAmount.setText(null);

    }

    private void reloadTheProductsList(String region) {

        String customerType = preferencesDb.isAuthDealer() ? EnumCustomerTypes.DEALER.getType() : EnumCustomerTypes.INDIVIDUAL.getType();
        List<String> productList = presenter.loadProductList(region,customerType);
        displayCementTypes(productList);

    }

    private void calculateTotalAmount() {

        if(spinnerSampleStatus == null || spinnerSampleStatus.getSelectedItem() == null){
            return;
        }

        if(spinnerDestinations == null || spinnerDestinations.getSelectedItem() == null){
            return;
        }

        String region = spinnerDestinations.getSelectedItem().toString();
        String cementTYpe = spinnerSampleStatus.getSelectedItem().toString();
        String quantity = edtPaymentUnrefQuantity.getText().toString();
        String customerType = preferencesDb.isAuthDealer() ? EnumCustomerTypes.DEALER.getType() : EnumCustomerTypes.INDIVIDUAL.getType();
        boolean tobeDelivered = getDeliverOption().equalsIgnoreCase(Boolean.TRUE.toString());

        presenter.calculateTotalAmount(cementTYpe,quantity,region,customerType,tobeDelivered);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private TextWatcher quantityTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculateTotalAmount();
        }

        @Override
        public void afterTextChanged(Editable s) {        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //autofillData(bundle);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        Bundle bundle = intent.getExtras();
        autofillData(bundle);

    }


    private void handleBarcodeScanResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            String scannedSampleId = result.getContents();

            if (scannedSampleId == null) {

                String toast = "Cancelled from activity";
                Toast.makeText(ReceiveSample.this, toast, Toast.LENGTH_SHORT).show();

            } else {

                edtSampleId.setText(scannedSampleId);

                String toast = "Scanned sample with ID : " + scannedSampleId;
                Toast.makeText(ReceiveSample.this, toast, Toast.LENGTH_SHORT).show();

                /* Check if the Sample is still in the registered samples list */
                StRegisteredSamplePojo sampleInRegistered = DataManager.findSampleToReceiveInRegisteredSamplesBySampleId(scannedSampleId);

                if(sampleInRegistered != null){

                    String destId = sampleInRegistered.getDestination_id();
                    String destination = DataManager.findDestNameById(destId);
                    SpinnerHandler.selectValue(spinnerDestinations,destinationAdapters,destId);
                    spinnerDestinations.setEnabled(false);

                    Log.e("REGISTERED SAMPLE ",destId + " Name "+destination);

                    return;
                }

                /* By the time we reach here the sample is not in the registered, so we check the received samples list */
                StReceivedSample sampleInReceived = DataManager.findSampleToReceiveInReceivedSamplesBySampleId(scannedSampleId);

                if(sampleInReceived != null){

                    String destId = sampleInReceived.getDestination_id();
                    String destination = DataManager.findDestNameById(destId);
                    SpinnerHandler.selectValue(spinnerDestinations,destinationAdapters,destId);
                    spinnerDestinations.setEnabled(false);

                    Log.e("RECEIVED SAMPLE ",destId + " Name "+destination);

                }

                /*Now we have failed to find the sample either in received or registered so advise the guy*/
                displayMessageDialog("Scanned Sample with ID ["+ scannedSampleId +"] cannot be received into the system");

            }

            // At this point we may or may not have a reference to the activity

        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}
