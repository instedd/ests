package ug.co.sampletracker.app.components.registeredsamples.registersample;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mindorks.paracamera.Camera;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.zelory.compressor.Compressor;
import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.otp.otprequest.PhoneNumberRequest;
import ug.co.sampletracker.app.components.progressbar.DialogProgressBar;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.FormData;
import ug.co.sampletracker.app.models.OtpVerificationData;
import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.requests.SampleRegistrationReq;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.EnumModules;
import ug.co.sampletracker.app.utils.general.DateHandler;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.ImageFilePath;
import ug.co.sampletracker.app.utils.general.SpinnerHandler;
import ug.co.sampletracker.app.utils.general.ViewHandler;
import ug.co.sampletracker.app.utils.interfaces.FieldValidationListener;
import ug.co.sampletracker.app.utils.receivers.SmsBroadcastReceiver;

public class RegisterSample extends AppCompatActivity implements
        RegisterSampleView, AdapterView.OnItemSelectedListener,
        SmsBroadcastReceiver.Listener, DatePickerDialog.OnDateSetListener {

    private static final int SMS_PERMISSION_CODE = 9291;
    private static final int CAMERA_PERMISSION_CODE = 120;
    private static final int FILE_SELECTION_CODE = 129;
    private static final int REQUEST_TAKE_PHOTO_USING_CAMERA_LIB = 121;
    private static final int REQUEST_SELECT_IMAGE_IN_ALBUM = 122;
    private Toolbar toolbar;
    private Button btnSubmit;
    private PreferencesDb preferencesDb;
    private MaterialSpinner spinnerFacilities;
    private MaterialSpinner spinnerDiseases;

    private RegisterSamplePresenter presenter;
    private MaterialDialog dialog;
    private ArrayAdapter<String> packagingsAdapter;
    private ArrayAdapter<String> rolesAdapter;
    private ArrayAdapter<String> measureUnitsAdapter;
    private ArrayAdapter<String> cementTypesAdapter;
    private Button btnScanCode;

    private SmsBroadcastReceiver smsBroadcastReceiver;
    private TextInputLayout edtClinicalNotesWrap;
    private TextInputLayout edtExpectedDateToReachWrap;
    private EditText edtSampleId;
    private ArrayAdapter<String> spinnerRegionsAdapter;
    private MaterialSpinner spinnerDestinations;
    private EditText edtSampleTakingDate;
    private TextInputLayout edtExpectedDateWrap;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1000;
    private TextInputLayout edtSampleIdWrap;
    private ArrayAdapter<String> specimenAdapter;
    private ArrayAdapter<String> facilitiesAdapter;
    private ArrayAdapter<String> transportersAdapters;
    private ArrayAdapter<String> destinationsAdapters;
    private ArrayAdapter<String> diseasesAdapter;
    private MaterialSpinner spinnerSpecimen;
    private MaterialSpinner spinnerTransporter;
    private EditText edtExpectedDateToReach;

    private Camera camera;
    private Bitmap bitmapFormData;
    private FormData formData;
    private EditText edtClinicalNotes;
    private EditText edtAttachment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_sample);

        initialize();
        populateFields();

    }

    private void populateFields() {

        presenter.loadAppData();

    }

    public void btnUploadFormClick(View view){

        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if(!arePermissionsEnabled(permissions)){
                requestMultiplePermissions(permissions);
                return;
            }

            showPictureDialog();
            //Dialogs.fileChooserDialog(RegisterSample.this, FILE_SELECTION_CODE);

        }

        showPictureDialog();
       // Dialogs.fileChooserDialog(RegisterSample.this, FILE_SELECTION_CODE);

    }

    private void showPictureDialog() {

        List<String> choices = new ArrayList<>();
        choices.add("Select photo from gallery");
        choices.add("Capture photo from camera");

        Dialogs.mdSingleChoiceSelectionDialog(RegisterSample.this,"Select action",choices,listCallbackSingleChoice);

    }

    MaterialDialog.ListCallbackSingleChoice listCallbackSingleChoice = (dialog, itemView, position, text) -> {

        switch (position){

            case 0:{
                selectImageInAlbum();
                dialog.dismiss();
                break;
            }
            case 1:{

                try {
                    camera.takePicture();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
                break;
            }

        }

        return false;
    };


    private void selectImageInAlbum() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestMultiplePermissions(String[] permissions){

        List<String> remainingPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
            }
        }

        requestPermissions(remainingPermissions.toArray(new String[remainingPermissions.size()]), 505);

    }


    private void initialize() {

        initializeViews();
        setUpToolbar();
        initializeDependencies();
        DrawerUtil.getDrawer(this,toolbar,preferencesDb);
        attachListeners();
        setUpCameraObj();

    }

    private void setUpCameraObj() {

        // Build the camera
        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                .setTakePhotoRequestCode(REQUEST_TAKE_PHOTO_USING_CAMERA_LIB)
                .setDirectory("pics")
                .setName("sample_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(100)
                .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                .build(this);

    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(RegisterSample.this);
        DbHandler dbHandler = new DbHandler();
        RegisterSampleInteractor interactor = new RegisterSampleInteractorImpl();
        presenter = new RegisterSamplePresenter(this,interactor);

        presenter.setPreferencesDb(preferencesDb);
        presenter.setDbHandler(dbHandler);

    }

    private void attachListeners() {

        btnSubmit.setOnClickListener(btn-> btnSubmitSampleClicked());
        btnScanCode.setOnClickListener(btn-> btnScanCodeClicked());

        edtSampleTakingDate.setOnClickListener(btn->showDatePicker(this));
        edtExpectedDateToReach.setOnClickListener(btn->showDatePicker(dateSetListener));

        spinnerDiseases.setOnItemSelectedListener(this);
        spinnerDestinations.setOnItemSelectedListener(this);


        EditText[] editTexts = new EditText[]{edtSampleTakingDate};
        attachFocusChangeListener(editTexts);

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            GregorianCalendar calendar = new GregorianCalendar(year,month,dayOfMonth);
            Date selectedDate = calendar.getTime();

            if(DateHandler.dateIsBeforeToday(selectedDate)){

                edtExpectedDateToReach.setText(null);
                displayMessageDialog("Old date selected");
                return;
            }

            String date = new DateHandler().getHumanReadableDate(calendar.getTime(), ConstStrings.DATE_FORMAT_TXN_SHORT);
            edtExpectedDateToReach.setText(date);

        }

    };



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

    private void showDatePicker(DatePickerDialog.OnDateSetListener dateSetListener) {

        Dialogs.showDatePicker(RegisterSample.this,dateSetListener);

    }


    private void attachFocusChangeListener(EditText[] editTexts) {

        for (EditText edtText : editTexts) {
            edtText.setOnFocusChangeListener(new CustomTextWatcher(edtText));
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        GregorianCalendar calendar = new GregorianCalendar(year,month,dayOfMonth);
        Date selectedDate = calendar.getTime();

        if(DateHandler.dateIsBeforeToday(selectedDate)){

            edtSampleTakingDate.setText(null);
            displayMessageDialog("Old date selected");
            return;
        }

        String date = new DateHandler().getHumanReadableDate(calendar.getTime(), ConstStrings.DATE_FORMAT_TXN_SHORT);
        edtSampleTakingDate.setText(date);

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

                     //   presenter.validateField(OrderUnrefRequest.Fields.QUANTITY.getName(),
                       //         edtPaymentUnrefQuantity.getText().toString(),validationListener);
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

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.ROLE.getName())){

            spinnerFacilities.setError(invalidFields.get(OrderUnrefRequest.Fields.ROLE.getName()));

        }else{
           spinnerFacilities.setError(null);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.ITEM_NAME.getName())){

            spinnerDiseases.setError(invalidFields.get(OrderUnrefRequest.Fields.ITEM_NAME.getName()));

        }else{
            spinnerDiseases.setError(null);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.QUANTITY.getName())){

            edtClinicalNotesWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.QUANTITY.getName()));

        }else{
            edtClinicalNotesWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.AMOUNT.getName())){

            edtExpectedDateToReachWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.AMOUNT.getName()));

        }else{
            edtExpectedDateToReachWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName())){

            edtSampleIdWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName()));

        }else{
            edtSampleIdWrap.setErrorEnabled(false);
        }

        if(invalidFields.containsKey(OrderUnrefRequest.Fields.EXPECTED_DATE.getName())){

            edtExpectedDateWrap.setError(invalidFields.get(OrderUnrefRequest.Fields.EXPECTED_DATE.getName()));

        }else{
            edtExpectedDateWrap.setErrorEnabled(false);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e(RegisterSample.class.getName(),"Request Code: "+requestCode);

        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {

            onSelectFromGalleryResult(data);

        }
        else /*if (requestCode == CAMERA_PERMISSION_CODE) */{

            handleBarcodeScanResult(requestCode, resultCode, data);

        }

    }

    private void onSelectFromGalleryResult(Intent data) {

        if (data != null) {

            try {

                /* Get image file path */
                String realPath  =  ImageFilePath.getPath(this, data.getData());
                edtAttachment.setText(realPath);

                /* Compress the image*/
                File file = new File(realPath);
                Bitmap compressedImageBitmap =  new Compressor(RegisterSample.this).compressToBitmap(file);

                bitmapFormData = compressedImageBitmap;

                //Attempt to get the Image URI
                Uri uri = ImageFilePath.getImageUri(RegisterSample.this, bitmapFormData, true);
                formData = new FormData(uri,file);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//        Picasso.get().load(getImageUri(this, bitmapObj!!)).resize(image.width,image.height).centerCrop().into(image);
//        image.setImageBitmap(bitmapObj);

    }

    private void handleBarcodeScanResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            String resultContents = result.getContents();

            if (resultContents == null) {

                String toast = "Cancelled from activity";
                Toast.makeText(RegisterSample.this, toast, Toast.LENGTH_SHORT).show();

            } else {

                String toast = "Scanned from activity: " + resultContents;
                Toast.makeText(RegisterSample.this, toast, Toast.LENGTH_SHORT).show();
                edtSampleId.setText(resultContents);
            }

            // At this point we may or may not have a reference to the activity

        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void navigateToPhoneNumberRequestPage() {

        OtpVerificationData otpVerificationData = new OtpVerificationData();
        otpVerificationData.setNextActivity(EnumModules.ORDER_HISTORY_UNREF.getMod());

        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_OTP_VERIFICATION,otpVerificationData.toGson(otpVerificationData));

        Intent intent = new Intent(RegisterSample.this, PhoneNumberRequest.class);
        intent.putExtras(bundle);
        startActivity(intent);

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
            case CAMERA_PERMISSION_CODE: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    IntentIntegrator integrator = new IntentIntegrator(RegisterSample.this);
                    integrator.initiateScan();

                } else {
                    Toast.makeText(RegisterSample.this, "We cannot proceed to read a QR Code without the permission", Toast.LENGTH_SHORT).show();
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

    private void btnScanCodeClicked() {
        scanQRCode();
    }

    public void scanQRCode() {

        if (!isCameraPermissionGranted()) {
            requestCameraPermission();
            return;
        }

        IntentIntegrator integrator = new IntentIntegrator(RegisterSample.this);
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


    private void btnSubmitSampleClicked() {

        SampleRegistrationReq req = getSampleRegistrationReq();

        /*First clear existing errors*/
        clearErrors();
        //presenter.validatePaymentRequest(request);

        presenter.registerSample(req, RegisterSample.this);

    }

    private SampleRegistrationReq getSampleRegistrationReq() {

        SampleRegistrationReq req = new SampleRegistrationReq();

        String initialSampleDate = edtSampleTakingDate.getText().toString();
        String finalDestinationDate = edtExpectedDateToReach.getText().toString();
        String barcode = edtSampleId.getText().toString();

        String selectedFacility = SpinnerHandler.spinnerValue(spinnerFacilities);
        int facility_code = DataManager.findFacilityIdByName(selectedFacility);

        String selectedDestination = SpinnerHandler.spinnerValue(spinnerDestinations);
        int destination = DataManager.findDestIdByName(selectedDestination);

        String selectedSpecimenType = SpinnerHandler.spinnerValue(spinnerSpecimen);
        int sample_type = DataManager.findSepcimenIdByName(selectedSpecimenType);

        String selectedDisease = SpinnerHandler.spinnerValue(spinnerDiseases);
        int disease = DataManager.findDiseaseIdByName(selectedDisease);

        String selectedTransporter = SpinnerHandler.spinnerValue(spinnerTransporter);
        int transporter = DataManager.findTransporterIdByName(selectedTransporter);
        String notes = edtClinicalNotes.getText().toString();
        FormData formData1 =  formData;

        req.initialSampleDate = initialSampleDate;
        req.finalDestinationDate = finalDestinationDate;
        req.facility_code = facility_code;
        req.barcode = barcode;
        req.destination = destination;
        req.sample_type = sample_type;
        req.disease = disease;
        req.transporter = transporter;
        req.notes = notes;
        req.formData = formData1;

        return req;

    }

    private String getSelectedSpinnerValue(MaterialSpinner spinner) {

        if(spinner != null && spinner.getSelectedItem() != null){

            return spinner.getSelectedItem().toString();

        }
        return ConstStrings.PROMPT_OPTION;
    }


    private void initializeViews() {

        btnSubmit = findViewById(R.id.btnSubmit);

        spinnerFacilities = findViewById(R.id.spinnerFacilities);
        spinnerDiseases = findViewById(R.id.spinnerDiseases);
        spinnerDestinations = findViewById(R.id.spinnerDestination);
        spinnerSpecimen = findViewById(R.id.spinnerSpecimen);
        spinnerTransporter = findViewById(R.id.spinnerTransporter);

        edtClinicalNotes = findViewById(R.id.edtClinicalNotes);

        edtSampleTakingDate = (EditText)findViewById(R.id.edtSampleTakingDate);
        edtExpectedDateToReach = (EditText)findViewById(R.id.edtExpectedDateToReach);
        edtAttachment = findViewById(R.id.edtAttachment);

        edtSampleId = (EditText)findViewById(R.id.edtSampleId);
        btnScanCode = (Button)findViewById(R.id.btnScanCode);

        edtSampleIdWrap = (TextInputLayout)findViewById(R.id.edtSampleIdWrap);
        edtClinicalNotesWrap = (TextInputLayout)findViewById(R.id.edtClinicalNotesWrap);
        edtExpectedDateToReachWrap = (TextInputLayout)findViewById(R.id.edtExpectedDateToReachWrap);
        edtExpectedDateWrap = (TextInputLayout)findViewById(R.id.edtExpectedDateWrap);


    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(RegisterSample.this.getResources().getString(R.string.module_register_sample));

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
        Dialogs.mdPrompt(RegisterSample.this, title, message, (dialog, which) -> presenter.loadAppData());

    }

    @Override
    public void displayDistributorRoles(List<String> distributorRoles) {

        rolesAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, distributorRoles);
        SpinnerHandler.populateSpinner(spinnerFacilities,rolesAdapter);

    }

    @Override
    public void displayPackagings(List<String> packagings) {

    }

    @Override
    public void displayPackagingsUnits(List<String> packagingUnits) {

        measureUnitsAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, packagingUnits);
//        SpinnerHandler.populateSpinner(txvUnitOfMeasure,measureUnitsAdapter);

    }

    @Override
    public void displayCementTypes(List<String> cementTypes) {

        cementTypesAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, cementTypes);
        SpinnerHandler.populateSpinner(spinnerDiseases,cementTypesAdapter);

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

        EditText[] editTexts = {edtExpectedDateToReach};
        ViewHandler.clearErrors(editTexts);

    }

    @Override
    public void displayMessageDialog(String message) {
        Dialogs.mdConfirm(message,RegisterSample.this);
    }

    @Override
    public void displayPaymentSuccessfulMessage(PaymentUnreferencedResponse response) {

        //todo confirm response sent from server
        clearFields();

        String message = presenter.getPaymentCompletedMessage(response);
        Dialogs.mdConfirm(message,RegisterSample.this);

    }

    @Override
    public void setUnitOfMeasureSelection(String unitOfMeasure) {


    }

    @Override
    public void displayTotalAmount(String amount) {

    }

    @Override
    public void confirmPayment(OrderUnrefRequest request) {

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
        new DialogProgressBar().showDialog(RegisterSample.this,response.getTransactionNo());

    }

    @Override
    public void displayRegions(List<String> regions) {

        spinnerRegionsAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, regions);
        SpinnerHandler.populateSpinner(spinnerDestinations, spinnerRegionsAdapter);

    }

    @Override
    public void displayDiseases(List<StDisease> diseases) {

        List<String> diseaseNames = new ArrayList<>();
        for (StDisease stDisease : diseases ) {
            diseaseNames.add(stDisease.name);
        }

        diseasesAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, diseaseNames);
        SpinnerHandler.populateSpinner(spinnerDiseases, diseasesAdapter);
    }

    @Override
    public void displayDestinations(List<StDestination> destinations) {

        List<String> names = new ArrayList<>();
        for (StDestination destination : destinations ) {
            names.add(destination.name);
        }
        destinationsAdapters = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerDestinations, destinationsAdapters);

    }

    @Override
    public void displayHealthFacilities(List<StFacility> health_facilities) {

        List<String> names = new ArrayList<>();
        for (StFacility item : health_facilities ) {
            names.add(item.name);
        }
        facilitiesAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerFacilities, facilitiesAdapter);
    }

    @Override
    public void displaySpecimen(List<StSpecimen> specimenList) {

        List<String> names = new ArrayList<>();
        for (StSpecimen item : specimenList ) {
            names.add(item.name);
        }
        specimenAdapter = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerSpecimen, specimenAdapter);
    }

    @Override
    public void displayTransporters(List<StTransporter> transporters) {

        List<String> names = new ArrayList<>();
        for (StTransporter item : transporters ) {
            names.add(item.name);
        }
        transportersAdapters = new ArrayAdapter<>(RegisterSample.this, android.R.layout.simple_spinner_item, names);
        SpinnerHandler.populateSpinner(spinnerTransporter, transportersAdapters);
    }

    private void clearErrors() {

        EditText[] edts = {edtClinicalNotes,edtSampleId,
                edtSampleTakingDate,edtExpectedDateToReach};
        TextInputLayout[] edtWraps = {edtClinicalNotesWrap,
                edtExpectedDateToReachWrap,edtExpectedDateWrap, edtSampleIdWrap};

        ViewHandler.clearErrors(edts);
        ViewHandler.clearErrors(edtWraps);

    }

    @Override
    public void clearFields() {

        EditText[] edts = {edtClinicalNotes,edtSampleId,
                edtSampleTakingDate,edtExpectedDateToReach,edtAttachment};
        MaterialSpinner[] spinners = {spinnerFacilities, spinnerDiseases, spinnerDestinations,spinnerSpecimen,spinnerTransporter};

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

        dialog = Dialogs.mdProgressDialog(RegisterSample.this,message);
        dialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int spinnerId = parent.getId();

        /*if(spinnerId == R.id.spinnerDiseases){

            if(spinnerDiseases == null || parent.getItemAtPosition(position) == null){
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


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private TextWatcher quantityTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {        }
    };


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

    }
}
