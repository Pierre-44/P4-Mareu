package com.pierre44.mareu;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.DummyGenerator;
import com.pierre44.mareu.repository.MeetingRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @After
    public void reset() {
        mMeetingRepository.getMeeting().clear();
    }

    // Check that the getMeetings method give the expected list of Meetings
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = mMeetingRepository.getMeeting();
        List<Meeting> expectedNeighbours = DummyGenerator.DUMMY_MEETINGS_LIST;
        assertTrue(meetings.containsAll(expectedNeighbours));
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

    //Check that the filterByDate method is working correctly
    @Test
    public void filterMeetingByDateWithSuccess() {
        List<Meeting> meetings = new ArrayList<>();
        String filterDate = "01/06/2021";
        meetings.addAll(mMeetingRepository.filterByDate(filterDate));
        for (Meeting m : meetings){
            assertTrue(m.getMeetingStartDate().equals(filterDate));
        }
    }

    //Check that the filterByRoom method is working correctly
    @Test
    public void filterMeetingByRoomWithSuccess() {
        List<Meeting> meetings = new ArrayList<>();
        Room filterRoom = mMeetingRepository.getRooms().get(1);
        meetings.addAll(mMeetingRepository.filterByRoom(filterRoom));
        for (Meeting m : meetings){
            assertTrue(m.getMeetingRoom() == filterRoom);
        }
    }
}