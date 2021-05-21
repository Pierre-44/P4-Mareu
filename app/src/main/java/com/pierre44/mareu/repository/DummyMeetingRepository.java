package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;

import java.util.List;

/**
 * Created by pmeignen on 19/05/2021.
 */
public class DummyMeetingRepository implements MeetingRepository {

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }
}
