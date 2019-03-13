package ug.co.sampletracker.app.components.receivedsamples.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Collections;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.ReceivedSamplesAdapter;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

public class ViewReceivedSamples extends AppCompatActivity implements ViewReceivedSamplesView, RecyclerClickListeners {

    private Toolbar toolbar;
    private ViewReceivedSamplesPresenter presenter;
    private ProgressDialog dialog;
    private SwipeRefreshLayout swipeToRefresh;
    private PreferencesDb preferencesDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        initialize();
        loadReceivedSamples();

    }

    private void loadReceivedSamples() {
        presenter.loadReceivedSamples();
    }

    private void initialize() {

        initializeViews();
        setUpToolbar();
        initializeDependencies();
        DrawerUtil.getDrawer(this,toolbar,preferencesDb);

    }

    private void initializeDependencies() {

        ViewReceivedSamplesInteractor interactor = new ViewReceivedSamplesInteractorImpl();
        preferencesDb = new PreferencesDb(ViewReceivedSamples.this);
        presenter = new ViewReceivedSamplesPresenter(this,interactor);
        presenter.setPreferencesDb(preferencesDb);

    }

    private void initializeViews() {

        swipeToRefresh = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        swipeToRefresh.setOnRefreshListener(() -> presenter.loadReceivedSamples());
    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(ViewReceivedSamples.this.getResources().getString(R.string.module_received_samples));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }

    @Override
    public void showProgressDialog() {
        Dialogs.toast(ViewReceivedSamples.this,"Loading samples please wait");
        swipeToRefresh.setRefreshing(true);
    }

    @Override
    public void stopProgressDialog() {
        swipeToRefresh.setRefreshing(false);
    }

    @Override
    public void displayMessage(String error) {

        toggleVisibilityOfNoOrdersPMessage(View.VISIBLE);
        Dialogs.toast(ViewReceivedSamples.this,error);
        displayReceivedSamples(Collections.emptyList());

    }

    @Override
    public void displayReceivedSamples(List<StReceivedSample> receivedSamples) {

        toggleVisibilityOfNoOrdersPMessage(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewOrders);

        ReceivedSamplesAdapter adapter = new ReceivedSamplesAdapter(receivedSamples,ViewReceivedSamples.this);
        adapter.setRecyclerClickListeners(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ViewReceivedSamples.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        if(receivedSamples.isEmpty()){
            toggleVisibilityOfNoOrdersPMessage(View.VISIBLE);
        }

    }

    @Override
    public void longRecyclerItemClick(View view, int position) {

    }

    @Override
    public void clickRecyclerItemClick(View view, int position) {

    }

    public void toggleVisibilityOfNoOrdersPMessage(int visibility){

        CardView cardView = findViewById(R.id.cardViewNoOrdersHolder);
        cardView.setVisibility(visibility);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.clear();
        getMenuInflater().inflate(R.menu.menu_search, menu);

        return true;
      //  return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {

            showSearchInputDialog();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void showSearchInputDialog() {

        String content = "Enter invoice number";
        String title = "Find Order";

        new MaterialDialog.Builder(this)
            .title(title)
            .content(content)
            .inputType(InputType.TYPE_CLASS_TEXT)
            .input(getString(R.string.hint_search), null, (dialog, input) -> {

                presenter.searchForOrder(input.toString());

            }).show();

    }

}
