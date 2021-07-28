package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyMeetingRepository implements MeetingRepository {

    /**
     * Generate a new ID
     */
    long newId;

    private final List<Meeting> mMeetings = DummyGenerator.generateMeetings();
    private final List<User> mGuests = DummyGenerator.generateUsers();
    private final List<Room> mRooms = DummyGenerator.generateRooms();

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
    public List<Meeting> filterByRoom(Room room) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting m = mMeetings.get(i);
            if (m.getMeetingRoom() == room) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }

    @Override
    public List<Meeting> filterByDate(String date) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting m = mMeetings.get(i);
            if (m.getMeetingStartDate().contentEquals(date)) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }

    /**
     *
     * @param meeting to organized
     */
    @Override
    public void organizeMeeting(Meeting meeting) {
        long meetingId = meeting.getMeetingId();
        String meetingTopic = meeting.getMeetingTopic();
        String meetingStartDate = meeting.getMeetingStartDate();
        String meetingStartTime = meeting.getMeetingStartTime();
        String meetingDuration = meeting.getMeetingDuration();
        Room meetingRoom = meeting.getMeetingRoom();
        List<User> guestsList = meeting.getMeetingGuests();
        this.organizeMeeting(meetingId, meetingTopic, meetingStartDate, meetingStartTime, meetingDuration, meetingRoom, guestsList);
    }

    @Override
    public List<User> getGuests(String meeting) {
        return mGuests;
    }

    /**
     *
     * @param meetingId        The Id of meeting
     * @param meetingTopic     The topic of meeting
     * @param meetingStartDate The meeting start date
     * @param meetingStartTime The meeting start time
     * @param meetingDuration  The meeting duration
     * @param meetingRoom      The Room Object of meeting
     * @param guestsList       The guests List of meeting
     */
    @Override
    public void organizeMeeting(
            long meetingId, String meetingTopic, String meetingStartDate, String meetingStartTime, String meetingDuration, Room meetingRoom, List<User> guestsList) {
        Meeting newMeeting = new Meeting(meetingId, meetingTopic, meetingStartDate, meetingStartTime, meetingDuration, meetingRoom, guestsList);
        mMeetings.add(newMeeting);
    }
}