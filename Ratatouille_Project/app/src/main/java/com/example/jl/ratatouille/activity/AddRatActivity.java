package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.UserDbAdapter;
import com.example.jl.ratatouille.sync.AppConfig;
import com.example.jl.ratatouille.sync.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

            submitButton = (Button) findViewById(R.id.btn_submit);
            cancelButton = (Button) findViewById(R.id.btn_cancel);

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
        }
}
