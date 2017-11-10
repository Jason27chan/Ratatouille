package com.example.jl.ratatouille;

import android.os.Bundle;

import com.example.jl.ratatouille.activity.GraphActivity;
import com.example.jl.ratatouille.model.Rat;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jason on 11/9/2017.
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GraphActivtiyTest {
    @Mock
    GraphActivity mGraphActivity;

    @Mock
    Bundle bundle;

    @Test
    public void testUpdateDayGraph() throws Exception {
        // Context of the app under test.
        List<Rat> ratList = new ArrayList<>();
        Rat rat = new Rat();
        rat.setDate(new Date(2012, 10, 3));
        ratList.add(rat);
        mGraphActivity.onCreate(bundle);
        mGraphActivity.setRatList(ratList);
//        mGraphActivity.updateDayGraph();
//        GraphView graphDay = mGraphActivity.updateDayGraph();

        LineGraphSeries<DataPoint> series = mGraphActivity.updateDayGraph();
        DataPoint pt = new DataPoint(0, 1);
//        graphDay.getSeries();
        Assert.assertEquals(pt, series.findDataPoint(0, 1));
    }

}
