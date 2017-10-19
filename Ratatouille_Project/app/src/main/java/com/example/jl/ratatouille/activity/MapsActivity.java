package com.example.jl.ratatouille.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.jl.ratatouille.R;

/**
 * Created by jav on 10/18/2017.
 */

public class MapsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation_maps);

        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                break;
                            case R.id.action_list:
                                startActivity(new Intent(MapsActivity.this, ListActivity.class));
                                break;
                            case R.id.action_graph:

                                break;
                            case R.id.action_settings:
                                startActivity(new Intent(MapsActivity.this, SettingsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }
}
