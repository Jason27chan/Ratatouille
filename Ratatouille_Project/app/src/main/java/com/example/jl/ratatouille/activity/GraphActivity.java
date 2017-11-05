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
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.DataService;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.Array;
import java.sql.Date;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.jl.ratatouille.activity.AddActivity.ADD_ACTIVITY_REQUEST;
import static com.example.jl.ratatouille.activity.FilterActivity.FILTER_ACTIVITY_REQUEST;

public class GraphActivity extends AppCompatActivity {
    private GraphView graph;
    private List<Rat> ratList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = findViewById(R.id.graph);
        setupNavigation();
        updateGraph();
    }

    private void updateGraph() {
        ratList = DataService.getSharedRats(this);
        List<ArrayList<Rat>> list = new ArrayList<>();
        Date date = null;
        int dayCount = -1;
        for (Rat r : ratList) {
            if (!r.getDate().equals(date)) {
                date = r.getDate();
                list.add(new ArrayList<Rat>());
                dayCount++;
            }
            list.get(dayCount).add(r);
        }
        DataPoint[] dataPoints = new DataPoint[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dataPoints[i] = new DataPoint(i, list.get(i).size());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graph.addSeries(series);

    }

    /**
     * Performs setup for add button and filter button.
     */
    private void setupButtons() {
        final FloatingActionButton addRatBtn = findViewById(R.id.graph_add);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(myIntent, ADD_ACTIVITY_REQUEST);
            }
        });

        final FloatingActionButton filterBtn = findViewById(R.id.graph_filter);
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
        BottomNavigationView nav = findViewById(R.id.bottom_navigation_graph);
        nav.setSelectedItemId(R.id.action_graph);
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
