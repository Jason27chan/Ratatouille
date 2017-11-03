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

public class FilterActivity extends AppCompatActivity {

    private EditText mEditStart, mEditEnd;
    private RadioGroup mSort;
    private RadioButton mSortOption;
    private Button mSubmit;


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

        mSubmit = findViewById(R.id.filter_submit);
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


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver, new IntentFilter(DataService.DATA_SERVICE_MSG));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    /**
     * displays the rat data within a certain date range
     */
    private void requestData() {
        Intent intent = new Intent(this, DataService.class);

        Map<String, String> options = new HashMap<>();
        String startDate = mEditStart.getText().toString();
        String endDate = mEditEnd.getText().toString();

        mSortOption = findViewById(mSort.getCheckedRadioButtonId());
        String sortBy = mSortOption.getText().toString();

        options.put("date_start", startDate);
        options.put("date_end", endDate);
        options.put("orderby", sortBy);

        /*
        APIService apiService = APIService.retrofit.create(APIService.class)
        Call<Rat[]> request = apiService.rats(options);
        request.enqueue(new Callback<Rat[]>() {

            @Override
            public void onResponse(Call<Rat[]> call, retrofit2.Response<Rat[]> response) {
                if (response.body() != null) {
                    setResult(RESULT_OK);
                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Rat[]> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });*/


        intent.putExtra("options", (HashMap) options);

        startService(intent);
        Log.v("eeeeee", "filter activity requestdata called");
    }
}
