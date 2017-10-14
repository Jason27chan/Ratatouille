package com.example.jl.ratatouille.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;

public class AddRatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);

        EditText editDate = (EditText) findViewById(R.id.editTxt_created_date);
        EditText editLocType = (EditText) findViewById(R.id.editTxt_loc_type);
        EditText editZip = (EditText) findViewById(R.id.editTxt_incident_zip);
        EditText editAddress = (EditText) findViewById(R.id.editTxt_incident_address);
        EditText editCity = (EditText) findViewById(R.id.editTxt_city);
        EditText editBorough = (EditText) findViewById(R.id.editTxt_borough);
        EditText editLatitude = (EditText) findViewById(R.id.editTxt_latitude);
        EditText editLongitude = (EditText) findViewById(R.id.editTxt_longitude);

        String date = editDate.getText().toString();
        String locType = editLocType.getText().toString();
        String zip = editZip.getText().toString();
        String address = editAddress.getText().toString();
        String city = editCity.getText().toString();
        String borough = editBorough.getText().toString();
        String latitude = editLatitude.getText().toString();
        String longitude = editLongitude.getText().toString();

        Rat rat = new Rat(date, locType, zip, address, city, borough, latitude, longitude);

    }

}
