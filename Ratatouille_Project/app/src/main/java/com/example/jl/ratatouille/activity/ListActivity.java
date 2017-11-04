package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.RecyclerViewAdapter;
import com.example.jl.ratatouille.service.DataService;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.util.EndlessOnScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the rat data in a RecyclerView
 */

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressBar progressBar;

    private List<Rat> ratList = new ArrayList<>();

    static final int ADD_ACTIVITY_REQUEST = 0;
    static final int FILTER_ACTIVITY_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_list);
        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.VISIBLE);

        setupRecyclerView();
        setupButtons();
        setupNavigation();
        setupEndlessScroll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                updateRecyclerView();
            }
        }
        if (requestCode == FILTER_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                updateRecyclerView();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView();
    }

    /**
     * Updates the RecyclerView to display ratList data.
     * ratList contains data that currently exists in shared preferences.
     */
    private void updateRecyclerView() {
        ratList = DataService.getSharedRats(this);
        ((RecyclerViewAdapter) mAdapter).updateData(ratList);

    }

    /**
     * Performs basic RecyclerView setup.
     */
    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.rat_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration
                = new DividerItemDecoration(mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mAdapter = new RecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Performs setup for add rat and filter buttons.
     */
    private void setupButtons() {
        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_REQUEST);
            }
        });

        final Button filterBtn = findViewById(R.id.btn_filter);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilterActivity.class);
                startActivityForResult(intent, FILTER_ACTIVITY_REQUEST);
            }
        });
    }

    /**
     * Performs setup for bottom navigation.
     */
    private void setupNavigation() {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation_list);
        nav.setSelectedItemId(R.id.action_list);
        nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                startActivity(new Intent(ListActivity.this,
                                        MapsActivity.class));
                                break;
                            case R.id.action_list:
                                break;
                            case R.id.action_graph:

                                break;
                            case R.id.action_settings:
                                startActivity(new Intent(ListActivity.this,
                                        SettingsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

    /**
     * Performs setup for endless scroll.
     */
    private void setupEndlessScroll() {
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }

}