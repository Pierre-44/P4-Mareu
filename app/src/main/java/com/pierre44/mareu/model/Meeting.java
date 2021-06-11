package com.pierre44.mareu.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by pmeignen on 18/05/2021.
 */
public class Meeting implements Serializable {


    /**
     * The id of meeting
     */
    private long id;

    /**
     * The topic of meeting
     */
    private String meetingTopic;

    /**
     * The meeting start date
     */
    private String meetingStartDate;

    /**
     * The meeting start time
     */
    private String meetingStartTime;

    /**
     * The meeting duration
     */
    private String meetingDuration;

    /**
     * The room of meeting
     */
    private Room meetingRoom;

    /**
     * The list of Strings of meeting
     */
    private List<User> guests;


    /**
     * Instantiates a new Meeting.
     * @param id               the id
     * @param meetingTopic     the meeting topic
     * @param meetingStartDate the meeting start date
     * @param meetingStartTime the meeting start time
     * @param meetingDuration  the meeting duration
     * @param meetingRoom      the meeting room
     * @param guestsList       the guests list
     */

    // Constructor
    public Meeting(long id, String meetingTopic, String meetingStartDate, String meetingStartTime, String meetingDuration, Room meetingRoom, List<User> guestsList) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.meetingStartDate = meetingStartDate;
        this.meetingStartTime = meetingStartTime;
        this.meetingDuration = meetingDuration;
        this.meetingRoom = meetingRoom;
        this.guests = guestsList;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets meeting topic.
     *
     * @return the meeting topic
     */
    public String getMeetingTopic() {
        return meetingTopic;
    }

    /**
     * Sets meeting topic.
     *
     * @param meetingTopic the meeting topic
     */
    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }


    /**
     * Gets meeting start date.
     *
     * @return the meeting start date
     */
    public String getMeetingStartDate() {
        return meetingStartDate;
    }

    /**
     * Sets meeting start date.
     *
     * @param meetingStartDate the meeting start date
     */
    public void setMeetingStartDate(String meetingStartDate) {
        this.meetingStartDate = meetingStartDate;
    }

    /**
     * Gets meeting start time.
     *
     * @return the meeting start time
     */
    public String getMeetingStartTime() {
        return meetingStartTime;
    }

    /**
     * Sets meeting start time.
     *
     * @param meetingStartTime the meeting start time
     */
    public void setMeetingStartTime(String meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    /**
     * Gets meeting duration.
     *
     * @return the meeting duration
     */
    public String getMeetingDuration() {
        return meetingDuration;
    }

    /**
     * Sets meeting duration.
     *
     * @param meetingDuration the meeting duration
     */
    public void setMeetingDuration(String meetingDuration) {
        this.meetingDuration = meetingDuration;
    }

    /**
     * Gets meeting room.
     *
     * @return the meeting room
     */
    public long getMeetingRoom() {
        return meetingRoom.getRoomId();
    }

    /**
     * Sets meeting room.
     *
     * @param meetingRoom the meeting room
     */
    public void setMeetingRoom(Room meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    /**
     * Gets guests.
     *
     * @return the guests
     */
    public List<User> getGuests() {
        return guests;
    }

    /**
     * Sets guests.
     *
     * @param guests the guests
     */
    public void setGuests(List<User> guests) {
        this.guests = guests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
