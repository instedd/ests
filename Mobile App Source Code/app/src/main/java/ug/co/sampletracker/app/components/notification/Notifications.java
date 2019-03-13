package ug.co.sampletracker.app.components.notification;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.NotificationsAdapter;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.StNotification;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumExtendMenuLabels;
import ug.co.sampletracker.app.utils.constants.EnumRequestByOption;
import ug.co.sampletracker.app.utils.general.DateHandler;
import ug.co.sampletracker.app.utils.general.DatePickerListener;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.SpinnerHandler;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

public class Notifications extends AppCompatActivity implements NotificationsView, RecyclerClickListeners {

    private Toolbar toolbar;
    private MaterialDialog dialog;
    private EditText edtFromDate;
    private EditText edtToDate;
    private EditText edtInvoiceNo;
    private MaterialSpinner spinnerRequestBy;
    private SwipeRefreshLayout swipeToRefresh;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveries);

        initialize();

    }

    private void initialize() {

        initializeViews();
        setUpToolbar();
        attachListeners();
        loadData();

    }

    private void loadData() {

       if(swipeToRefresh != null){
           swipeToRefresh.setRefreshing(true);
       }

        List<StNotification> notifications = DataManager.appData.getNotifications();

        NotificationsAdapter adapter = new NotificationsAdapter(notifications,Notifications.this);
        adapter.setRecyclerClickListeners(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Notifications.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        if(swipeToRefresh != null && swipeToRefresh.isRefreshing()){
            swipeToRefresh.setRefreshing(false);
        }

    }

    private void attachListeners() {

        swipeToRefresh = findViewById(R.id.swipeToRefresh);

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();

            }
        });


        spinnerRequestBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String requestBy = SpinnerHandler.spinnerValue(spinnerRequestBy);

                if(!requestBy.equalsIgnoreCase(EnumRequestByOption.DATE.getVal())  ){

                    toggleVisibilityOfDateFields(false);
                }else{
                    toggleVisibilityOfDateFields(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        edtFromDate.setOnClickListener(btn->showDatePicker(edtFromDate));
        edtToDate.setOnClickListener(btn->showDatePicker(edtToDate));

    }

    private void toggleVisibilityOfDateFields(boolean visible){

        LinearLayout linearLayout = findViewById(R.id.containerDates);

        if(visible){
            linearLayout.setVisibility(View.VISIBLE);
            edtInvoiceNo.setVisibility(View.GONE);
            edtInvoiceNo.requestFocus();
            return;
        }

        linearLayout.setVisibility(View.GONE);
        edtInvoiceNo.setVisibility(View.VISIBLE);

    }

    private void showDatePicker(EditText editText) {

        Dialogs.showDatePicker(Notifications.this, new DatePickerListener(editText));

    }

    private void initializeViews() {

        recyclerView = findViewById(R.id.recyclerView);

        edtFromDate = findViewById(R.id.edtFromDate);
        edtToDate = findViewById(R.id.edtToDate);
        edtInvoiceNo = findViewById(R.id.edtInvoiceNo);

        spinnerRequestBy = (MaterialSpinner) findViewById(R.id.spinnerRequestBy);

        edtFromDate.setText(new DateHandler().getHumanReadableDate(DateHandler.dateTimeNow(),ConstStrings.DATE_FORMAT_TXN_SHORT));
        edtToDate.setText(new DateHandler().getHumanReadableDate(DateHandler.dateTimeNow(),ConstStrings.DATE_FORMAT_TXN_SHORT));

    }

    private void setUpToolbar() {

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(EnumExtendMenuLabels.NOTIFICATION.getItem());

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        PreferencesDb preferencesDb = new PreferencesDb(Notifications.this);
        DrawerUtil.getDrawer(this, toolbar, preferencesDb);

    }

    public void btnSearchClick(View view) {

    }


    @Override
    public void displayValidationErrors(HashMap<String, String> invalidFields) {

    }

    @Override
    public void showDialog(String title) {

        dialog = Dialogs.mdProgressDialog(Notifications.this, title);
        dialog.show();

    }

    @Override
    public void closeDialog() {

        if (dialog != null) {
            dialog.dismiss();
        }

        if(swipeToRefresh != null && swipeToRefresh.isRefreshing()){
            swipeToRefresh.setRefreshing(false);
        }

    }

    @Override
    public void displayError(String error) {
        Dialogs.mdConfirm(error,Notifications.this);
    }

    @Override
    public void displayMessage(String message) {

        Dialogs.mdConfirm(message,Notifications.this);

    }


    @Override
    public void longRecyclerItemClick(View view, int position) {


    }

    @Override
    public void clickRecyclerItemClick(View view, int position) {


    }
}
