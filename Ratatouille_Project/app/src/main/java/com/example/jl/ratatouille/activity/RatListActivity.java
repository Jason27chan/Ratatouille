package com.example.jl.ratatouille.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Adapter;
import android.support.v7.widget.RecyclerView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.adapter.RatAdapter;
import com.example.jl.ratatouille.model.Rat;

import java.util.ArrayList;
import java.util.List;


/**
 * The App Activity for the entire class
 */
public class RatListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Rat> ratList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rat_recycler_view);

        mRecyclerView.setHasFixedSize(true); //improves performance since changes in content do not change layout size of RecyclerView

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RatAdapter(ratList);
        mRecyclerView.setAdapter(mAdapter);

        Rat rat = new Rat(1, 1, "city", 123, "address", "city", "borough", 0, 100);
        ratList.add(rat);


        /*final Button logoutBtn = (Button) findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
        CSVFile csvFile = new CSVFile(inputStream);
        final List scoreList = csvFile.read();

        int[] intArray = {0, 1, 7, 8, 9, 16, 23, 30, 29};
        final TableLayout table = (TableLayout) findViewById(R.id.ratTable);

        for (int i = 0; i < 50; i++) {
            final TableRow row = new TableRow(table.getContext());
            final String[] dataArray = ((String[]) scoreList.get(i));
            for (int j = 0; j < intArray.length; j++) {
                TextView text = new TextView(table.getContext());
                text.setText(dataArray[intArray[j]]);
                row.addView(text);
            }

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 1. Instantiate an AlertDialog.Builder with its constructor
                    AlertDialog.Builder builder = new AlertDialog.Builder(row.getContext());

                    // 2. Chain together various setter methods to set the dialog characteristics
                    String message = "";
                    for (int j = 0; j < dataArray.length; j++) {
                        message += dataArray[j] + "\n";
                    }

                    builder.setMessage(message)
                            .setTitle("SuperRat!");

                    // 3. Get the AlertDialog from create()
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            table.addView(row);
        }*/
    }
}