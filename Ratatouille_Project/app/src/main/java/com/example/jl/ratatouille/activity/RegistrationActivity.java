package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.sync.URLConfig;
import com.example.jl.ratatouille.sync.AppController;
import com.example.jl.ratatouille.adapter.SQLiteAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jav on 9/12/2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private static final int REGISTER_USER_REQUEST_CODE = 1;

    private EditText usernameEntry, passwordEntry, confirmEntry;
    private String newUsername, newPassword, newConfirm, newAccType;

    private RadioGroup accountTypes;
    private RadioButton accountType;

    private Button registrationButton;

    private SQLiteAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //get database adapter
        dbAdapter = new SQLiteAdapter(this).open();

        //get EditText references
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

                registerUser(newUsername, newPassword, newConfirm, newAccType);

            }
        });
    }

    /**
     *  Registers the user into the database
     * @param username the desired username
     * @param password desired password
     * @param confirm desired password entered again for confirmation
     * @param account_type whether its a user or admin
     */
    private void registerUser(final String username, final String password, final String confirm, final String account_type) {
        String cancel_req_tag = "req_register";
        StringRequest strReq = new StringRequest(Request.Method.POST, URLConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {

                        Toast.makeText(getApplicationContext(), "registration successful", Toast.LENGTH_SHORT).show();

                        // Launch login activity
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("confirm", confirm);
                params.put("account_type", account_type);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, cancel_req_tag);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}