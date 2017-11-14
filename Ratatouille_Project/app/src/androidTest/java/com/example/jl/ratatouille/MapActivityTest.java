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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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
        onView(withId(R.id.filter_submit)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddRatButton() {
        onView(withId(R.id.btn_addRat_maps)).perform(click());
        onView(withId(R.id.editTxt_created_date)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_incident_zip)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_incident_address)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_city)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_borough)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_latitude)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_longitude)).check(matches(isDisplayed()));
        onView(withId(R.id.editTxt_borough)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_submit)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_submit)).check(matches(withHint("Submit")));
        onView(withId(R.id.btn_cancel)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_cancel)).check(matches(withHint("Cancel")));

    }

    @Test
    public void testNavigateList() {
        onView(withId(R.id.action_list)).perform(click());
        onView(withId(R.id.rat_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigateGraph() {
        onView(withId(R.id.action_graph)).perform(click());
        onView(withId(R.id.graph_day)).check(matches(isDisplayed()));
        onView(withId(R.id.graph_month)).check(matches(isDisplayed()));
        onView(withId(R.id.graph_year)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigateSettings() {
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.btn_logout)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_logout)).check(matches(withText("Logout")));
    }

}
