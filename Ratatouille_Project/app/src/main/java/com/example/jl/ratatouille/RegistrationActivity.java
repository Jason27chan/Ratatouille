package com.example.jl.ratatouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jav on 9/12/2017.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REGISTER_USER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button registrationButton = (Button) findViewById(R.id.registration_button_register);
        registrationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registration_button_register:
                registerNewUser();
                break;

            case R.id.registration_button_cancel:
                finish();
        }

    }

    private void registerNewUser() {

        Intent registerIntent = new Intent();

        startActivityForResult(registerIntent, REGISTER_USER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
