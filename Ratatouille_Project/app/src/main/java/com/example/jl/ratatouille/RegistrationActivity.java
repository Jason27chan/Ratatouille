package com.example.jl.ratatouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl.ratatouille.db.LoginDataBaseAdapter;

/**
 * Created by jav on 9/12/2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private static final int REGISTER_USER_REQUEST_CODE = 1;

    private String newUsername;
    private String newPassword;

    private EditText usernameEntry;
    private EditText passwordEntry;
    private EditText confirmEntry;

    private Button registrationButton;

    private LoginDataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //get database adapter
        dbAdapter = new LoginDataBaseAdapter(this).open();

        //get EditText references
        usernameEntry = (EditText) findViewById(R.id.editTxt_regUsername);
        passwordEntry = (EditText) findViewById(R.id.editTxt_regPassword);
        confirmEntry = (EditText) findViewById(R.id.editTxt_regConfirm);

        //get registration button reference
        registrationButton = (Button) findViewById(R.id.btn_regRegister);

        //on click of registration button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get entered username and password
                newUsername = usernameEntry.getText().toString();
                newPassword = passwordEntry.getText().toString();

                if (newUsername.equals("") || newPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter a username and password", Toast.LENGTH_LONG).show();

                } else {
                    //save the data in database
                    dbAdapter.insertEntry(newUsername, newPassword, null);
                    Toast.makeText(getApplicationContext(), "account creation successful", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}
