package com.example.jl.ratatouille;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jav on 9/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private String _user = "user";
    private String _pass = "pass";

    private EditText _username;
    private EditText _password;
    private Button _button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _username = (EditText) findViewById(R.id.editText2);
        _password = (EditText) findViewById(R.id.editText);
        _button = (Button) findViewById(R.id.ButtonLogin);

        _button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userInput = _username.getText().toString();
                String passInput = _password.getText().toString();
                if (userInput.equals(_user) && passInput.equals(_pass)) {
                    // Go to App page
                    Intent myIntent = new Intent(v.getContext(), AppActivity.class);
                    startActivity(myIntent);


                } else {
                    // Error Message
                    System.out.println("Error");
                }
            }
        });
    }
}
