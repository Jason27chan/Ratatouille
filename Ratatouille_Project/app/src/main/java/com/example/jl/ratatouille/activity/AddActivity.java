package com.example.jl.ratatouille.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.MSG;
import com.example.jl.ratatouille.service.APIService;
import com.example.jl.ratatouille.service.DataService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Shannon on 10/14/2017.
 */

public class AddActivity extends AppCompatActivity {

    private EditText editDate, editLocType, editZip, editAddress,
            editCity, editBorough, editLatitude, editLongitude;
    private Button submitButton, cancelButton;

    public static final int ADD_ACTIVITY_REQUEST = 0;

    /**
     * Receives broadcast from DataService when Rats in shared preferences
     * have been fully refreshed. Finishes AddActivity and returns to
     * calling activity.
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            AddActivity.this.setResult(RESULT_OK);
            AddActivity.this.finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(DataService.DATA_SERVICE_MSG));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);

        submitButton = findViewById(R.id.btn_submit);
        cancelButton = findViewById(R.id.btn_cancel);

        editDate = findViewById(R.id.editTxt_created_date);
        editLocType = findViewById(R.id.editTxt_loc_type);
        editZip = findViewById(R.id.editTxt_incident_zip);
        editAddress = findViewById(R.id.editTxt_incident_address);
        editCity = findViewById(R.id.editTxt_city);
        editBorough = findViewById(R.id.editTxt_borough);
        editLatitude = findViewById(R.id.editTxt_latitude);
        editLongitude = findViewById(R.id.editTxt_longitude);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editDate.getText().toString();
                String locType = editLocType.getText().toString();
                String zip = editZip.getText().toString();
                String address = editAddress.getText().toString();
                String city = editCity.getText().toString();
                String borough = editBorough.getText().toString();
                String latitude = editLatitude.getText().toString();
                String longitude = editLongitude.getText().toString();
                addRat(date, locType, zip, address, city, borough, latitude, longitude);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Adds a new rat sighting to the database.
     *
     * @param date the date of the sighting
     * @param locType the type of the location of the setting
     * @param zip the zip code of the sighting
     * @param address the address of the sighting
     * @param city the city where the sighting occurred
     * @param borough the borough where the sighting occurred
     * @param latitude the latitude of the sighting
     * @param longitude the longitude of the sighting
     */
    private void addRat(final String date, final String locType,
                        final String zip, final String address,
                        final String city, final String borough,
                        final String latitude, final String longitude){
        APIService apiService = APIService.retrofit.create(APIService.class);
        Map<String, String> options = new HashMap<>();
        options.put("date", date);
        options.put("loc_type", locType);
        options.put("zip", zip);
        options.put("address", address);
        options.put("city", city);
        options.put("borough", borough);
        options.put("lat", latitude);
        options.put("lng", longitude);
        Call<MSG> request = apiService.addRat(options);

        request.enqueue(new Callback<MSG>() {

            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                if (!response.body().getError()) {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(),
                            msg, Toast.LENGTH_SHORT).show();
                    refreshRats();
                } else {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(),
                            msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Refreshes the Rats in shared preferences by calling DataService
     * with current filter options.
     */
    private void refreshRats() {
        Map<String, String> options = DataService.getSharedOptions(this);
        Intent intent = new Intent(this, DataService.class);
        intent.putExtra("options", (HashMap) options);
        startService(intent);
    }
}


