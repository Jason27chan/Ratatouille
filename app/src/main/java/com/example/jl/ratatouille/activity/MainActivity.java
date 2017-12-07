package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.activity.authentication.LoginActivity;
import com.example.jl.ratatouille.activity.authentication.RegistrationActivity;

/**
 *  Leads to login and registration
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button buttonLogin = findViewById(R.id.ButtonToLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final Button buttonRegister = findViewById(R.id.ButtonToRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
