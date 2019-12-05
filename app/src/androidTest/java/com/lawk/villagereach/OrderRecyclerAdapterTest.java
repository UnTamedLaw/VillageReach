//package com.lawk.villagereach;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.ViewAction;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.contrib.RecyclerViewActions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.rule.UiThreadTestRule;
//import androidx.test.runner.AndroidJUnit4;
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class OrderRecyclerAdapterTest {
//
//    private OrderRecyclerAdapter adapter;
//    private RecyclerView recyclerView;
//
//    @Rule
//    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class, true, false);
//    @Rule
//    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();
//
//    @Before
//    public void setup() throws Throwable {
//        final TestActivity activity = activityTestRule.launchActivity(null);
//
//        uiThreadTestRule.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                adapter = new OrderRecyclerAdapter(activity);
//                recyclerView = new RecyclerView(activity);
//                recyclerView.setId(R.id.recyclerview);
//                activity.setContentView(recyclerView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
//                recyclerView.setAdapter(adapter);
//            }
//        });
//    }
//    @Test
//    public void testRecyclerClick() {
//        Espresso.onView(ViewMatchers.withId(R.id.formClick)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
//    }
//
//}
