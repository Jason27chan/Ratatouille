package com.example.jl.ratatouille;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jl.ratatouille.activity.authentication.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Catherine on 11/10/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void CheckLoginActivityCorrect1() {
        onView(withId(R.id.editTxt_logUsername)).perform(typeText("q"));
        onView(withId(R.id.editTxt_logPassword)).perform(typeText("q"));
        onView(withId(R.id.btn_logLogin)).perform(click());
        onView(withId(R.id.bottom_navigation_maps)).check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginActivityCorrect2() {
        onView(withId(R.id.editTxt_logUsername)).perform(typeText("cathy"));
        onView(withId(R.id.editTxt_logPassword)).perform(typeText("1"));
        onView(withId(R.id.btn_logLogin)).perform(click());
        onView(withId(R.id.bottom_navigation_maps)).check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginActivityIncorrect1() {
        onView(withId(R.id.editTxt_logUsername)).perform(typeText("cathy"));
        onView(withId(R.id.editTxt_logPassword)).perform(typeText("cathy!!!"));
        onView(withId(R.id.btn_logLogin)).perform(click());
        onView(withId(R.id.bottom_navigation_maps)).check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginActivityIncorrect2() {
        onView(withId(R.id.editTxt_logUsername)).perform(typeText(""));
        onView(withId(R.id.editTxt_logPassword)).perform(typeText(""));
        onView(withId(R.id.btn_logLogin)).perform(click());
        onView(withId(R.id.bottom_navigation_maps)).check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginActivityIncorrect3() {
        onView(withId(R.id.editTxt_logUsername)).perform(typeText("true"));
        onView(withId(R.id.editTxt_logPassword)).perform(typeText("true"));
        onView(withId(R.id.btn_logLogin)).perform(click());
        onView(withId(R.id.bottom_navigation_maps)).check(matches(isDisplayed()));
    }
}
