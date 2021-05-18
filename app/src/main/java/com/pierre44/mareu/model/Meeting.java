package com.pierre44.mareu.model;

import java.security.PrivateKey;
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
    private long id;

    /**
     * The topic of meeting
     */
    private String meetingTopic;

    /**
     * The hour time of meeting
     */
    private Date meetingTime;

    /**
     * The date of meeting
     */
    private Date meetingDate;

    /**
     * The room of meeting
     */
    private Room meetingRoom;

    /**
     * The user host of meeting
     */
    private User host;

    /**
     * The list of users of meeting
     */
    private List<User> guests;

    public Meeting(long id, String meetingTopic, Date meetingTime, Date meetingDate, Room meetingRoom, User host, List<User> guests) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.meetingTime = meetingTime;
        this.meetingDate = meetingDate;
        this.meetingRoom = meetingRoom;
        this.host = host;
        this.guests = guests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public Date getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Room getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(Room meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public List<User> getGuests() {
        return guests;
    }

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
