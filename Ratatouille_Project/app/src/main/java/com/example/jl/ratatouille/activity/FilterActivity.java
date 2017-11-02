package com.example.jl.ratatouille.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.service.DataService;

import java.util.HashMap;
import java.util.Map;

import static com.example.jl.ratatouille.R.string.submit;

public class FilterActivity extends AppCompatActivity {

    private EditText mEditStart, mEditEnd;
    private RadioGroup mSort;
    private RadioButton mSortOption;
    private Button mSubmit;

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
        intent.putExtra("options", (HashMap) options);

        startService(intent);
        finish();
    }
}
