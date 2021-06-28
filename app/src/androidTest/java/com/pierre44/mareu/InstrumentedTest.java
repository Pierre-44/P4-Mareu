package com.pierre44.mareu;


import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.DummyGenerator;
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
    private List<Meeting> meetings = DummyGenerator.DUMMY_MEETINGS_LIST;
    public ActivityScenario mActivity;
    MeetingRepository mMeetingRepository;

    @Rule
    public ActivityScenarioRule mActivityRule = new ActivityScenarioRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getScenario();
        assertThat(mActivity, notNullValue());
        mMeetingRepository = DI.getMeetingRepository();
        meetings = DummyGenerator.DUMMY_MEETINGS_LIST;
    }

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

    //TODO : MeetingsList delete action button should remove item

    /**
     * @Test public void myMeetingsList_deleteAction_shouldRemoveItem() {
     * // Given : We remove the element at position 0
     * onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
     * .check((ViewAssertion) withItemCount(ITEMS_COUNT));
     * // When perform a click on a delete icon
     * onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
     * .perform(RecyclerViewActions.actionOnItemAtPosition(0, (ViewAction) new DeleteViewAction()));
     * // Then : the number of element is 19
     * onView(allOf(ViewMatchers.withId(R.id.recycler_view_meeting)))
     * .check((ViewAssertion) withItemCount(ITEMS_COUNT - 1));
     * }
     **/


    //TODO : Create Meetings action button should open activity
    @Test
    public void MyMeetingListActivity_ClickOnCreateMeetingsFloatingActionButton_ShouldOpenCreateMeetingActivity() {

        /**
         // Given : We are on meeting list activity

         // When :  Click on createMeetingButton FAB

         // Then : Create meeting activity to be open
         **/
    }

    //TODO : Complete Meetings action form and Validate should add meeting on list

    @Test
    public void MyCreateMeetingActivity_CompleteForm_ShouldAddMeetingOnMeetingListActivity() {


        /**
         // Given : We had the element at position 0

         // When : complete id/text_input_meeting_topic

         // When : complete id/date_picker_actions

         // When : complete id/time_picker_action

         // When : complete id/spinner_room_action

         // When : complete id/spinner_duration_action

         // When : complete id/text_input_email

         // When : click on id/buttonValidate"

         // Then : the number of element is ist size + 1
         **/

    }

    //TODO : Filtering By Room show the correct meeting

    @Test
    public void MyMeetingListActivity_ApplyFilterByRoom_DisplaysTheCorrectMeetings() {

        /**
         // Given : set meeting with specified Room

         // When : perform click on filter by Room and select the Room

         // Then : check that the meeting displayed the specified meeting
         **/
    }
    //TODO : Filtering By Date show the correct meeting

    @Test
    public void MyMeetingListActivity_ApplyFilterByDate_DisplaysTheCorrectMeetings() {

    }
    /**
     // Given : set meeting on today's date

     // When : perform click on filter by date

     // When : complete date picker and select today's date

     // Then : check that the meeting displayed and that the today's date
     **/

    //TODO : Filtering Reset correctly init meeting List

    @Test
    public void MyMeetingListActivity_DisableFilter_DisplaysTheCorrectMeetings() {

    /**
     // Given : set meetings on today's date and with specified Room

     // When : perform click on filter by date

     // When : perform click on filter by Room and select the Room

     // When : complete date picker and select today's date

     // Then : check that the meeting displayed and that the today's date
     **/
    }
}
