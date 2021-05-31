package com.pierre44.mareu.events;

import com.pierre44.mareu.model.Meeting;

/**
 * Created by pmeignen on 31/05/2021.
 */
public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }


}
