package com.pierre44.mareu;


import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.DummyMeetingGenerator;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.ui_meeting_list.ListMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {


    private static final int ITEMS_COUNT = 20;
    private List<Meeting> meetings = DummyMeetingGenerator.DUMMY_MEETINGS;
    public ActivityScenario mActivity;
    MeetingRepository service;

    @Rule
    public ActivityScenarioRule mActivityRule = new ActivityScenarioRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getScenario();
        assertThat(mActivity, notNullValue());
        service = DI.getMeetingRepository();
        meetings = DummyMeetingGenerator.DUMMY_MEETINGS;
    }

    //TODO : instrumental test


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pierre44.mareu", appContext.getPackageName());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
                .check(matches(hasMinimumChildCount(1)));
    }
/**
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 0
        onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
                .check((ViewAssertion) withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, (ViewAction) new DeleteViewAction()));
        // Then : the number of element is 19
        onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
                .check((ViewAssertion) withItemCount(ITEMS_COUNT - 1));
    }
**/


}
