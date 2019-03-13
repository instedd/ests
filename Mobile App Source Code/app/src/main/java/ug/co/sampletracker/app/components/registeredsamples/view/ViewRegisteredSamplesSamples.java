package ug.co.sampletracker.app.components.registeredsamples.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.RegisteredSamplesAdapter;
import ug.co.sampletracker.app.components.dashboard.DashboardPage;
import ug.co.sampletracker.app.components.otp.otprequest.PhoneNumberRequest;
import ug.co.sampletracker.app.components.registeredsamples.view.dialog.DialogCustomStatement;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.OtpVerificationData;
import ug.co.sampletracker.app.models.StRegisteredSamplePojo;
import ug.co.sampletracker.app.models.requests.StatementRequest;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.EnumModules;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

public class ViewRegisteredSamplesSamples extends AppCompatActivity implements ViewRegisteredSamplesView, RecyclerClickListeners,
        DialogCustomStatement.StatementCustomizationListener {

    private Toolbar toolbar;
    private ViewRegisteredSamplesPresenter presenter;
    private ProgressDialog dialog;
    private SwipeRefreshLayout swipeToRefresh;
    private PreferencesDb preferencesDb;
    private final int SMS_PERMISSION_CODE  =1245;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_statement);

        initialize();

    }

    private void initialize() {

        initializeViews();
        initializeDependencies();
        setUpToolbar();
        DrawerUtil.getDrawer(this,toolbar,preferencesDb);

        loadRegisteredSamples();

    }

    private void showPermissionDeniedMsgAndRedirectToDashboard() {

        Dialogs.toast(ViewRegisteredSamplesSamples.this,EnumErrors.PERMISSION_DENIED.getErr());
        startActivity(new Intent(ViewRegisteredSamplesSamples.this,DashboardPage.class));
        finish();

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

                    showPermissionDeniedMsgAndRedirectToDashboard();

                }
                break;
            }
        }
    }

    private void navigateToPhoneNumberRequestPage() {

        OtpVerificationData otpVerificationData = new OtpVerificationData();
        otpVerificationData.setNextActivity(EnumModules.MINI_STATEMENT_UNREF.getMod());

        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_OTP_VERIFICATION,otpVerificationData.toGson(otpVerificationData));

        Intent intent = new Intent(ViewRegisteredSamplesSamples.this, PhoneNumberRequest.class);
        intent.putExtras(bundle);
        startActivity(intent);

        finish();

    }

    private void loadRegisteredSamples() {
        presenter.loadRegisteredSamples();
    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(ViewRegisteredSamplesSamples.this);
        ViewRegisteredSamplesInteractor interactor = new ViewRegisteredSamplesInteractorImpl();
        presenter = new ViewRegisteredSamplesPresenter(this,interactor);
        presenter.setPreferencesDb(preferencesDb);

    }

    private void initializeViews() {

        FloatingActionButton fab = findViewById(R.id.fabCustomizeStatement);
        fab.setOnClickListener(view -> showStatementCustomizationDialog());

        swipeToRefresh = findViewById(R.id.swipeToRefresh);
        swipeToRefresh.setOnRefreshListener(() -> presenter.loadRegisteredSamples());

    }

    private void showStatementCustomizationDialog() {

        DialogCustomStatement dialog = new DialogCustomStatement(ViewRegisteredSamplesSamples.this,
                preferencesDb.customerReferenceNoOtherCustomer(),this);
        dialog.showDialog();

    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(ViewRegisteredSamplesSamples.this.getResources().getString(R.string.module_registered_samples));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    @Override
    public void displayRegisteredSamples(List<StRegisteredSamplePojo> samples) {

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMiniStatement);

        RegisteredSamplesAdapter adapter = new RegisteredSamplesAdapter(samples, ViewRegisteredSamplesSamples.this);
        adapter.setRecyclerClickListeners(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ViewRegisteredSamplesSamples.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        if(samples.isEmpty()){
            toggleVisibilityOfNoStatementMessageCard(true);
            displayMsgErr("No samples found");
            return;
        }

        toggleVisibilityOfNoStatementMessageCard(false);

    }

    private void toggleVisibilityOfNoStatementMessageCard(boolean visibility) {

        CardView cardViewStatementNoItems = findViewById(R.id.cardViewStatementNoItems);

        if(visibility){
            cardViewStatementNoItems.setVisibility(View.VISIBLE);
            return;
        }
        cardViewStatementNoItems.setVisibility(View.GONE);

    }

    @Override
    public void displayMsgErr(String message) {

        toggleVisibilityOfNoStatementMessageCard(true);
        Dialogs.toast(ViewRegisteredSamplesSamples.this,message);

    }

    @Override
    public void stopProgressDialog() {
        swipeToRefresh.setRefreshing(false);
    }

    @Override
    public void startProgressDialog() {
        Dialogs.toast(ViewRegisteredSamplesSamples.this,"Loading registered samples please wait");
        swipeToRefresh.setRefreshing(true);
    }

    @Override
    public void displayMessageDialog(String message) {
        Dialogs.mdConfirm(message, ViewRegisteredSamplesSamples.this);
    }

    @Override
    public void longRecyclerItemClick(View view, int position) {

    }

    @Override
    public void clickRecyclerItemClick(View view, int position) {

    }

    @Override
    public void loadStatementBasedOnCustomization(StatementRequest request) {
        presenter.loadRegisteredSamples();
    }
}
