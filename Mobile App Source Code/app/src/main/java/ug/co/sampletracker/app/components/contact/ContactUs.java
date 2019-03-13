package ug.co.sampletracker.app.components.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.contact.offices.Offices;
import ug.co.sampletracker.app.components.locations.maps.LocationMap;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.Dealer;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.models.OfficeLocation;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.CustomView;

public class ContactUs extends AppCompatActivity implements CustomView {

    private ImageButton btnEmail;
    private ImageButton btnCall;
    private ImageButton btnLocation;
    OfficeLocation hqLocation = OfficeLocation.headOfficeLocation();
    private Toolbar toolbar;
    private PreferencesDb preferencesDb;
    private TextView btnTxvViewOffices;
    private final int PHONE_CALL_PERMISSION = 1121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        initialize();

    }

    private void initialize() {

        initializeViews();
        initializeDependencies();
        setUpToolbar();
        attachListeners();
        DrawerUtil.getDrawer(this, toolbar, preferencesDb);

    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(ContactUs.this);

    }

    private void attachListeners() {

        btnEmail.setOnClickListener(btn -> openEmailApp());
        btnCall.setOnClickListener(btn -> openCallApp());
        btnLocation.setOnClickListener(btn -> openMapsPage());

    }

    private void openMapsPage() {

        Dealer data = new Dealer();

        data.setDealerNumber(hqLocation.getPhone());
        data.setDealerName(hqLocation.getOfficeName());
        data.setLocation(hqLocation.getPhysicalAddress());
        data.setLatitude(hqLocation.getLatitude());
        data.setLongitude(hqLocation.getLongitude());
        Intent intent = new Intent(ContactUs.this, LocationMap.class);
        intent.putExtra(ConstStrings.BUNDLE_DEALER, Dealer.toGson(data));
        startActivity(intent);

    }

    private void openCallApp() {

        if (isPhoneCallPermissionGranted()) {
            makeCall();
        }else{

            String message = "Hima Cement App will now request permission to make a phone call on your device.";
            Dialogs.mdPrompt(ContactUs.this,"Phone Call Permission", message, (dialog, which) -> {
                requestPhoneCallPermissionPermission();
            });

        }

    }

    private void makeCall() {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + hqLocation.getPhone()));
        Dialogs.toast(ContactUs.this, "Calling " + hqLocation.getPhone());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);

    }

    private void openEmailApp() {

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { hqLocation.getEmail() });
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Hima Cement Inquiry");
        startActivity(sendIntent);

    }

    private void initializeViews() {

        btnEmail = (ImageButton) findViewById(R.id.btnEmail);
        btnCall = (ImageButton) findViewById(R.id.btnCall);
        btnLocation = (ImageButton) findViewById(R.id.btnLocation);
        btnTxvViewOffices = (TextView) findViewById(R.id.btnTxvViewOffices);

    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(ContactUs.this.getResources().getString(R.string.m_service_name));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    /**
     * Check if we have PHONE permission
     */
    public boolean isPhoneCallPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request runtime PHONE permission
     */
    private void requestPhoneCallPermissionPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            // You may display a non-blocking explanation here, read more in the documentation:
            // https://developer.android.com/training/permissions/requesting.html
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_PERMISSION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PHONE_CALL_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    makeCall();

                } else {
                    displayError("We cannot proceed to make a call without the permission");
                }
                break;
            }
        }

    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void displayError(String error) {
        Dialogs.mdConfirm(error,ContactUs.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,ContactUs.this);
    }

    public void btnGoToRegionalOffices(View view) {
        startActivity(new Intent(ContactUs.this, Offices.class));
    }
}
