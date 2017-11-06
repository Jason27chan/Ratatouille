package com.example.jl.ratatouille.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.data.Data;
import com.example.jl.ratatouille.model.MSG;
import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.APIService;
import com.example.jl.ratatouille.service.DataService;
import com.google.gson.Gson;

import net.grandcentrix.tray.AppPreferences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.jl.ratatouille.R.string.submit;
import static com.example.jl.ratatouille.data.Data.options;

/**
 * a class that filters the dates for the map, list, and graph
 */
public class FilterActivity extends AppCompatActivity {

    private EditText mEditStart, mEditEnd;
    private RadioGroup mSort;

    public static final int FILTER_ACTIVITY_REQUEST = 1;

    /**
     * Receives broadcast from DataService when new Rats have been
     * fully loaded to shared preferences. Finishes FilterActivity
     * and returns to calling activity.
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            FilterActivity.this.setResult(RESULT_OK);
            FilterActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        final Button mSubmit = findViewById(R.id.filter_submit);
        mEditStart = findViewById(R.id.filter_startDate);
        mEditEnd = findViewById(R.id.filter_endDate);
        mSort = findViewById(R.id.radio_sort);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    /**
     * Sets filter options as last saved inputs.
     * Obtains last saved inputs from shared preferences.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver, new IntentFilter(DataService.DATA_SERVICE_MSG));

        Map<String, String> options = DataService.getSharedOptions(this);
        if (options != null) {
            mEditStart.setText(options.get("date_start"));
            mEditEnd.setText(options.get("date_end"));
        }
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    /**
     * Calls DataService to retrieve Rats that meet specifications.
     */
    private void requestData() {
        Map<String, String> options = new HashMap<>();
        String startDate = mEditStart.getText().toString();
        String endDate = mEditEnd.getText().toString();
        final RadioButton mSortOption
                = findViewById(mSort.getCheckedRadioButtonId());
        String sortBy = mSortOption.getText().toString();
        options.put("date_start", startDate);
        options.put("date_end", endDate);
        options.put("orderby", sortBy);

        Intent intent = new Intent(this, DataService.class);
        intent.putExtra("options", (HashMap) options);
        startService(intent);
    }
}
