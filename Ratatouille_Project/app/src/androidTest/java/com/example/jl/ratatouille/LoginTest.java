package com.example.jl.ratatouille;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jl.ratatouille.activity.authentication.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Test
    public void useLogin() throws Exception {
        // Context of the app under test.
        LoginActivity LogAct = new LoginActivity();

        // need to fix useLogin to return true/false
        assertEquals(true, LogAct.checkLogin("q","q"));
        assertEquals(false, LogAct.checkLogin("username","password"));
        assertEquals(false, LogAct.checkLogin("shwu6","123456"));
    }
}
