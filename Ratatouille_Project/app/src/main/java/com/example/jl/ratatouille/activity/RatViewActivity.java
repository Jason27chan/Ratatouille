package com.example.jl.ratatouille.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;

import java.util.ArrayList;

/**
 * Created by jav on 10/9/2017.
 *
 * Detailed view for a Rat
 */

public class RatViewActivity extends AppCompatActivity{
    private Rat rat;
    private ArrayList<String> ratData;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_view);

        rat = getIntent().getExtras().getParcelable("rat");
        ratData = new ArrayList<>();
        ratData.add(rat.getRatId());
        ratData.add(rat.getDate());
        ratData.add(rat.getLocType());
        ratData.add(rat.getZip());
        ratData.add(rat.getAddress());
        ratData.add(rat.getCity());
        ratData.add(rat.getBorough());
        ratData.add(rat.getLatitude());
        ratData.add(rat.getLongitude());

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ratData);

        listView = (ListView) findViewById(R.id.rat_list_view);
        listView.setAdapter(adapter);

    }
}
