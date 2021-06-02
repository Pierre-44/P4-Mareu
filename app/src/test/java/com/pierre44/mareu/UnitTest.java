package com.pierre44.mareu;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.DummyMeetingGenerator;
import com.pierre44.mareu.repository.MeetingRepository;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

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
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    // Check that the deleteMeeting method removes Meeting from the list of Meetings
    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting meetingToDelete = mMeetingRepository.getMeeting().get(0);
        mMeetingRepository.deleteMeeting(meetingToDelete);
        assertFalse(mMeetingRepository.getMeeting().contains(meetingToDelete));
    }

    // Check that the addMeeting method creates a new neighbor
    @Test
    public void createNeighboursWithSuccess() {
        int nbNeighbours = serviceApi.getNeighbours().size();
        Neighbour neighbourToAdd = serviceApi.getNeighbours().get(0);
        serviceApi.createNeighbour(neighbourToAdd);
        assertEquals(serviceApi.getNeighbours().size(), nbNeighbours + 1);


}