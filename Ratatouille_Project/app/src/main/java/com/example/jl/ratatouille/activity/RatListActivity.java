package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.RatListAdapter;
import com.example.jl.ratatouille.data.CSVFile;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.util.EndlessOnScrollListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Displays the rat data in a RecyclerView
 */

public class RatListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Rat> ratList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rat_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        //set layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //add divider lines
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        //set adapter
        ratList = new ArrayList<>();
        mAdapter = new RatListAdapter(ratList, this);
        mRecyclerView.setAdapter(mAdapter);

        loadRatData();

        //set endless scroll
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener() {
            @Override
            public void onLoadMore() {
                loadRatData();
            }
        });

        //todo: add button which leads to filter options activity


        //add rat button
        final Button addRatBtn = (Button) findViewById(R.id.btn_addRat);
        addRatBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), AddRatActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


        //logout button
        final Button logoutBtn = (Button) findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    //todo: add filtering options and endless scroll

    /**
     * Fills the ratList with the data pulled from the CSV file
     */
    private void loadRatData() {
        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings_trimmed);
        CSVFile csvFile = new CSVFile(inputStream);
        List rowList = csvFile.read();
        for (int i = 0; i < 50; i++) {
            Rat rat = new Rat();
            String[] row = (String[]) rowList.get(i);
            String id = row[0];
            String date = row[1];
            String locType = row[2];
            String zip = row[3];
            String address = row[4];
            String city = row[5];
            String borough = row[6];
            String latitude = row[7];
            String longitude = row[8];
            rat.setRatId(id);
            rat.setDate(date);
            rat.setLocType(locType);
            rat.setZip(zip);
            rat.setAddress(address);
            rat.setCity(city);
            rat.setBorough(borough);
            rat.setLatitude(latitude);
            rat.setLongitude(longitude);
            ratList.add(rat);
        }
        mAdapter.notifyDataSetChanged();
    }
}