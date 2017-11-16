package com.example.jl.ratatouille;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jl.ratatouille.activity.FilterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jason on 11/14/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FilterActivityTest {

    @Rule
    public ActivityTestRule<FilterActivity> mActivityRule =
            new ActivityTestRule(FilterActivity.class);

    @Test
    public void testBadDateInput() {
        onView(withId(R.id.filter_startDate)).perform(typeText(""));
        onView(withId(R.id.filter_startDate)).perform(typeText(""));
        onView(withId(R.id.filter_startDate)).perform(replaceText("/"));
        onView(withId(R.id.filter_endDate)).perform(replaceText("."));
        onView(withId(R.id.radioBtn_asc)).perform(click());
        onView(withId(R.id.filter_submit)).perform(click());
        onView(withId(R.id.error_text)).check(matches(withText("Invalid input: try again")));
    }

    @Test
    public void testGoodDateInput() {
        onView(withId(R.id.filter_startDate)).perform(typeText(""));
        onView(withId(R.id.filter_startDate)).perform(typeText(""));
        onView(withId(R.id.filter_startDate)).perform(replaceText("2012-03-27"));
        onView(withId(R.id.filter_endDate)).perform(replaceText("2011-03-28"));
        onView(withId(R.id.radioBtn_asc)).perform(click());
        onView(withId(R.id.filter_submit)).perform(click());
        onView(withId(R.id.error_text)).check(matches(withText("")));
    }



}
