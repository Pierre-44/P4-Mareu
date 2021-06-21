package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;

import java.util.List;

/**
 * Created by pmeignen on 19/05/2021.
 */
public interface MeetingRepository {

    /**
     * Gets meeting.
     *
     * @return the meeting List
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

    /**
     * Gets rooms.
     *
     * @return the List of rooms
     */
    List<Room> getRooms();

    /**
     * organized meeting
     *
     * @param meeting to organized
     */
    void organizeMeeting(Meeting meeting);

    /**
     * organize a meeting with :
     *
     * @param meetingId         The Id of meeting
     * @param meetingTopic      The topic of meeting
     * @param meetingStartDate  The meeting start date
     * @param meetingStartTime  The meeting start time
     * @param meetingDuration   The meeting duration
     * @param meetingRoom       The Room Object
     * @param guestsList        The guests List
     */
    void organizeMeeting(long meetingId, String meetingTopic, String meetingStartDate, String meetingStartTime, String meetingDuration, Room meetingRoom, List<User> guestsList);

    long getNewId();

}
