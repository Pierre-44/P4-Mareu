package com.pierre44.mareu.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by pmeignen on 18/05/2021.
 */
public class Meeting {

    /**
     * The id of meeting
     */
    private int id;

    /**
     * The topic of meeting
     */
    private String meetingTopic;

    /**
     * The meeting start time in ms form 01/01/1970
     */
    private String meetingStartTime;

    /**
     * The meeting end time in ms form 01/01/1970
     */
    private String meetingEndTime;

    /**
     * The room of meeting
     */
    private Room meetingRoom;

    /**
     * The user host of meeting
     */
    private String host;

    /**
     * The list of users of meeting
     */
    private List<User> guests;


    public Meeting(int id, String meetingTopic, String meetingStartTime, String meetingEndTime, Room meetingRoom, String host, List<User> guests) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
        this.meetingRoom = meetingRoom;
        this.host = host;
        this.guests = guests;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
     * Gets meeting time.
     *
     * @return the meeting time
     */
    public String getMeetingEndTime() {
        return meetingEndTime;
    }

    /**
     * Sets meeting time.
     *
     * @param meetingEndTime the meeting time
     */
    public void setMeetingEndTime(String meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    /**
     * Gets meeting date.
     *
     * @return the meeting date
     */
    public String getMeetingStartTime() {
        return meetingStartTime;
    }

    /**
     * Sets meeting date.
     *
     * @param meetingStartTime the meeting date
     */
    public void setMeetingStartTime(String meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    /**
     * Gets meeting room.
     *
     * @return the meeting room
     */
    public Room getMeetingRoom() {
        return meetingRoom;
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
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets host.
     *
     * @param host the host
     */
    public void setHost(String host) {
        this.host = host;
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
