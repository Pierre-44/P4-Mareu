package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;

import java.util.List;

/**
 * Created by pmeignen on 19/05/2021.
 */
public interface MeetingRepository {

    /**
     * Gets meeting.
     *
     * @return the meeting to get
     */
    List <Meeting> getMeeting();

    /**
     * Filter by room list.
     *
     * @param room the room
     * @return the meeting list filtered by room
     */
    List<Meeting> filterByRoom (Room room);

    /**
     * Filter by date list.
     *
     * @param date the date
     * @return the meeting list filtered by date
     */
    List<Meeting> filterByDate(String date);

    /**
     * Create meeting.
     *
     * @param meeting the meeting created
     */
    void createMeeting(Meeting meeting);

    /**
     * Delete meeting.
     *
     * @param meeting the meeting deleted
     */
    void deleteMeeting(Meeting meeting);

    List<Room> getRooms();

    long getNewId();

}
