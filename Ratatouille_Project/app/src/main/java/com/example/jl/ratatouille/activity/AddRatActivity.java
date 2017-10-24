package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;

import java.io.FileWriter;

/**
 * Created by Shannon on 10/14/2017.
 */

public class AddRatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);

        final Button submitButton;
        final Button cancelButton;

        submitButton = findViewById(R.id.btn_submit);
        cancelButton = findViewById(R.id.btn_cancel);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add to database
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //do not add to database
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        EditText editDate = findViewById(R.id.editTxt_created_date);
        EditText editLocType = findViewById(R.id.editTxt_loc_type);
        EditText editZip = findViewById(R.id.editTxt_incident_zip);
        EditText editAddress = findViewById(R.id.editTxt_incident_address);
        EditText editCity = findViewById(R.id.editTxt_city);
        EditText editBorough = findViewById(R.id.editTxt_borough);
        EditText editLatitude = findViewById(R.id.editTxt_latitude);
        EditText editLongitude = findViewById(R.id.editTxt_longitude);

        String date = editDate.getText().toString();
        String locType = editLocType.getText().toString();
        String zip = editZip.getText().toString();
        String address = editAddress.getText().toString();
        String city = editCity.getText().toString();
        String borough = editBorough.getText().toString();
        String latitude = editLatitude.getText().toString();
        String longitude = editLongitude.getText().toString();

        /*
        Rat rat = new Rat(date, locType, zip, address, city, borough, latitude, longitude);
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(R.raw.rat_sightings));
            fileWriter.append(Integer.toString(rat.getRatId())).append(",");
            fileWriter.append(locType).append(",");
            fileWriter.append(zip).append(",");

            //Write the CSV file header
            //Write a new student object list to the CSV file

        } catch (Exception e) {


        }*/
    }
}


