package com.example.jl.ratatouille;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import com.example.jl.ratatouille.activity.ListActivity;
import com.example.jl.ratatouille.activity.MainActivity;
import com.example.jl.ratatouille.adapter.RecyclerViewAdapter;
import com.example.jl.ratatouille.model.Rat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Shannon on 11/10/2017.
 */

/**
 * Method JUnit Test, which tests if the RecyclerView is updating the RecyclerView
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateDataTest extends AndroidTestCase {
    private List<Rat> input;
    private List<Rat> actual;
    private java.sql.Date date;
    private Rat goodRat;
    private Rat badRat;
    private RecyclerViewAdapter newRecyclerView;
    @Mock
    private Context context;

    /*
     * Set up is run before every test
     */
    @Before
    public void setUp() {
//        context = new MockContext();
        date = new java.sql.Date(2017-11-11);
        goodRat = new Rat(date, "city", 30332, "564 Jefferson St", "Atlanta", "", "34.123", 24.44);
        badRat = null;
        newRecyclerView = new RecyclerViewAdapter(context);
//        newRecyclerView.toString();
    }
    /*
     * Rats with good input should be updated with all good input
     */
    @Test
    public void testAllGoodRats() {
        input = new ArrayList<>();
        input.add(goodRat);
        input.add(goodRat);
        input.add(goodRat);

        newRecyclerView.updateData(input);
        actual = newRecyclerView.getRatList();

        assertEquals(input.size(), newRecyclerView.getItemCount());
        assertEquals(input.get(0), actual.get(0));
        assertEquals(input.get(1), actual.get(1));
        assertEquals(input.get(2), actual.get(2));
    }

    /*
     * Test a list that has not been initialized (a null list)
     */
    @Test
    public void testNullList() {
        input = null;
        newRecyclerView.updateData(input);
        actual = newRecyclerView.getRatList();

//        assertEquals(input.size(), newRecyclerView.getItemCount());
        assertEquals(null, actual);
    }

    /*
     * Test empty list
     */
    @Test
    public void testEmptyList() {
        input = new ArrayList<>();

        newRecyclerView.updateData(input);
        actual = newRecyclerView.getRatList();
        assertEquals(input, actual);

    }

    /*
     * Test some good and bad rats in list
     */
    @Test
    public void testGoodAndBadRats() {
        input = new ArrayList<>();
        input.add(goodRat);
        input.add(badRat);
        input.add(badRat);

        newRecyclerView.updateData(input);
        actual = newRecyclerView.getRatList();
        assertEquals(input.size(), actual.size());
        assertEquals(input.get(0), actual.get(0));
        assertEquals(input.get(1), actual.get(1));
        assertEquals(input.get(2), actual.get(2));
    }

    /*
     * Test for null input is still updated with null input
     */
    @Test
    public void testAllBadRats() {
        input = new ArrayList<>();
        input.add(badRat);
        input.add(badRat);
        input.add(badRat);

        newRecyclerView.updateData(input);
        actual = newRecyclerView.getRatList();
        assertEquals(input.size(), actual.size());
        assertEquals(input.get(0), actual.get(0));
        assertEquals(input.get(1), actual.get(1));
        assertEquals(input.get(2), actual.get(2));
    }
}
