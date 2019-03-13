package ug.co.sampletracker.app.components.contact.offices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.OfficesAdapter;
import ug.co.sampletracker.app.components.registeredsamples.registersample.RegisterSample;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.OfficeLocation;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

public class Offices extends AppCompatActivity implements OfficesView, RecyclerClickListeners {

    private RecyclerView recyclerViewDealers;
    private OfficesPresenter presenter;
    private Toolbar toolbar;
    private OfficesAdapter adapter;
    private List<OfficeLocation> officeLocationList = new ArrayList<>();
    private OrderUnrefRequest orderData = new OrderUnrefRequest();
    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offices);

        initialize();
        populateFields();
        attachListeners();

    }

    private void attachListeners() {

    }

    private void populateFields() {
        presenter.loadOffices();
    }

    private void initialize() {

        initializeViews();
        setUpToolbar();
        initializeDependencies();

    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Offices.this.getResources().getString(R.string.module_regional_offices));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    private void initializeDependencies() {

        PreferencesDb preferencesDb = new PreferencesDb(Offices.this);
        DbHandler dbHandler = new DbHandler();

        OfficesInteractor interactor = new OfficesInteractorImpl();
        presenter = new OfficesPresenter(this,interactor);
        presenter.setPreferencesDb(preferencesDb);
        presenter.setDbHandler(dbHandler);

    }

    private void initializeViews() {

        recyclerViewDealers = (RecyclerView)findViewById(R.id.recyclerViewOffices);

    }

    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {
        dialog = Dialogs.mdProgressDialog(Offices.this, title);
        dialog.show();
    }

    @Override
    public void closeDialog() {

        if(dialog != null){
            dialog.dismiss();
        }

    }

    @Override
    public void displayError(String error) {
        Dialogs.mdConfirm(error,Offices.this);
    }

    @Override
    public void displayMessage(String message) {
        Dialogs.mdConfirm(message,Offices.this);
    }

    @Override
    public void displayOfficeLocations(List<String> dealerLocations) {
    }

    @Override
    public void displayOffices(List<OfficeLocation> officeLocations) {

        if(officeLocations.isEmpty()){
            Dialogs.toast(Offices.this,"No office locations found");
        }

        officeLocationList = officeLocations;

        adapter = new OfficesAdapter(officeLocations, Offices.this);
        adapter.setRecyclerClickListeners(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Offices.this);
        recyclerViewDealers.setLayoutManager(layoutManager);
        recyclerViewDealers.setAdapter(adapter);
        recyclerViewDealers.setHasFixedSize(true);

    }

    @Override
    public void longRecyclerItemClick(View view, int position) {

    }

    @Override
    public void clickRecyclerItemClick(View view, int position) {

       /* Dealer dealer = officeLocationList.get(position);

        int id = view.getId();
        if(id == R.id.txvBtnViewLocation){

            Intent intent = new Intent(Offices.this, LocationMap.class);
            intent.putExtra(ConstStrings.BUNDLE_DEALER,Dealer.toGson(dealer));
            startActivity(intent);

        }else if(id == R.id.txvBtnChooseDealer){

            orderData.setDealerName(dealer.getDealerName());
            orderData.setDistributorNo(dealer.getDealerNumber());

            goToOrdersPage();

        }
*/
    }

    private void goToOrdersPage() {

        Intent intent = new Intent(Offices.this, RegisterSample.class);
        String data = OrderUnrefRequest.toGson(orderData);

        Log.e(Offices.class.getName(),data);

        Bundle bundle = new Bundle();
        bundle.putString(ConstStrings.BUNDLE_ORDER, data);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //goToOrdersPage();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
