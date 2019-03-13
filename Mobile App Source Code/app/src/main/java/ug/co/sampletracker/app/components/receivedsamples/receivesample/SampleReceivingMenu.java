package ug.co.sampletracker.app.components.receivedsamples.receivesample;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.SelectionMenuAdapter;
import ug.co.sampletracker.app.components.dashboard.DashboardMenuHandler;
import ug.co.sampletracker.app.components.receivedsamples.view.ViewReceivedSamples;
import ug.co.sampletracker.app.components.otp.otprequest.PhoneNumberRequest;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.OtpVerificationData;
import ug.co.sampletracker.app.models.SelectionMenuItem;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumModules;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.CustomView;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

public class SampleReceivingMenu extends AppCompatActivity implements CustomView {

    private static final int SMS_PERMISSION_CODE = 1203;
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    private SelectionMenuAdapter adapter;
    private List<SelectionMenuItem> menuItems = new ArrayList<>();
    private PreferencesDb preferencesDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample_receiving_menu);
        initialize();

    }

    private void initialize() {

        initializeViews();
        initializeDependencies();
        loadData();
        DrawerUtil.getDrawer(this, toolbar, preferencesDb);

    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(SampleReceivingMenu.this);

    }

    private void loadData() {

        menuItems = getMenuItemsReceiving();

        adapter = new SelectionMenuAdapter(menuItems,SampleReceivingMenu.this);
        adapter.setRecyclerClickListeners(recyclerviewClickListeners);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SampleReceivingMenu.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
        recyclerview.setHasFixedSize(true);

    }

    private RecyclerClickListeners recyclerviewClickListeners = new RecyclerClickListeners() {

        @Override
        public void longRecyclerItemClick(View view, int position) {


        }

        @Override
        public void clickRecyclerItemClick(View view, int position) {

            SelectionMenuItem menuItem = menuItems.get(position);
            int optionIcon = menuItem.getIcon();

            PreferencesDb preferencesDb = new PreferencesDb(SampleReceivingMenu.this);
            boolean authDealer = preferencesDb.isAuthDealer();

            if(optionIcon == R.drawable.ic_add_option){

                NavHandler.navToActivityAnimated(SampleReceivingMenu.this,ReceiveSample.class);

            }else if(optionIcon == R.drawable.ic_view){

//                displayError("Operation not supported yet");

                NavHandler.navToActivityAnimated(SampleReceivingMenu.this,ViewReceivedSamples.class);

                /*
                if(authDealer){
                    NavHandler.navToActivityAnimated(SampleReceivingMenu.this,ViewReceivedSamples.class);
                }else {
                    handleOpenOtherCustomersPaymentHistoryPage(preferencesDb);
                } */

            }

        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case SMS_PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    navigateToPhoneNumberRequestPage();

                } else {
                    displayMessage("We cannot proceed to payment history without the permission");
                }
                break;
            }
        }
    }

    private void navigateToPhoneNumberRequestPage() {

        OtpVerificationData otpVerificationData = new OtpVerificationData();
        otpVerificationData.setNextActivity(EnumModules.ORDER_HISTORY_UNREF.getMod());

        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_OTP_VERIFICATION,otpVerificationData.toGson(otpVerificationData));

        NavHandler.navToActivityAnimatedWithBundle(SampleReceivingMenu.this,PhoneNumberRequest.class,bundle);

    }


    private List<SelectionMenuItem> getMenuItemsReceiving() {

        List<SelectionMenuItem> menuItems = new ArrayList<>();

        String receiveSample = DashboardMenuHandler.RECEIVE_SAMPLE;
        String viewReceivedSamples = DashboardMenuHandler.VIEW_RECEIVED_SAMPLES;

        SelectionMenuItem menuItemAdd = new SelectionMenuItem(receiveSample,R.drawable.ic_add_option);
        SelectionMenuItem menuItemView = new SelectionMenuItem(viewReceivedSamples,R.drawable.ic_view);

        menuItems.add(menuItemAdd);
        menuItems.add(menuItemView);

        return menuItems;

    }

    private void initializeViews() {

        setUpToolbar();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

    }

    private void setUpToolbar() {

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        String title = getResources().getString(R.string.module_receive_sample);
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_dialog_title_only, menu);
        return true;//super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Dialogs.mdConfirm(error,SampleReceivingMenu.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,SampleReceivingMenu.this);
    }
}
