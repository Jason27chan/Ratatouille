package com.example.jl.ratatouille;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by jav on 9/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private String _user = "user";
    private String _pass = "pass";

    private EditText _username;
    private EditText _password;


    _username = (EditText) findViewById(R.id.student_name_input);
    _password = (EditText) findViewById(R.id.student_name_input);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
