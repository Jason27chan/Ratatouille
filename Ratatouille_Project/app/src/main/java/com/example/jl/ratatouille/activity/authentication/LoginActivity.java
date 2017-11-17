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
import com.example.jl.ratatouille.adapter.UserSQLiteAdapter;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Class that allows a user to login
 *
 * Created by jav on 9/12/2017.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText usernameEdit;
    private EditText passwordEdit;

    //private static final String TAG = "Registration Activity";

    private UserSQLiteAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEdit = findViewById(R.id.editTxt_logUsername);
        passwordEdit = findViewById(R.id.editTxt_logPassword);
        final Button buttonLogin = findViewById(R.id.btn_logLogin);

        dbAdapter = new UserSQLiteAdapter(this).open();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = usernameEdit.getText().toString();
                String passInput = passwordEdit.getText().toString();

                if ("".equals(userInput) || "".equals(passInput)) {
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
     * Checks if user login credentials are valid.
     *
     * @param username the username to be checked
     * @param password the password to be checked
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