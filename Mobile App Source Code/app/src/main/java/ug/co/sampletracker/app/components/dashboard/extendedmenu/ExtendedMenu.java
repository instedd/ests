package ug.co.sampletracker.app.components.dashboard.extendedmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.adapters.SelectionMenuAdapter;
import ug.co.sampletracker.app.components.dashboard.DashboardMenuHandler;
import ug.co.sampletracker.app.components.notification.Notifications;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.SelectionMenuItem;
import ug.co.sampletracker.app.utils.DrawerUtil;
import ug.co.sampletracker.app.utils.constants.EnumExtendMenuLabels;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

public class ExtendedMenu extends AppCompatActivity {

    private Toolbar toolbar;
    private SelectionMenuAdapter adapter;
    private List<SelectionMenuItem> menuItems;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_menu);

        initialize();
        loadMenu();

    }

    private void loadMenu() {

        menuItems = DashboardMenuHandler.getExtendedMenuItems();

        adapter = new SelectionMenuAdapter(menuItems,ExtendedMenu.this);
        adapter.setRecyclerClickListeners(recyclerviewClickListeners);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ExtendedMenu.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    RecyclerClickListeners recyclerviewClickListeners = new RecyclerClickListeners() {
        @Override
        public void longRecyclerItemClick(View view, int position) {

        }

        @Override
        public void clickRecyclerItemClick(View view, int position) {

            SelectionMenuItem menuItem = menuItems.get(position);
            String itemLabel = menuItem.getLabel();

            handleMenuSelection(itemLabel);

        }
    };

    private void handleMenuSelection(String menuItem) {

        Intent intent = null;

        if(menuItem.equalsIgnoreCase(EnumExtendMenuLabels.DELIVERIES.getItem())){

            Dialogs.toast(ExtendedMenu.this,menuItem);
            intent = new Intent(ExtendedMenu.this, Notifications.class);

        }else{
            Dialogs.toast(ExtendedMenu.this,"Selected option not supported, please try again");
            return;
        }

        if(intent != null){
            startActivity(intent);
        }

    }

    private void initialize() {

        setUpToolbar();
        initializeViews();

        PreferencesDb preferencesDb = new PreferencesDb(ExtendedMenu.this);
        DrawerUtil.getDrawer(this, toolbar, preferencesDb);

    }

    private void initializeViews() {

        recyclerView = findViewById(R.id.recyclerview);

    }

    private void setUpToolbar() {

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.module_dealer_reports));

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

    }

}
