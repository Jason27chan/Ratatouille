package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jl.ratatouille.R;

/**
 * Class that represents the settings
 * which is the logout button
 *
 * Created by jav on 10/19/2017.
 */

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button logoutBtn = findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        BottomNavigationView nav = findViewById(R.id.bottom_navigation_settings);
        nav.setSelectedItemId(R.id.action_settings);
        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                startActivity(new Intent(
                                        SettingsActivity.this, MapsActivity.class));
                                break;
                            case R.id.action_list:
                                startActivity(new Intent(
                                        SettingsActivity.this, ListActivity.class));
                                break;
                            case R.id.action_graph:

                                break;
                            case R.id.action_settings:
                                break;
                        }
                        return true;
                    }
                });
    }
}
