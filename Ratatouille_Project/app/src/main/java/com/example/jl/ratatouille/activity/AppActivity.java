package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.db.CSVFile;

import java.io.InputStream;
import java.util.List;

/**
 * The App Activity for the entire class
 */
public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        /*TextView accountMade = (TextView) findViewById(R.id.getemgood);
        if (RegistrationActivity.user.isAdmin()) {
            accountMade.setText("you are an admin");
        } else {
            accountMade.setText("you are a user");
        }*/

        final Button logoutBtn = (Button) findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();

        // for all String arrays in scoreList
        //  create new TableRow
        //  for all elements in intArray
        //      Add column to tableRow
        //  add tableRow to tableLayout


//        ArrayAdapter<String> ratAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        for (int i = 0; i < 3; i++) {
//            ratAdapter.addAll((String[]) scoreList.get(i));
//        }


        int[] intArray = {0, 1, 7, 8, 9, 16, 23, 30, 29};
        TableLayout table = (TableLayout) findViewById(R.id.ratTable);

        for (int i = 0; i < 50; i++) {
            TableRow row = new TableRow(table.getContext());
            for (int j = 0; j < intArray.length; j++) {
                TextView text = new TextView(table.getContext());
                text.setText(((String[]) scoreList.get(i))[intArray[j]]);
            }
            table.addView(row);
        }

//        int count = ratAdapter.getCount();
//        for (int i = 0; i < count; i++) {
//            table.addView(ratAdapter.getView(i, null, table));
//        }

//        ListView ratData = (ListView) findViewById(R.id.ratList);
//        ratData.setAdapter(ratAdapter);
    }
}