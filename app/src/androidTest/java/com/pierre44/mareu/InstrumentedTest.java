package com.pierre44.mareu;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;
import com.pierre44.mareu.repository.DummyGenerator;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.ui_meeting_list.ListMeetingActivity;
import com.pierre44.mareu.utils.DeleteViewAction;
import com.pierre44.mareu.utils.UtilsTools;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.pierre44.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    public static final int TEST_ITEMS_COUNT = 10;
    public static final String TEST_MEETING_TOPIC = "sujet de la réunion";
    public static final String TEST_MEETING_LONG_TOPIC = "description de sujet de la réunion plus long";
    public static final int TEST_FIRST_POSITION = 0;
    public static final String TEST_DURATION_SPINNER_1H = "01:00";
    public static final String TEST_DATE_PICKER_MEETING = "01/07/2021";
    public static final String TEST_ROOM_SELECTED_ON_SPINNER = DummyGenerator.DUMMY_ROOMS_LIST.get(0).toString();
    public static final String TEST_HOUR_PICKER_MEETING_13_00 = "13:00";
    public static final Room TEST_ROOM_MEETING_0 = DummyGenerator.DUMMY_ROOMS_LIST.get(0);
    public static final User TEST_MEETING_GUEST_0 = DummyGenerator.DUMMY_USERS_LIST.get(0);
    public static final User TEST_MEETING_GUEST_1 = DummyGenerator.DUMMY_USERS_LIST.get(1);
    public static final List<User> TEST_GUESTS_LIST_0 = Arrays.asList(TEST_MEETING_GUEST_0,TEST_MEETING_GUEST_1);
    public static final Meeting TEST_MEETING_1 = new Meeting(0, "TEST", TEST_DATE_PICKER_MEETING, TEST_HOUR_PICKER_MEETING_13_00, TEST_DURATION_SPINNER_1H, TEST_ROOM_MEETING_0, TEST_GUESTS_LIST_0);
    public static final int TEST_EXPECTED_COUNT_FOR_FILTER_BY_ROOM = 2;
    public static final int TEST_EXPECTED_COUNT_FOR_FILTER_BY_DATE = 3;
    public static final String SELECTED_ROOM_NAME = String.valueOf(R.string.google);

    public static final List<Meeting> TEST_MEETINGS = DummyGenerator.DUMMY_MEETINGS_LIST;
    public ActivityScenario mActivity;
    MeetingRepository mMeetingRepository;
    private Calendar mCalendar;

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> myActivityScenarioRule = new ActivityScenarioRule<>(ListMeetingActivity.class);

    @Before
    public void setUp() {
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::doMeetingListEmpty);
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        //Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        //When : we have the list displayed
        onView(withId(R.id.recycler_view_meeting)).check(matches(isDisplayed()));
        //Then : the 10 meetings appear
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_MEETINGS.size()));
    }

    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        //Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        //When : we delete the first one in list by clicking the delete button
        onView(allOf(withParent(withId(R.id.recycler_view_meeting)), withParentIndex(TEST_FIRST_POSITION)));
        onView(withId(R.id.recycler_view_meeting)).perform(RecyclerViewActions.actionOnItemAtPosition(TEST_FIRST_POSITION, new DeleteViewAction()));
        //Then : the meeting list counts sized less one
        onView(withId(R.id.meeting_list_activity)).check(withItemCount(TEST_MEETINGS.size() - 1));
    }

    @Test
    public void myMeetingListActivity_ClickOnCreateMeetingsFloatingActionButton_ShouldOpenCreateMeetingActivity() {
        // Given : We are on meeting list activity
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When :  Click on createMeetingButton FAB
        onView(withId(R.id.create_meeting_button)).perform(click());
        // Then : Create meeting activity to be open
        onView(withId(R.id.create_meeting_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void myCreateMeetingActivity_CompleteForm_ShouldAddMeetingOnMeetingListActivity() {
        // Given : We are on CreateMeeting Activity
        onView(withId(R.id.create_meeting_activity)).perform(click());
        // When : complete text_input_meeting_topic
        onView(withId(R.id.meeting_topic)).perform(click());
        onView(withId(R.id.meeting_topic)).perform(typeTextIntoFocusedView(TEST_MEETING_TOPIC), closeSoftKeyboard(), pressImeActionButton());
        onView(withId(R.id.meeting_topic)).check(matches(withText(TEST_MEETING_TOPIC)));
        // When : complete date_picker_actions
        mCalendar = Calendar.getInstance();
        onView(withId(R.id.date_picker_actions)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.date_picker_actions)).check(matches(withText(TEST_DATE_PICKER_MEETING)));
        // When : complete time_picker_action
        onView(withId(R.id.time_picker_action)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_action)).check(matches(withText(UtilsTools.dateFormat(mCalendar, UtilsTools.TIME_FORMAT_HH_MM))));
        // When : complete spinner_room_action
        onView(withId(R.id.meeting_room)).perform(click());
        onView(withText(TEST_ROOM_SELECTED_ON_SPINNER)).check(matches(isDisplayed()));
        onView(withText(TEST_ROOM_SELECTED_ON_SPINNER)).perform(click());
        onView(withId(R.id.meeting_room)).check(matches(withText(TEST_ROOM_SELECTED_ON_SPINNER)));
        // When : complete spinner_duration_action
        onView(withId(R.id.spinner_meeting_duration)).perform(click());
        onView(withText(TEST_DURATION_SPINNER_1H)).check(matches(isDisplayed()));
        onView(withText(TEST_DURATION_SPINNER_1H)).perform(click());
        onView(withId(R.id.spinner_meeting_duration)).check(matches(withText(TEST_DURATION_SPINNER_1H)));
        // When : complete text_input_email with 2 emails
        onView(withId(R.id.text_input_email)).perform(click());
        onView(withId(R.id.text_input_email)).perform(typeTextIntoFocusedView(TEST_MEETING_GUEST_0.getEmail()), closeSoftKeyboard());
        onView(withId(R.id.text_input_email)).perform(typeTextIntoFocusedView(TEST_MEETING_GUEST_1.getEmail()), closeSoftKeyboard());
        // When : click on buttonValidate
        onView(withId(R.id.create_meeting_validate_button)).perform(click());
        // Then : On List Meeting Activity the number of element is List size + 1
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_MEETINGS.size() + 1));
    }

    @Test
    public void myMeetingListActivity_ApplyFilterByRoom_DisplaysTheCorrectMeetings() {
        // Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : perform click on filter by Room and select the Room
        onView(withContentDescription(R.string.filter_menu)).perform(click());
        onView(withText(R.string.filter_by_room)).perform(click());
        onView(allOf(withParent(withId(R.id.list_room))));
        onView(withText(TEST_ROOM_SELECTED_ON_SPINNER)).check(matches(isDisplayed()));
        // Then : check that the meeting displayed the specified meeting
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_EXPECTED_COUNT_FOR_FILTER_BY_ROOM));
        onView(withId(R.id.recycler_view_meeting)).perform(RecyclerViewActions.actionOnItemAtPosition(TEST_FIRST_POSITION, click()));
        onView(allOf(withId(R.id.meeting_room_text), not(withText(SELECTED_ROOM_NAME)))).check(doesNotExist());
    }

    @Test
    public void myMeetingListActivity_ApplyFilterByDate_DisplaysTheCorrectMeetings() {
        // Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : perform click on filter by date
        onView(withContentDescription(R.string.filter_menu)).perform(click());
        onView(withText(R.string.filter_by_date)).perform(click());
        // When : complete date picker and select date 01/07/2021
        mCalendar = Calendar.getInstance();
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.date_picker_actions)).check(matches(withText(TEST_DATE_PICKER_MEETING)));
        // Then : check that the meeting displayed and that the 01/07/2021 date
        onView(withId(R.id.meeting_list_activity)).check(withItemCount(TEST_EXPECTED_COUNT_FOR_FILTER_BY_DATE));
    }

    //TODO : Filtering Reset correctly init meeting List
    @Test
    public void MyMeetingListActivity_DisableFilter_DisplaysTheListMeetings() {

        // Given : we have 20 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : perform click on filter by Room and select the Room
        onView(withContentDescription(R.string.filter_menu)).perform(click());
        onView(withText(R.string.filter_by_room)).perform(click());
        onView(allOf(withParent(withId(R.id.list_room)))).perform(RecyclerViewActions.actionOnItemAtPosition(TEST_FIRST_POSITION, click()));
        onView(withText(TEST_ROOM_SELECTED_ON_SPINNER)).check(matches(isDisplayed()));
        // When : perform click on filter reset
        onView(withContentDescription(R.string.filter_menu)).perform(click());
        onView(withText(R.string.filter_reset)).perform(click());
        // Then : check we have all of meetings
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_MEETINGS.size()));
    }


    //private static Matcher<View> getViewByContentDescription(int contentDescription) {
    //    return AllOf.allOf(withId(R.id.recycler_view_meeting), withContentDescription(String.valueOf(contentDescription)));
    //}
}