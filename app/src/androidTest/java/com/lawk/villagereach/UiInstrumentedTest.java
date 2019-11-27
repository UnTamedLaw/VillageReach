package com.lawk.villagereach;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiInstrumentedTest {
    @Rule
    public ActivityTestRule<FormActivity> activityRule =
            new ActivityTestRule<>(FormActivity.class);

    public static ViewAction setTextInTextView(final String value) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }
    @Test
    public void testFormActivityUI() {
        onView(withId(R.id.quantity_ordered))
                .perform(setTextInTextView("5"));
        onView(withId(R.id.quantity_shipped))
                .perform(setTextInTextView("5"));
        onView(withId(R.id.quantity_accepted))
                .perform(typeText("5"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.quantity_rejected))
                .perform(typeText("0"))
                .perform(ViewActions.closeSoftKeyboard());
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
