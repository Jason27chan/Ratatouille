package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.DataService;
import com.example.jl.ratatouille.sync.AppController;
import com.example.jl.ratatouille.sync.RequestPackage;
import com.example.jl.ratatouille.sync.URLConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import static com.example.jl.ratatouille.sync.URLConfig.URL_ADD_RAT;

/**
 * Created by Shannon on 10/14/2017.
 */

public class AddRatActivity extends AppCompatActivity {

    private EditText editDate, editLocType, editZip, editAddress, editCity, editBorough, editLatitude, editLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);

        final Button submitButton;
        final Button cancelButton;

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
                //do not add to database
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }

    private void addRat(final String date, final String locType, final String zip, final String address,
                        final String city, final String borough, final String latitude, final String longitude){
        String cancel_req_tag = "req_register";
        StringRequest strReq = new StringRequest(Request.Method.POST, URLConfig.URL_ADD_RAT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {

                        Toast.makeText(getApplicationContext(), "add successful", Toast.LENGTH_SHORT).show();

                        //Launch login activity
                        Intent intent = new Intent(AddRatActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("date", date);
                params.put("loc_type", locType);
                params.put("zip", zip);
                params.put("address", address);
                params.put("city", city);
                params.put("borough", borough);
                params.put("lat", latitude);
                params.put("lng", longitude);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, cancel_req_tag);

    }
}


