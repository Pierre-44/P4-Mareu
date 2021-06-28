package com.pierre44.mareu.events;

import com.pierre44.mareu.model.Meeting;

/**
 * Created by pmeignen on 24/06/2021.
 */
public class GetMeetingDetail {

    public Meeting meeting;

    public GetMeetingDetail(Meeting meeting) {
        this.meeting = meeting;
    }
}
