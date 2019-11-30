package com.lawk.villagereach;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void canLogin() throws InterruptedException {
        Intents.init();
        onView(withId(R.id.username)).perform(replaceText("administrator"));
        onView(withId(R.id.password)).perform(replaceText("password"));
        onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(10000);
        intended(hasComponent(DeliveryActivity.class.getName()));

    }
}
