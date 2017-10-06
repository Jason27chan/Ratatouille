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
import com.example.jl.ratatouille.app.AppConfig;
import com.example.jl.ratatouille.app.AppController;
import com.example.jl.ratatouille.db.LoginDataBaseAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jav on 9/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText _username;
    private EditText _password;
    private Button _button;

    private static final String TAG = RegistrationActivity.class.getSimpleName();

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

                //check for empty inputs
                if (userInput.equals("") || passInput.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter a username and password", Toast.LENGTH_LONG).show();

                } else {
                    checkLogin(userInput, passInput);
                }
            }
        });
    }

    private void checkLogin(final String username, final String password) {
        //tag used to cancel request?
        String cancel_req_tag = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        //add session management here

                        //JSONObject user = jObj.getJSONObject("user");
                        //String username = user.getString("username");

                        Intent intent = new Intent(LoginActivity.this, AppActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //error in login
                        String errorMsg = jObj.getString("msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        //adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, cancel_req_tag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}