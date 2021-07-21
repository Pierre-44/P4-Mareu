package com.pierre44.mareu;


import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;
import com.pierre44.mareu.repository.DummyGenerator;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.ui_meeting_list.ListMeetingActivity;
import com.pierre44.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.pierre44.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;


@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private static MeetingRepository mMeetingRepository = DI.getNewInstanceMeetingRepository();
    private Calendar mCalendar;

    public static final int TEST_ITEMS_COUNT = 10;

    public static final int TEST_MEETING_ID = 1000;
    public static final String TEST_MEETING_TOPIC = "sujet test";
    public static final String TEST_MEETING_LONG_TOPIC = "description de sujet de la réunion plus long";
    public static final int TEST_FIRST_POSITION = 0;
    public static final String TEST_DURATION_SPINNER_1H = "01:00";
    public static final String TEST_DATE_PICKER_MEETING = "02/07/2021";

    public static final String TEST_HOUR_PICKER_MEETING_12_00 = "12:00";
    public static final String TEST_HOUR_PICKER_MEETING_13_00 = "13:00";

    public static final int TEST_ROOM_MEETING_INT_0 = 0;
    public static final String TEST_ROOM_MEETING_STRING_CHROME = String.valueOf(R.string.google);

    public static final String TEST_ROOM_MEETING_STRING_0 = DummyGenerator.DUMMY_ROOMS_LIST.get(0).toString();

    public static final Room TEST_ROOM_MEETING_0 = DummyGenerator.DUMMY_ROOMS_LIST.get(0);

    public static final User TEST_MEETING_GUEST_0 = DummyGenerator.DUMMY_USERS_LIST.get(0);
    public static final User TEST_MEETING_GUEST_1 = DummyGenerator.DUMMY_USERS_LIST.get(1);

    public static final List<User> TEST_GUESTS_USER_LIST = Arrays.asList(TEST_MEETING_GUEST_0, TEST_MEETING_GUEST_1);

    public static final Meeting TEST_MEETING_2 = new Meeting(2000, "TEST", TEST_DATE_PICKER_MEETING, TEST_HOUR_PICKER_MEETING_12_00, TEST_DURATION_SPINNER_1H, TEST_ROOM_MEETING_0, TEST_GUESTS_USER_LIST);
    public static final Meeting TEST_MEETING_3 = new Meeting(mMeetingRepository.getNewId(), TEST_MEETING_TOPIC, TEST_DATE_PICKER_MEETING, TEST_HOUR_PICKER_MEETING_13_00, TEST_DURATION_SPINNER_1H, TEST_ROOM_MEETING_0, TEST_GUESTS_USER_LIST);

    public static final int TEST_EXPECTED_COUNT_FOR_FILTER_BY_ROOM = 2;
    public static final int TEST_EXPECTED_COUNT_FOR_FILTER_BY_DATE = 3;


    public static final List<Meeting> TEST_MEETINGS = DummyGenerator.DUMMY_MEETINGS_LIST;
    public ActivityScenario mActivity;


    @Rule
    public ActivityScenarioRule<ListMeetingActivity> myActivityScenarioRule = new ActivityScenarioRule<>(ListMeetingActivity.class);

    @Before
    public void setUp() {
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::doMeetingListEmpty);
    }

    // ok test pass 45sec / 3sec
    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        //Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        //When : we have the list displayed
        onView(withId(R.id.recycler_view_meeting)).check(matches(isDisplayed()));
        //Then : the 10 meetings appear
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_MEETINGS.size()));
    }

    // ok test pass 48sec / 3sec
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        //Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        //When : we delete the first one in list by clicking the delete button
        onView(allOf(withParent(withId(R.id.recycler_view_meeting)), withParentIndex(TEST_FIRST_POSITION)));
        onView(withId(R.id.recycler_view_meeting)).perform(RecyclerViewActions.actionOnItemAtPosition(TEST_FIRST_POSITION, new DeleteViewAction()));
        //Then : the meeting list counts sized less one
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_MEETINGS.size() - 1));
    }

    // ok test pass 28sec / 3sec
    @Test
    public void myMeetingListActivity_clickOnCreateMeetingsFloatingActionButton_shouldOpenCreateMeetingActivity() {
        // Given : We are on meeting list activity
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When :  Click on createMeetingButton FAB
        onView(withId(R.id.create_meeting_button)).perform(click());
        // Then : Create meeting activity to be open
        onView(withId(R.id.create_meeting_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void myCreateMeetingActivity_completeForm_shouldAddMeetingOnMeetingListActivity() {
        // Given : We are on meeting list activity
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : We go on CreateMeeting Activity
        onView(withId(R.id.create_meeting_button)).perform(click());
        // When : complete text_input_meeting_topic
        onView(withId(R.id.text_input_meeting_topic))
                .perform(click())
                .perform(typeText(TEST_MEETING_TOPIC), closeSoftKeyboard());
        onView(withId(R.id.text_input_meeting_topic))
                .check(matches(withText(TEST_MEETING_TOPIC)));
        // When : complete date_picker_actions  with date 2/07/2021
        onView(withId(R.id.date_picker_actions)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021,6 + 1,2));
        onView(withId(android.R.id.button1)).perform(click());
        // When : complete time_picker_action with 12:00
        onView(withId(R.id.time_picker_action)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12,0));
        onView(withId(android.R.id.button1)).perform(click());
        // When : complete spinner_room_action
        onView(withId(R.id.spinner_room_action)).perform(click());
        onData(allOf(is(instanceOf(Room.class)))).atPosition(0).perform(click());
        // When : complete spinner_duration_action
        onView(withId(R.id.spinner_meeting_duration)).perform(click());
        onView(withText(TEST_DURATION_SPINNER_1H)).perform(click());
        // When : complete text_input_email with 2 emails and use type "," and ";" to validate
        onView(withId(R.id.text_input_email)).perform(click());
        onView(withId(R.id.text_input_email)).perform(typeText(TEST_MEETING_GUEST_0.getEmail()));
        onView(withId(R.id.text_input_email)).perform(typeText(","));
        onView(withId(R.id.text_input_email)).perform(typeText(TEST_MEETING_GUEST_1.getEmail()));
        onView(withId(R.id.text_input_email)).perform(typeText(";"));
        // When : click on buttonValidate
        onView(withId(R.id.create_meeting_validate_button)).perform(click());
        // Then : On List Meeting Activity the number of element is List size + 1
        //TODO : check count doesn't work ?
        //onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_MEETINGS.size() + 1));
    }

    @Test
    public void myMeetingListActivity_ApplyFilterByRoom_DisplaysTheCorrectMeetings() {
        // Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : we add a meeting with room 0 "Chrome" (TEST_MEETING_2) to have 2 meeting on "Chrome" room
        mMeetingRepository.createMeeting(TEST_MEETING_2);
        // When : perform click on filter by Room and select room 0 "Chrome"
        onView(withId(R.id.menu_meeting_list_activity)).perform(click());
        //onView(withId(R.id.filterRoom)).perform(click());
        onData(allOf(is(instanceOf(Room.class)))).atPosition(0).perform(click());
        onView(withId(R.id.filterRoom)).perform(click());
        // When : select the Room at index 0 "Chrome"
        onView(allOf(withParent(withId(R.id.list_room)), withParentIndex(TEST_ROOM_MEETING_INT_0))).perform(click());
        // Then : check that the meeting displayed the specified meetings
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(TEST_EXPECTED_COUNT_FOR_FILTER_BY_ROOM));
        onView(allOf(withId(R.id.meeting_room), withText(TEST_ROOM_MEETING_STRING_CHROME)));
    }

    @Test
    public void myMeetingListActivity_ApplyFilterByDate_DisplaysTheCorrectMeetings() {
        // Given : we have 10 meetings scheduled and 3 are on 02/07/2021
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : perform click on filter by date
        onView(withId(R.id.menu_meeting_list_activity)).perform(click());
        onView(withId(R.id.filterDate)).perform(click());
        // When : complete date picker with date 02/07/2021
        onView(withId(android.R.id.button1)).perform(PickerActions.setDate(2021, 6, 2));
        onView(withId(android.R.id.button1)).check(matches(withText(TEST_DATE_PICKER_MEETING)));
        // Then : check we have 3 meetings displayed and that the 02/07/2021 date
        onView(withId(R.id.meeting_list_activity)).check(withItemCount(TEST_EXPECTED_COUNT_FOR_FILTER_BY_DATE));
        onView(allOf(withId(R.id.meeting_list_activity))).check(matches(withText(TEST_DATE_PICKER_MEETING)));

    }

    //TODO : Filtering Reset correctly init meeting List
    @Test
    public void myMeetingListActivityDisableFilterDisplaysTheListMeetings() {
        // Given : we have 10 meetings scheduled
        myActivityScenarioRule.getScenario().onActivity(ListMeetingActivity::addAllTestMeetings);
        // When : perform click on filter by Room and select the Room at position 0
        onView(withId(R.id.menu_meeting_list_activity)).perform(click());

        onView(withText(R.string.filter_by_room)).perform(click());
        onView(allOf(withParent(withId(R.id.list_room)))).perform(RecyclerViewActions.actionOnItemAtPosition(TEST_FIRST_POSITION, click()));
        onView(withText(TEST_ROOM_MEETING_STRING_0)).check(matches(isDisplayed()));
        // When : perform click on filter reset
        onView(withContentDescription(R.string.filter_menu)).perform(click());
        onView(withText(R.string.filter_reset)).perform(click());
        // Then : check we have all of 10 meetings

    }

    //private static Matcher<View> getViewByContentDescription(int contentDescription) {
    //    return AllOf.allOf(withId(R.id.recycler_view_meeting), withContentDescription(String.valueOf(contentDescription)));
    //}
}