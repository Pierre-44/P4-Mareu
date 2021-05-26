package com.pierre44.mareu;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.DummyMeetingGenerator;
import com.pierre44.mareu.repository.MeetingRepository;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = mMeetingRepository.getMeeting();
        List<Meeting> expectedNeighbours = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }
}