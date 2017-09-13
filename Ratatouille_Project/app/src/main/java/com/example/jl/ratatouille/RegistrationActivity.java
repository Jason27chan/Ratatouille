package com.example.jl.ratatouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    private Button registrationButton;

    private LoginDataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //get database adapter
        dbAdapter = new LoginDataBaseAdapter(this).open();

        //get edit text references
        usernameEntry = (EditText) findViewById(R.id.registration_edit_username);
        passwordEntry = (EditText) findViewById(R.id.registration_edit_password);

        //get registration button reference
        registrationButton = (Button) findViewById(R.id.registration_button_register);

        //on click of registration button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get entered username and password
                newUsername = usernameEntry.getText().toString();
                newPassword = passwordEntry.getText().toString();

                //save the data in database
                dbAdapter.insertEntry(newUsername, newPassword);

            }
        });
    }

}
