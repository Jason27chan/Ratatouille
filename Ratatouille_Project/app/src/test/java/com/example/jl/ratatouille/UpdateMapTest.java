package com.example.jl.ratatouille;


import com.example.jl.ratatouille.activity.MapsActivity;
import com.example.jl.ratatouille.model.Rat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jav on 11/10/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UpdateMapTest {

    private List<Rat> input;
    private List<Rat> expected;
    private MapsActivity activity;

    private Rat badRat;
    private Rat goodRatGoodLoc;
    private Rat goodRatBadLoc;

    /**
     * This setup is run before each test
     */
    @Before
    public void setUp() {
        input = new ArrayList<>();
        expected = new ArrayList<>();
        activity = Robolectric.setupActivity(MapsActivity.class);
        activity.setRatList(input);
        badRat = null;
        goodRatGoodLoc = new Rat();
        goodRatGoodLoc.setLatitude("1");
        goodRatGoodLoc.setLongitude(1.0);
        goodRatBadLoc = new Rat();
    }

    @Test
    public void testNullList() {
        activity.setRatList(null);
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testEmpty() {
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testNoNullRatsNoNullLocations() {
        input.add(goodRatGoodLoc);
        input.add(goodRatGoodLoc);
        input.add(goodRatGoodLoc);
        expected.add(goodRatGoodLoc);
        expected.add(goodRatGoodLoc);
        expected.add(goodRatGoodLoc);
        activity.setRatList(input);

        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i));
        }
        System.out.println();
        for (int i = 0; i < activity.updateMap().size(); i++) {
            System.out.println(activity.updateMap().get(i));
        }

        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testNoNullRatsSomeNullLocations() {
        input.add(goodRatGoodLoc);
        input.add(goodRatBadLoc);
        input.add(goodRatBadLoc);
        expected.add(goodRatGoodLoc);
        activity.setRatList(input);
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testNoNullRatsAllNullLocations() {
        input.add(goodRatBadLoc);
        input.add(goodRatBadLoc);
        input.add(goodRatBadLoc);
        activity.setRatList(input);
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testSomeNullRatsNoNullLocations() {
        input.add(badRat);
        input.add(badRat);
        input.add(goodRatGoodLoc);
        expected.add(goodRatGoodLoc);
        activity.setRatList(input);
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testSomeNullRatsSomeNullLocations() {
        input.add(badRat);
        input.add(goodRatBadLoc);
        input.add(goodRatGoodLoc);
        expected.add(goodRatGoodLoc);
        activity.setRatList(input);

        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i));
        }
        System.out.println();
        for (int i = 0; i < activity.updateMap().size(); i++) {
            System.out.println(activity.updateMap().get(i));
        }
        System.out.println();
        for (Rat r : activity.getRatList()) {
            System.out.println(r);
        }

        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testSomeNullRatsAllNullLocations() {
        input.add(badRat);
        input.add(goodRatBadLoc);
        input.add(goodRatBadLoc);
        activity.setRatList(input);
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }

    @Test
    public void testAllNullRatsAllNullLocations() {
        input.add(badRat);
        input.add(badRat);
        input.add(badRat);
        activity.setRatList(input);
        assertEquals(expected.size(), activity.updateMap().size());
        assertEquals(expected, activity.updateMap());
    }
}
