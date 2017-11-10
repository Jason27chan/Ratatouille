package com.example.jl.ratatouille.activity.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.MSG;
import com.example.jl.ratatouille.service.APIService;
import com.example.jl.ratatouille.adapter.UserSQLiteAdapter;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Class that allows a user to register
 * as an admin or a typical user
 *
 * Created by jav on 9/12/2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private static final int REGISTER_USER_REQUEST_CODE = 1;

    private EditText usernameEntry, passwordEntry, confirmEntry;
    private String newUsername, newPassword, newConfirm, newAccType;

    private RadioGroup accountTypes;
    private RadioButton accountType;

    private UserSQLiteAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //get database adapter
        dbAdapter = new UserSQLiteAdapter(this).open();

        //get EditText references
        usernameEntry = findViewById(R.id.editTxt_regUsername);
        passwordEntry = findViewById(R.id.editTxt_regPassword);
        confirmEntry = findViewById(R.id.editTxt_regConfirm);

        //get account types RadioGroup reference
        accountTypes = findViewById(R.id.radio_regType);

        //get registration Button reference
        final Button registrationButton = findViewById(R.id.btn_regRegister);

        //on click of registration button
        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get account type RadioButton reference
                int selectedId = accountTypes.getCheckedRadioButtonId();
                accountType = findViewById(selectedId);

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
     *  Registers a new user.
     *
     * @param username the desired username
     * @param password desired password
     * @param confirm desired password entered again for confirmation
     * @param account_type whether its a user or admin
     */
    private void registerUser(final String username, final String password,
                              final String confirm, final String account_type) {

        APIService apiService = APIService.retrofit.create(APIService.class);
        Map<String, String> options = new HashMap<>();
        options.put("username", username);
        options.put("password", password);
        options.put("confirm", confirm);
        options.put("account_type", account_type);
        Call<MSG> request = apiService.register(options);
        request.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                if (!response.body().getError()) {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(),
                            msg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(),
                            msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}