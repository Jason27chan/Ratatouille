package com.example.jl.ratatouille.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.RecyclerViewAdapter;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.http.DataService;
import com.example.jl.ratatouille.http.NetworkHelper;
import com.example.jl.ratatouille.http.RequestPackage;
import com.example.jl.ratatouille.util.EndlessOnScrollListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.jl.ratatouille.internet.URLConfig.URL_LOAD_RATS;

/**
 * Displays the rat data in a RecyclerView
 */

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Rat> ratList;
    private int startIndex;

    private boolean networkOk;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Rat[] rats = (Rat[]) intent.getParcelableArrayExtra(DataService.DATA_SERVICE_PAYLOAD);
            Log.v(rats[0].getAddress(), "sdlkfjslkdjs");
            ratList = new ArrayList<>();
            ratList = Arrays.asList(rats);
            displayData();
            //mAdapter.notifyDataSetChanged();
            Log.v("RECIEVEBROADCAST", "sldkfjsdlkfjlskdjfslkdj");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_list);

        //check if network ok
        networkOk = NetworkHelper.hasNetworkAccess(this);

        //broadcast receiver for rat data service
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver, new IntentFilter(DataService.DATA_SERVICE_MSG));

        //load rats from url with service
        if (networkOk) {
            /*RequestPackage requestPackage = new RequestPackage();
            requestPackage.setEndPoint(URL_LOAD_RATS);
            requestPackage.setParam("date_start", "2017-08-24");
            requestPackage.setParam("date_end", "2017-08-24");
            Intent intent = new Intent(this, DataService.class);
            intent.putExtra(DataService.REQUEST_PACKAGE, requestPackage);
            startService(intent);*/
        } else {
            Toast.makeText(this, "network error", Toast.LENGTH_LONG).show();
        }

        setupRecyclerView();
        requestData();
        //loadRatData(startIndex, startIndex + 25);
        setupEndlessScroll();

        //add rat
        final FloatingActionButton addRatBtn = findViewById(R.id.btn_addRat);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddRatActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setupEndlessScroll() {
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }

    private void requestData() {
        Intent intent = new Intent(this, DataService.class);
        Map<String, String> options = new HashMap<>();
        options.put("date_start", "2017-08-24");
        options.put("date_end", "2017-08-24");
        intent.putExtra("options", (HashMap) options);
        startService(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }
}