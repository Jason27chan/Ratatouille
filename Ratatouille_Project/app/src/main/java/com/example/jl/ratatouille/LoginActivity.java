package com.example.jl.ratatouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl.ratatouille.db.LoginDataBaseAdapter;
import com.example.jl.ratatouille.users.Admin;
import com.example.jl.ratatouille.users.User;

import static com.example.jl.ratatouille.RegistrationActivity.user;

import static com.example.jl.ratatouille.R.string.login;

/**
 * Activity that allows the login to be created
 *
 * Created by jav on 9/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private String _user = "user";
    private String _pass = "pass";

    private EditText _username;
    private EditText _password;
    private Button _button;

    private LoginDataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _username = (EditText) findViewById(R.id.editTxt_logUsername);
        _password = (EditText) findViewById(R.id.editTxt_logPassword);
        _button = (Button) findViewById(R.id.btn_logLogin);

        dbAdapter = new LoginDataBaseAdapter(this).open();

        _button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get user input
                String userInput = _username.getText().toString();
                String passInput = _password.getText().toString();

                //get stored password
                String password = dbAdapter.getPassword(userInput);

                if (userInput.equals("") || passInput.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter a username and password", Toast.LENGTH_LONG).show();

                } else if (password.equals(getString(R.string.not_found))) {
                    Toast.makeText(getApplicationContext(), "username not found", Toast.LENGTH_LONG).show();

                } else if (password.equals(passInput)) {
                    if (dbAdapter.getAccType(userInput).equals("User")) {
                        user = new User(userInput, passInput);
                    } else {
                        user = new Admin(userInput, passInput);
                    }
                    Intent myIntent = new Intent(v.getContext(), AppActivity.class);
                    startActivity(myIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_LONG).show();
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
