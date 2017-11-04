package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jl.ratatouille.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static com.example.jl.ratatouille.activity.AddActivity.ADD_ACTIVITY_REQUEST;
import static com.example.jl.ratatouille.activity.FilterActivity.FILTER_ACTIVITY_REQUEST;

public class GraphActivity extends AppCompatActivity {
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        setupNavigation();

        graph = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

    /**
     * Performs setup for add button and filter button.
     */
    private void setupButtons() {
        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat_maps);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(myIntent, ADD_ACTIVITY_REQUEST);
            }
        });

        final Button filterBtn = findViewById(R.id.map_filter);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilterActivity.class);
                startActivityForResult(intent, FILTER_ACTIVITY_REQUEST);
            }
        });
    }

    /**
     * Performs setup for navigation bar.
     */
    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation_maps);
        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_list:
                                startActivity(new Intent(
                                        GraphActivity.this, ListActivity.class));
                                break;
                            case R.id.action_map:
                                startActivity(new Intent(
                                        GraphActivity.this, MapsActivity.class));
                                break;
                            case R.id.action_settings:
                                startActivity(new Intent(
                                        GraphActivity.this, SettingsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }
}
