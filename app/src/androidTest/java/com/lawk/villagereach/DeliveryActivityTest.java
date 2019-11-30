package com.lawk.villagereach;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeliveryActivityTest {
    @Rule
    public ActivityTestRule<DeliveryActivity> activityTestRule =
            new ActivityTestRule<>(DeliveryActivity.class);

    @Test
    public void testDeliveryActivityUI() {
        onView(withId(R.id.quantity_accepted))
                .perform(typeText("5"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.quantity_rejected))
                .perform(typeText("0"));
        onView(withId(R.id.rejection_reason))
                .perform(typeText("N/A"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.rejection_reason))
                .perform(click());
        onData(CoreMatchers.allOf(is(instanceOf(String.class)), is("N/A")))
                .perform(click());
        onView(withId(R.id.notes))
                .perform(typeText("N/A"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.delivery_signature))
                .perform(typeText("Delivery Driver1"));
        onView(withId(R.id.receive_signature))
                .perform(typeText("Receiver1"));

        onView(withId(R.id.submit))
                .perform(click());

    }
}
