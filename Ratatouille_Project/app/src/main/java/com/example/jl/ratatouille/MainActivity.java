package com.example.jl.ratatouille;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jl.ratatouille.db.CSVFile;

import java.io.InputStream;
import java.util.List;

/**
 *  Leads to login and registration
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button buttonLogin = (Button) findViewById(R.id.ButtonToLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final Button buttonRegister = (Button) findViewById(R.id.ButtonToRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        InputStream inputStream = getResources().openRawResource(R.raw.RatSightings);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();
    }


}
