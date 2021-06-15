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
public class DummyMeetingRepository implements MeetingRepository {

    /**
     * Generate a new ID
     */
    long newId;

    private final List<Meeting> meetings = DummyGenerator.generateMeetings();
    private final List<User> users = DummyGenerator.generateUsers();
    private final List<Room> rooms = DummyGenerator.generateRooms();

    public List<Meeting> filteredMeeting;

    @Override
    public long getNewId() {
        newId = new Random().nextInt(1000);
        return newId;
    }

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

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public List<Meeting> filterByRoom(Room room) {
        filteredMeeting = new ArrayList<>();
        for (Meeting m : getMeeting()) {
            if (m.getMeetingRoom() == room) {
                filteredMeeting.add(m);
            }
        }
        return filteredMeeting;
    }

    @Override
    public List<Meeting> filterByDate(String date) {
        filteredMeeting = new ArrayList<>();
        for (Meeting m : getMeeting()) {
            if (m.getMeetingStartDate().equals(date)) {
                filteredMeeting.add(m);
            }
        }
        return filteredMeeting;
    }
}

