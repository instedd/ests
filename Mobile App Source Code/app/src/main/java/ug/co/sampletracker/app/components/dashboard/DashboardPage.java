package ug.co.sampletracker.app.components.dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.DashboardMenuAdapter;
import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.components.registeredsamples.registersample.RegisterSampleInteractor;
import ug.co.sampletracker.app.components.registeredsamples.registersample.RegisterSampleInteractorImpl;
import ug.co.sampletracker.app.components.registeredsamples.registersample.RegisterSamplePresenter;
import ug.co.sampletracker.app.components.registeredsamples.registersample.RegisterSampleView;
import ug.co.sampletracker.app.components.registeredsamples.view.ViewRegisteredSamplesSamples;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.DashboardMenuRow;
import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;
import ug.co.sampletracker.app.utils.receivers.SmsBroadcastReceiver;

public class DashboardPage extends AppCompatActivity implements
        RegisterSampleView, SmsBroadcastReceiver.Listener, RecyclerClickListeners {

    private PreferencesDb preferencesDb;
    private RegisterSamplePresenter presenterPaymentsUnreferenced;
    private MaterialDialog dialog;
    private final int SMS_PERMISSION_CODE = 1200;
    private SmsBroadcastReceiver smsBroadcastReceiver;
    private Menu menu;

    private RecyclerView recyclerview;
    private List<DashboardMenuRow> menuRowList = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout_a);

        initialize();

    }

    private void initialize() {

        setUpToolbar();
        initializeViews();
        initializeDependencies();
        attacheListeners();

        DrawerUtil.getDrawer(this, toolbar, preferencesDb);

        customiseDashboard();


    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.m_service_name));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

    }

    private void customiseDashboard() {

        loadDashboardMenuItems();

    }

    private void loadDashboardMenuItems() {

        List<DashboardMenuRow> menuRows = getDashboardMenuRows();

        DashboardMenuAdapter menuAdapter = new DashboardMenuAdapter(menuRows,DashboardPage.this);
        menuAdapter.setRecyclerClickListeners(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardPage.this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(menuAdapter);
        recyclerview.setHasFixedSize(true);

    }

    private List<DashboardMenuRow> getDashboardMenuRows() {

        menuRowList =  DashboardMenuHandler.getCustomDashboardMenu(preferencesDb.isAuthUser(),preferencesDb.isAuthDealer());
        return menuRowList;

    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(DashboardPage.this);

        RegisterSampleInteractor registerSampleInteractor = new RegisterSampleInteractorImpl();
        presenterPaymentsUnreferenced = new RegisterSamplePresenter(this, registerSampleInteractor);
        presenterPaymentsUnreferenced.setPreferencesDb(preferencesDb);
    }

    private void attacheListeners() {

    }

    private void initializeViews() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    public void onResume() {
        super.onResume();
        customiseDashboard();

        if(this.menu != null){

            this.menu.clear();
            this.onCreateOptionsMenu(this.menu);

        }
    }


    @Override
    public void displayMessageFailedToLoadAppData(String error) {

    }

    @Override
    public void displayDistributorRoles(List<String> distributorRoles) {

    }

    @Override
    public void stopProgressDialog() {

        dialog.dismiss();

    }

    @Override
    public void startProgressDialog(String message) {

        if(dialog != null){
            dialog.setTitle(message);
            if(!dialog.isShowing()){
                dialog.show();
            }
            return;
        }

        dialog = Dialogs.mdProgressDialog(DashboardPage.this,message);
        dialog.show();

    }

    @Override
    public void displayPackagings(List<String> packagings) {

    }

    @Override
    public void displayPackagingsUnits(List<String> packagingUnits) {

    }

    @Override
    public void displayCementTypes(List<String> cementTypes) {

    }

    @Override
    public void displayValidationErrors(List<String> invalidFields) {

    }

    @Override
    public void displayMessageDialog(String message) {
        Dialogs.mdConfirm(message,DashboardPage.this);
    }

    @Override
    public void displayPaymentSuccessfulMessage(PaymentUnreferencedResponse response) {

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

        stopProgressDialog();

        if(dialog != null && dialog.isShowing()){

            dialog.dismiss();

        }else{
        }

        NavHandler.navToActivityAnimated(DashboardPage.this,ViewRegisteredSamplesSamples.class);

    }

    @Override
    public void enabledVerifyOTPFields(boolean enabled) {
    }

    @Override
    public void displaySuccessfullPaymentsAndStartWaitingDialog(PaymentUnreferencedResponse response) {

    }

    @Override
    public void displayRegions(List<String> regions) {

    }

    @Override
    public void displayDiseases(List<StDisease> diseases) {

    }

    @Override
    public void displayDestinations(List<StDestination> destinations) {

    }

    @Override
    public void displayHealthFacilities(List<StFacility> health_facilities) {

    }

    @Override
    public void displaySpecimen(List<StSpecimen> specimen) {

    }

    @Override
    public void displayTransporters(List<StTransporter> transporters) {

    }

    @Override
    public void clearFields() {

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
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SMS_PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    displayMessageDialog("We cannot proceed to payment history with the permission");
                }
                return;
            }
        }
    }

    @Override
    public void onTextReceived(String text) {

        String code = text.replace(ConstStrings.OTP_SMS_START_TEXT,"").trim();
        unregisterReceiver(smsBroadcastReceiver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.menu = menu;
        this.menu.clear();

        if(preferencesDb.isAuthUser()){
            DashboardPage.this.getMenuInflater().inflate(R.menu.menu_main_logged_in, this.menu);
            return true;
        }

        DashboardPage.this.getMenuInflater().inflate(R.menu.menu_main_sign_in, this.menu);

        return super.onCreateOptionsMenu(this.menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            showLogoutConfirmationDialog();
            return true;
        }

        if (id == R.id.action_login) {

            NavHandler.navToActivityAnimated(DashboardPage.this,SignIn.class);
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void showLogoutConfirmationDialog() {

        Dialogs.mdPrompt(DashboardPage.this, "Confirm Action", "Are you sure you want to logout?",
                (dialog, which) -> new NavHandler(DashboardPage.this).logout());

    }

    @Override
    public void longRecyclerItemClick(View view, int position) {

    }

    @Override
    public void clickRecyclerItemClick(View view, int position) {

        if(menuRowList.isEmpty()){
            return;
        }

        DashboardMenuRow menuRow = menuRowList.get(position);
        Class<?> activity = view.getId() == R.id.btnLeftOption ? menuRow.getLeftMenuItem().getaClass() : menuRow.getRightMenuItem().getaClass();

        if(activity == DashboardPage.class){
            String moduleLabel = view.getId() == R.id.btnLeftOption ? menuRow.getLeftMenuItem().getLabel() : menuRow.getRightMenuItem().getLabel();
            displayMessageDialog(moduleLabel + " module is coming soon");
            return;
        }

        NavHandler.navToActivityAnimated(DashboardPage.this,activity);

    }
}
