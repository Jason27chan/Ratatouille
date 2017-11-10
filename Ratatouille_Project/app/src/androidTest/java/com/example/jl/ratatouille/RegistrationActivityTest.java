package com.example.jl.ratatouille;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.jl.ratatouille.activity.authentication.RegistrationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Emily Chang on 11/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegistrationActivityTest {
    @Rule
    public ActivityTestRule<RegistrationActivity> mActivityRule = new ActivityTestRule(RegistrationActivity.class);

    @Test
    public void CheckLoginActivityIncorrect1() {
        onView(withId(R.id.editTxt_regUsername)).perform(typeText("q"));
        onView(withId(R.id.editTxt_regPassword)).perform(typeText("q"));
        onView(withId(R.id.editTxt_regConfirm)).perform(typeText("q"));
        onView(withId(R.id.btn_regRegister)).perform(click());
        onView(withId(R.id.btn_logLogin)).check(matches(isDisplayed()));
    }

    @Test
    public void CheckLoginActivityCorrect1() {
        onView(withId(R.id.editTxt_regUsername)).perform(typeText("7"));
        onView(withId(R.id.editTxt_regPassword)).perform(typeText("7"));
        onView(withId(R.id.editTxt_regConfirm)).perform(typeText("7"));
        onView(withId(R.id.btn_regRegister)).perform(click());
        onView(withId(R.id.editTxt_logUsername)).check(matches(isDisplayed()));
    }

}
