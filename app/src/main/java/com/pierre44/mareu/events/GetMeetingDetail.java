package com.pierre44.mareu.events;

import com.pierre44.mareu.model.Meeting;

public class GetMeetingDetail {

    public Meeting meeting;

    public GetMeetingDetail(Meeting meeting) {
        this.meeting = meeting;
    }
}

