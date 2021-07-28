package com.pierre44.mareu.events;

import com.pierre44.mareu.model.Meeting;

public class DeleteMeetingEvent {

    /**
     * The Meeting to delete
     */
    public Meeting meeting;

    /**
     * Instantiates a new Delete meeting event.
     *
     * @param meeting the meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
