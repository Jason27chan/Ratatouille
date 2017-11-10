package com.example.jl.ratatouille;

import com.example.jl.ratatouille.activity.MapsActivity;
import com.example.jl.ratatouille.model.Rat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jav on 11/10/2017.
 */

public class MapsActivityTest {

    List<Rat> testRats;

    /**
     * This method is run before each test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testRats = new ArrayList<>();
    }

    @Test
    public void testUpdateMap() {
        assertEquals(testRats.size(), MapsActivity.updateMap().size());

    }
}
