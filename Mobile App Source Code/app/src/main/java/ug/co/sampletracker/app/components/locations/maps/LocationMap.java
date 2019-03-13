package ug.co.sampletracker.app.components.locations.maps;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.Dealer;
import ug.co.sampletracker.app.utils.constants.ConstStrings;

public class LocationMap extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;
    private Dealer dealer = new Dealer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_location_map);

        initialize();
        getDealerDetails();

    }

    private void getDealerDetails() {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String dealerGson = bundle.getString(ConstStrings.BUNDLE_DEALER);
            dealer = Dealer.fromGson(dealerGson);
        }

    }

    private void initialize() {

        setUpToolbar();
        initializeMap();

    }

    private void setUpToolbar() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Location");

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

    }


    private void initializeMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng sydney = new LatLng(dealer.getLatitude(), dealer.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title(dealer.getDealerName()+"\n"+dealer.getLocation()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMinZoomPreference(15);

    }

    public void btnDoneClick(View view) {

        onBackPressed();
        finish();

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
