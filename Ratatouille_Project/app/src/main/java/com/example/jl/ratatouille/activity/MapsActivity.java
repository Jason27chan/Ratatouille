package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.DataService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jav on 10/18/2017.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<Rat> ratList = new ArrayList<>();
    private BitmapDescriptor rat_icon;
    private ProgressBar progressBar;

    private static final int ADD_ACTIVITY_REQUEST = 0;
    private static final int FILTER_ACTIVITY_REQUEST = 1;


    /**
     * adds the markers
     */
    private void updateMap() {
        ratList = DataService.getSharedRats(this);
        mMap.clear();
        if (ratList != null) {
            for (Rat r : ratList) {
                if (r.getLatitude() != null && r.getLongitude() != null) {
                    LatLng latlng = new LatLng(Double.parseDouble(r.getLatitude()), r.getLongitude());
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latlng));
                    marker.setTag(r);
                } else {
                    //add to error log
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(CheckGooglePlayServices()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            //google play services error
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
        //LatLngBounds nyBounds = new LatLngBounds(new LatLng(33.771403, -84.407349), new LatLng(33.781547, -84.390801));
        LatLng ny = new LatLng(40.7128, -74.0060);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ny, 11));

        updateMap();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Rat rat = (Rat) marker.getTag(); //retrieve Rat object from the marker
                Intent intent = new Intent(MapsActivity.this, ViewActivity.class);
                intent.putExtra("user", getIntent().getStringExtra("user"));
                intent.putExtra("rat", rat); //send rat to detail view
                startActivity(intent);
                return false;
            }
        });

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
     * sets up navigation bar at the bottom
     */
    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation_maps);
        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                break;
                            case R.id.action_list:
                                startActivity(new Intent(MapsActivity.this, ListActivity.class));
                                break;
                            case R.id.action_graph:
                                startActivity(new Intent(MapsActivity.this, GraphActivity.class));
                                break;
                            case R.id.action_settings:
                                startActivity(new Intent(MapsActivity.this, SettingsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

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
     * sets up the buttons on the maps activity
     */
    private void setupButtons() {
        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat_maps);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(myIntent, ADD_ACTIVITY_REQUEST);
            }
        });

        final Button filterBtn = findViewById(R.id.map_filter);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilterActivity.class);
                startActivityForResult(intent, FILTER_ACTIVITY_REQUEST);
            }
        });
    }

}
