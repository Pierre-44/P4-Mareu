package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pmeignen on 19/05/2021.
 */
public class DummyMeetingRepository implements MeetingRepository{

    /**
     * Generate a new ID
     */
    long newId;

    private final List<Meeting> mMeetings = DummyGenerator.generateMeetings();
    private final List<User> mUsers = DummyGenerator.generateUsers();
    private final List<Room> mRooms = DummyGenerator.generateRooms();
    public List<Meeting> filteredMeeting;

    @Override
    public long getNewId() {
        newId = new Random().nextInt(1000);
        return newId;
    }

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public List<Room> getRooms() {
        return mRooms;
    }

    /**
     * Get meeting room from its id
     *
     * @param id of room
     * @return the room
     */
    @Override
    public Room getRoomById(long id) {
        for (int i = 0; i < mRooms.size(); i++) {
            if (mRooms.get(i).getRoomId() == id) return mRooms.get(i);
        }
        return null;
    }


    @Override
    public List<Meeting> getMeetingsForFilterMeetingRoom(long meetingRoomId) {
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            if (mRooms.get(i).getRoomId() == meetingRoomId) {
                meetings.add(mMeetings.get(i));
            }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsForFilterMeetingDate(long dayTimeStamp) {
        return null;
    }

    @Override
    public List<Meeting> filterByRoom(Room room) {
        filteredMeeting = new ArrayList<>();
        for (Meeting m : getMeetings()) {
            if (m.getMeetingRoom() == room) {
                filteredMeeting.add(m);
            }
        }
        return filteredMeeting;
    }

    @Override
    public List<Meeting> filterByDate(String date) {
        filteredMeeting = new ArrayList<>();
        for (Meeting m : getMeetings()) {
            if (m.getMeetingStartDate().equals(date)) {
                filteredMeeting.add(m);
            }
        }
        return filteredMeeting;
    }

    @Override
    public void organizeMeeting(Meeting meeting) {
        long meetingId = meeting.getMeetingId();
        String meetingTopic = meeting.getMeetingTopic();
        String meetingStartDate = meeting.getMeetingStartDate();
        String meetingStartTime = meeting.getMeetingStartTime();
        String meetingDuration = meeting.getMeetingDuration();
        Room meetingRoom = meeting.getMeetingRoom();
        List<User> guestsList = meeting.getMeetingGuests();
        this.organizeMeeting(meetingId,meetingTopic,meetingStartDate,meetingStartTime,meetingDuration,meetingRoom,guestsList);
    }

    @Override
    public void organizeMeeting(
            long meetingId, String meetingTopic, String meetingStartDate, String meetingStartTime, String meetingDuration, Room meetingRoom, List<User> guestsList) {
        Meeting newMeeting = new Meeting(meetingId, meetingTopic, meetingStartDate, meetingStartTime, meetingDuration, meetingRoom, guestsList);
        mMeetings.add(newMeeting);
    }
}