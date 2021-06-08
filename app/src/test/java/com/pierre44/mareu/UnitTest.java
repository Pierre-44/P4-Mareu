package com.pierre44.mareu;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.DummyMeetingGenerator;
import com.pierre44.mareu.repository.MeetingRepository;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    private MeetingRepository mMeetingRepository;

    @Before
    public void setup() {
        mMeetingRepository = DI.getNewInstanceMeetingRepository();
    }

    // Check that the getMeetings method give the expected list of Meetings
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = mMeetingRepository.getMeeting();
        List<Meeting> expectedNeighbours = DummyMeetingGenerator.DUMMY_MEETINGS;
        Assert.assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    // Check that the deleteMeeting method removes Meeting from the list of Meetings
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = mMeetingRepository.getMeeting().get(0);
        mMeetingRepository.deleteMeeting(meetingToDelete);
        assertFalse(mMeetingRepository.getMeeting().contains(meetingToDelete));
    }

    // Check that the addMeeting method creates a new neighbor
    @Test
    public void createMeetingsWithSuccess() {
        int nbMeeting = mMeetingRepository.getMeeting().size();
        Meeting meetingToAdd = mMeetingRepository.getMeeting().get(0);
        mMeetingRepository.createMeeting(meetingToAdd);
        assertEquals(mMeetingRepository.getMeeting().size(), nbMeeting + 1);
    }
    
    //TODO : Check that the filterByDate method is working correctly

    //TODO : Check that the filterByRoom method is working correctly

}