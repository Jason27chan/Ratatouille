package com.example.jl.ratatouille;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jl.ratatouille.activity.MapsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jason on 11/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MapActivityTest {

    @Rule
    public ActivityTestRule<MapsActivity> mActivityRule =
            new ActivityTestRule(MapsActivity.class);

    @Test
    public void testFilterButton() {
        onView(withId(R.id.map_filter)).perform(click());
        onView(withId(R.id.filter_submit)).check(matches(withText("Submit")));
    }

    @Test
    public void testAddRatButton() {
        onView(withId(R.id.btn_addRat_maps)).perform(click());
        onView(withId(R.id.btn_submit)).check(matches(withHint("Submit")));
    }

}
