package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.DataService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import net.grandcentrix.tray.AppPreferences;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.example.jl.ratatouille.activity.AddActivity.ADD_ACTIVITY_REQUEST;
import static com.example.jl.ratatouille.activity.FilterActivity.FILTER_ACTIVITY_REQUEST;
import static com.example.jl.ratatouille.service.DataService.SHARED_RATS;

/**
 * Represents the Map and the data that is shows
 *
 * Created by jav on 10/18/2017.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<Rat> ratList = new ArrayList<>();

    private static final String TAG = "MapsActivity";

    /**
     * Updates the markers on the map to display the sightings
     * saved in shared preferences. (Public for testing purposes)
     *
     * @return a List of Rats added to the GoogleMap (for testing purposes)
     */
    public List<Rat> updateMap() {
        ratList = DataService.getSharedRats(this);
        List<Rat> mapList = new ArrayList<>();
        mMap.clear();
        if (ratList != null) {
            for (Rat r : ratList) {
                if (r.getLatitude() != null && r.getLongitude() != null) {
                    LatLng latlng = new LatLng(
                            Double.parseDouble(r.getLatitude()), r.getLongitude());
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latlng));
                    marker.setTag(r);
                    mapList.add(r);
                } else {
                    Log.v(TAG, "sighting location unavailable");
                }
            }
        }
        return mapList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final AppPreferences prefs = new AppPreferences(getApplicationContext());
        Gson gson = new Gson();
        Rat[] rats = new Rat[1];
        int year = 2015;
        int month = 12;
        int day = 12;
        String loc_type = "1";
        int zip = 1;
        String address = "1";
        String city = "1";
        String borough = "1";
        String latitude = "1";
        double longitude = 1.0;
        rats[0] = new Rat(new Date(year, month, day),
                loc_type, zip, address, city, borough, latitude, longitude);
        String jsonRats = gson.toJson(rats);
        prefs.put(SHARED_RATS, jsonRats);

        if(CheckGooglePlayServices()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            Log.v(TAG, "google play services unavailable");
        }
        setupNavigation();
        setupButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMap != null) {
            updateMap();
        }
    }

    /**.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //rat_icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_rat);
        //LatLngBounds nyBounds = new LatLngBounds(
        // new LatLng(33.771403, -84.407349), new LatLng(33.781547, -84.390801));
        double v = 40.7128;
        double v1 = -74.0060;
        LatLng ny = new LatLng(v, v1);
        int v2 = 11;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ny, v2));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Rat rat = (Rat) marker.getTag();
                Intent intent = new Intent(MapsActivity.this, ViewActivity.class);
                intent.putExtra("user", getIntent().getStringExtra("user"));
                intent.putExtra("rat", rat);
                startActivity(intent);
                return false;
            }
        });
        updateMap();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                updateMap();
            }
        }
        if (requestCode == FILTER_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                updateMap();
            }
        }
    }

    /**
     * Checks to see if Google Play Services are available.
     * @return true of Google Play Services are available, false if otherwise
     */
    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    /**
     * Performs setup for navigation bar.
     */
    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation_maps);
        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_list:
                                startActivity(new Intent(
                                        MapsActivity.this, ListActivity.class));
                                break;
                            case R.id.action_graph:
                                startActivity(new Intent(
                                        MapsActivity.this, GraphActivity.class));
                                break;
                            case R.id.action_settings:
                                startActivity(new Intent(
                                        MapsActivity.this, SettingsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

    /**
     * Performs setup for add button and filter button.
     */
    private void setupButtons() {
        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat_maps);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(myIntent, ADD_ACTIVITY_REQUEST);
            }
        });

        final FloatingActionButton filterBtn = findViewById(R.id.map_filter);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilterActivity.class);
                startActivityForResult(intent, FILTER_ACTIVITY_REQUEST);
            }
        });
    }

}
