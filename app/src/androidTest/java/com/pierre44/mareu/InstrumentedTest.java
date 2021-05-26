package com.pierre44.mareu;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pierre44.mareu", appContext.getPackageName());
    }


    //TODO : instrumental test
    /**
     @Test public void myMeetingList_shouldNotBeEmpty(){
     // First scroll to the position that needs to be matched and click on it.
     onView(getViewByContentDescription(MeetingFragment.MEETINGS_FRAGMENT_LIST))
     .check(matches(hasMinimumChildCount(1)));
     }

     @Test
     public void myMeetingsList_deleteAction_shouldRemoveItem() {
     // Given : We remove the element at position N
     onView(getViewByContentDescription(MeetingFragment.MEETINGS_FRAGMENT_LIST))
     .check(withItemCount(ITEMS_COUNT));
     // When perform a click on a delete icon
     onView(getViewByContentDescription(MeetingFragment.MEETINGS_FRAGMENT_LIST))
     .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
     // Then : the number of element is XX

     @Test
     public void myMeetingsList_FabAction_shouldOpenActivity_add_neighbour() {
     // Given : We Are On Neighbour list
     onView(getViewByContentDescription(MeetingFragment.MEETINGS_FRAGMENT_LIST));
     // When : Click Neighbour Fab Button
     onView(withId(R.id.add_neighbour)).perform(click());
     // Then : Activity_add_neighbour to be open
     onView(withId(R.id.activity_add_neighbour))
     .check(matches(isDisplayed()));
     }

     private static Matcher<View> getViewByContentDescription(int contentDescription) {
     return allOf(withId(R.id.fragment_list_meetings), withContentDescription(String.valueOf(contentDescription)));
     }
     */

    
}