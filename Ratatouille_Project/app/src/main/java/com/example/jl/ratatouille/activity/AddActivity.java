package com.example.jl.ratatouille.activity;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.service.APIService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Shannon on 10/14/2017.
 */

public class AddActivity extends AppCompatActivity {

    private EditText editDate, editLocType, editZip, editAddress, editCity, editBorough, editLatitude, editLongitude;
    private DatePicker datePicker;
    private Button submitButton, cancelButton;

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

                //todo:implement date view
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

    private void addRat(final String date, final String locType, final String zip, final String address,
                        final String city, final String borough, final String latitude, final String longitude){
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
        Call<ResponseBody> request = apiService.addRat(options);
        request.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "add successful", Toast.LENGTH_SHORT).show();
                finish();
                //todo:http error codes
                /*try {
                    JSONObject jObj = new JSONObject(response.errorBody().string());
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Toast.makeText(getApplicationContext(), "add successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        String errorMsg = jObj.getString("msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


