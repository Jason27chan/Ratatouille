package com.example.jl.ratatouille.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.RecyclerViewAdapter;
import com.example.jl.ratatouille.service.DataService;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.util.EndlessOnScrollListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Displays the rat data in a RecyclerView
 */

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Rat> ratList;
    ProgressBar progressBar;
    private EditText editTextStartDate, editTextEndDate;
    private Map<String, String> options;
    private Intent intent;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Rat[] rats = (Rat[]) intent.getParcelableArrayExtra(DataService.DATA_SERVICE_PAYLOAD);
            ratList = Arrays.asList(rats);
            mAdapter.notifyDataSetChanged();
            displayData();
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_list);
        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.VISIBLE);

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver, new IntentFilter(DataService.DATA_SERVICE_MSG));
        setupRecyclerView();
        setupButton();
        setupNavigation();
        setupEndlessScroll();
        requestData();
    }

    private void requestData() {
        intent = new Intent(this, DataService.class);
        options = new HashMap<>();
        editTextStartDate = findViewById(R.id.editTxt_startDate);
        editTextEndDate = findViewById(R.id.editTxt_endDate);
        Button button = findViewById(R.id.btn_submitDate);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String startDate = editTextStartDate.getText().toString();
                String endDate = editTextEndDate.getText().toString();
                if (startDate.equals("") || endDate.equals("")) {
                    Toast.makeText(getApplicationContext(), "please enter a start date and end date", Toast.LENGTH_LONG).show();
                } else {
                    options.put("date_start", startDate);
                    options.put("date_end", endDate);
                    intent.putExtra("options", (HashMap) options);
                    startService(intent);
                }
            }
        });

    }

    private void displayData() {
        if (ratList != null) {
            mAdapter = new RecyclerViewAdapter(ratList, this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.rat_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration
                = new DividerItemDecoration(mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
    }

    private void setupButton() {
        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation_list);
        nav.setSelectedItemId(R.id.action_list);
        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                startActivity(new Intent(ListActivity.this, MapsActivity.class));
                                break;
                            case R.id.action_list:
                                break;
                            case R.id.action_graph:

                                break;
                            case R.id.action_settings:
                                startActivity(new Intent(ListActivity.this, SettingsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

    private void setupEndlessScroll() {
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }


    private void updateData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }
}