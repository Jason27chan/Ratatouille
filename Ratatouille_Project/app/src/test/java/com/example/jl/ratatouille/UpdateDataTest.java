package com.example.jl.ratatouille;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jl.ratatouille.adapter.RecyclerViewAdapter;
import com.example.jl.ratatouille.model.Rat;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Shannon on 11/10/2017.
 */

/**
 * Method JUnit Test, which tests if the RecyclerView is updating the RecyclerView
 */
public class UpdateDataTest {
    private List<Rat> myRatList = new ArrayList<>();
    Rat rat = new Rat();
    myRatList.add(rat);
    // create many rats

    @Test
    public void useUpdateData() throws Exception {
        private RecyclerViewAdapter newRecyclerView = new RecyclerViewAdapter();
        private List<Rat> ratList = newRecyclerView.getRatList(); // does this ratList
        newRecyclerView.updateData(); // params!
        // make an ArrayList<Rat> rats to be passed in
        // call updateData(List<Rat> rats) from RecyclerViewAdapter =
        // check ratList with ArrayList
        // lots of assert statements

        assertEquals(newRecyclerView.getRatList()[0], myRatList[0]);
    }
}
