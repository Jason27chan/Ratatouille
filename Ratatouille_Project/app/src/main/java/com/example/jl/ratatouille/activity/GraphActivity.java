package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.DataService;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.jl.ratatouille.activity.AddActivity.ADD_ACTIVITY_REQUEST;
import static com.example.jl.ratatouille.activity.FilterActivity.FILTER_ACTIVITY_REQUEST;

/**
 * the class that represents graphs
 */
public class GraphActivity extends AppCompatActivity {
    private GraphView graphDay, graphMonth, graphYear;
    private List<Rat> ratList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graphDay = findViewById(R.id.graph_day);
        graphMonth = findViewById(R.id.graph_month);
        graphYear = findViewById(R.id.graph_year);
        setupNavigation();
        setupButtons();
        updateGraphs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateGraphs();
    }

    /**
     * update graphs when a new date range is selected
     */
    private void updateGraphs() {
        ratList = DataService.getSharedRats(this);
        updateDayGraph();
        updateMonthGraph();
        updateYearGraph();
    }

    /**
     * updates the day graph when the date range is selected
     */
    private void updateDayGraph() {
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
        graphDay.addSeries(series);
    }

    /**
     * month graph is updated when the new date range is selected
     */
    private void updateMonthGraph() {
        Calendar cal = Calendar.getInstance();
        List<ArrayList<Rat>> list = new ArrayList<>();
        int month = -1;
        int monthCount = -1;
        for (Rat r : ratList) {
            cal.setTime(r.getDate());
            int ratMonth = cal.get(Calendar.MONTH);
            if (ratMonth != month) {
                month = ratMonth;
                list.add(new ArrayList<Rat>());
                monthCount++;
            }
            list.get(monthCount).add(r);
        }
        DataPoint[] dataPoints = new DataPoint[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dataPoints[i] = new DataPoint(i, list.get(i).size());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graphMonth.addSeries(series);
    }

    /**
     * year graph is updated when the new date range is selected
     */
    private void updateYearGraph() {
        Calendar cal = Calendar.getInstance();
        List<ArrayList<Rat>> list = new ArrayList<>();
        int year = -1;
        int yearCount = -1;
        for (Rat r : ratList) {
            cal.setTime(r.getDate());
            int ratYear = cal.get(Calendar.YEAR);
            if (ratYear != year) {
                year = ratYear;
                list.add(new ArrayList<Rat>());
                yearCount++;
            }
            list.get(yearCount).add(r);
        }
        DataPoint[] dataPoints = new DataPoint[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dataPoints[i] = new DataPoint(i, list.get(i).size());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graphYear.addSeries(series);
    }

    /**
     * Performs setup for add button and filter button.
     */
    private void setupButtons() {
        final FloatingActionButton addRatBtn = findViewById(R.id.graph_add);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
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


    public List<Rat> getRatList() {
        return ratList;
    }

    public void setRatList(List<Rat> ratList) {
        this.ratList = ratList;
    }
}