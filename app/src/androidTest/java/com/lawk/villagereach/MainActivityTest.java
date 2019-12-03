package com.lawk.villagereach;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {
    private static final String TAG = "myTracker";
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void setUp() throws Exception {
        Intents.init();
    }
    @After
    public void cleanUp() throws Exception {
        Intents.release();
    }

    @Test
    public void canLogin() throws InterruptedException {
        //test with wrong login creds
        onView(withId(R.id.username)).perform(replaceText("administrator"));
        onView(withId(R.id.password)).perform(replaceText("wrongpassword"));
        onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(5000);

        //test with correct login creds
        onView(withId(R.id.username)).perform(replaceText("administrator"));
        onView(withId(R.id.password)).perform(replaceText("password"));
        onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(10000);
        intended(hasComponent(DeliveryActivity.class.getName()));
    }
}
