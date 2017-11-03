package com.example.jl.ratatouille.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jl.ratatouille.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(20, 5),
                new DataPoint(21, 3),
                new DataPoint(27, 2),
                new DataPoint(24, 4)
        });
        graph.addSeries(series);
    }
}
