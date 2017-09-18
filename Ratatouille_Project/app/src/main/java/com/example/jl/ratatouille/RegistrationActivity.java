package com.example.jl.ratatouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jl.ratatouille.db.LoginDataBaseAdapter;

/**
 * Created by jav on 9/12/2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private static final int REGISTER_USER_REQUEST_CODE = 1;

    private String newUsername;
    private String newPassword;
    private String newConfirm;
    private String newAccType;

    private EditText usernameEntry;
    private EditText passwordEntry;
    private EditText confirmEntry;

    private RadioGroup accountTypes;
    private RadioButton accountType;

    private Button registrationButton;

    private LoginDataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //get database adapter
        dbAdapter = new LoginDataBaseAdapter(this).open();

        //get account info EditText references
        usernameEntry = (EditText) findViewById(R.id.editTxt_regUsername);
        passwordEntry = (EditText) findViewById(R.id.editTxt_regPassword);
        confirmEntry = (EditText) findViewById(R.id.editTxt_regConfirm);

        //get account types RadioGroup reference
        accountTypes = (RadioGroup) findViewById(R.id.radio_regType);

        //get registration Button reference
        registrationButton = (Button) findViewById(R.id.btn_regRegister);

        //on click of registration button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get account type RadioButton reference
                int selectedId = accountTypes.getCheckedRadioButtonId();
                accountType = (RadioButton) findViewById(selectedId);

                //get entered username, passwords, and account type
                newUsername = usernameEntry.getText().toString();
                newPassword = passwordEntry.getText().toString();
                newConfirm = confirmEntry.getText().toString();
                newAccType = accountType.getText().toString();

                if (newUsername.equals("") || newPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter a username and password", Toast.LENGTH_LONG).show();

                } else if (!newPassword.equals(newConfirm)) {
                    Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_LONG).show();

                } else {
                    //save the data in database
                    dbAdapter.insertEntry(newUsername, newPassword, newAccType);
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
