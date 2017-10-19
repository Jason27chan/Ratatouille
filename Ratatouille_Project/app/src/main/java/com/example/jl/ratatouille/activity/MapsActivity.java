package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.data.CSVFile;
import com.example.jl.ratatouille.model.Rat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jav on 10/18/2017.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private BitmapDescriptor rat_icon;
    private List<Rat> ratList = new ArrayList<>();
    private int startIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat_maps);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddRatActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    /**.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        loadRatData(startIndex, startIndex + 25);
        mMap = googleMap;
        //rat_icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_rat);
        //LatLngBounds nyBounds = new LatLngBounds(new LatLng(33.771403, -84.407349), new LatLng(33.781547, -84.390801));
        LatLng ny = new LatLng(40.7128, -74.0060);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ny, 12));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Rat rat = (Rat) marker.getTag(); //retrieve Rat object from the marker
                Intent intent = new Intent(MapsActivity.this, RatViewActivity.class);
                intent.putExtra("user", getIntent().getStringExtra("user"));
                intent.putExtra("rat", rat); //send rat to detail view
                startActivity(intent);
                return false;
            }
        });

    }

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
            InputStream inStream = getResources().openRawResource(fileName[0]);
            CSVFile csvFile = new CSVFile(inStream);
            List rowList = csvFile.read();
            for (int i = fileName[1]; i < fileName[2]; i++) {
                Rat rat = new Rat();
                String[] row = (String[]) rowList.get(i);
                int id = Integer.valueOf(row[0]);
                String date = row[1];
                String locType = row[2];
                String zip = row[3];
                String address = row[4];
                String city = row[5];
                String borough = row[6];
                String latitude = row[7];
                String longitude = row[8];
                rat.setRatId(id);
                rat.setDate(date);
                rat.setLocType(locType);
                rat.setZip(zip);
                rat.setAddress(address);
                rat.setCity(city);
                rat.setBorough(borough);
                rat.setLatitude(latitude);
                rat.setLongitude(longitude);
                ratList.add(rat);
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            for (Rat r : ratList) {
                LatLng latlng = new LatLng(
                        Double.parseDouble(r.getLatitude()), Double.parseDouble(r.getLongitude()));
                Marker marker = mMap.addMarker(new MarkerOptions().position(latlng));
                marker.setTag(r);
            }
            spinner.setVisibility(View.GONE);
        }
    }
}
