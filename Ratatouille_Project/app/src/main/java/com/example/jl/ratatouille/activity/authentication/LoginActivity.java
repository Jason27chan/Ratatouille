package com.example.jl.ratatouille.activity.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.activity.MapsActivity;
import com.example.jl.ratatouille.model.MSG;
import com.example.jl.ratatouille.service.APIService;
import com.example.jl.ratatouille.adapter.SQLiteAdapter;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by jav on 9/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText _username;
    private EditText _password;
    private Button _button;

    private static final String TAG = RegistrationActivity.class.getSimpleName();

    private SQLiteAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _username = findViewById(R.id.editTxt_logUsername);
        _password = findViewById(R.id.editTxt_logPassword);
        _button = findViewById(R.id.btn_logLogin);

        dbAdapter = new SQLiteAdapter(this).open();

        _button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userInput = _username.getText().toString();
                String passInput = _password.getText().toString();

                if (userInput.equals("") || passInput.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "please enter a username and password",
                            Toast.LENGTH_LONG).show();
                } else {
                    checkLogin(userInput, passInput);
                }
            }
        });
    }

    /**
     * Checks to the login to make sure that the username and password are correct
     * @param username the username for the user
     * @param password the password for the user
     */
    private void checkLogin(final String username, final String password) {
        APIService apiService = APIService.retrofit.create(APIService.class);
        Map<String, String> options = new HashMap<>();
        options.put("username", username);
        options.put("password", password);

        Call<MSG> request = apiService.login(options);
        request.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                if (!response.body().getError()) {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(), msg,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    startActivity(intent);
                } else {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(), msg,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}