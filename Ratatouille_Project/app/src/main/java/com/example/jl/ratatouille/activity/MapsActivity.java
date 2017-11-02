package com.example.jl.ratatouille.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.RecyclerViewAdapter;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jav on 10/18/2017.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private BitmapDescriptor rat_icon;
    private List<Rat> ratList = new ArrayList<>();
    boolean onReceived;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Rat[] rats = (Rat[]) intent.getParcelableArrayExtra(DataService.DATA_SERVICE_PAYLOAD);
            ratList = Arrays.asList(rats);
            if (ratList.isEmpty()) {
                //error log
            } else {
                addMarkers();
            }
        }
    };

    private void addMarkers() {
        mMap.clear();
        for (Rat r : ratList) {
            if (r.getLatitude() != null && r.getLongitude() != null) {
                LatLng latlng = new LatLng(Double.parseDouble(r.getLatitude()), r.getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions().position(latlng));
                marker.setTag(r);
            } else {
                //add to error log
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(CheckGooglePlayServices()) {
            LocalBroadcastManager.getInstance(getApplicationContext())
                    .registerReceiver(mBroadcastReceiver, new IntentFilter(DataService.DATA_SERVICE_MSG));
        }

        requestData();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupNavigation();

        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat_maps);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
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

    private void requestData() {
        Intent intent = new Intent(this, DataService.class);
        Map<String, String> options = new HashMap<>();
        options.put("date_start", "2017-08-23");
        options.put("date_end", "2017-08-23");
        intent.putExtra("options", (HashMap) options);
        startService(intent);
    }

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
    private void loadRatData(int start, int end) {
        new LoadDataTask().execute(R.raw.rat_sightings_trimmed, start, end);
    }

    private class LoadDataTask extends AsyncTask<Integer, Void, String> {
        private ProgressBar spinner;

        @Override
        protected void onPreExecute() {
            spinner = findViewById(R.id.progressBar_maps);
            spinner.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(Integer... fileName) {
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            for (Rat r : ratList) {
                LatLng latlng = new LatLng(r.getLatitude(), r.getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions().position(latlng));
                marker.setTag(r);
            }
            spinner.setVisibility(View.GONE);
        }
    }**/
}
