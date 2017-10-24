package com.project.demorecord;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


    // ไม่กรอก Name และ Age กดปุ่ม ADDED จะต้องเจอ Please Enter user info //
    @Test
    public void testcase1() {
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }

    // ไม่กรอก Name และ Age=20 กดปุ่ม ADDED จะต้องเจอ Please Enter user info //
    @Test
    public void testcase2() {
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }

    // ไม่มีการเพิ่ม UserInfo และกด GO TO LIST จะเจอ Not Found //
    @Test
    public void testcase3() {
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    // ไม่กรอก Age และ Name=Ying กดปุ่ม ADDED จะต้องเจอ Please Enter user info //
    @Test
    public void testcase4() {
        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }

    // กรอก Name=Ying และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ying อายุ 20 เป็นตัวแรก //
    @Test
    public void testcase5() {
        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
    }

    // กรอก Name=Ladarat และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ladarat อายุ 20 ใน ListView ลำดับที่ 2 //
    @Test
    public void testcase6() {
        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
    }

    // กรอก Name=Somkait และ Age=80 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Somkait อายุ 80 ใน ListView ลำดับที่ 3 //
    @Test
    public void testcase7() {
        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
    }

    // กรอก Name=Prayoch และ Age=60 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Somkait อายุ 60 ใน ListView ลำดับที่ 4 //
    @Test
    public void testcase8() {
        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
    }

}