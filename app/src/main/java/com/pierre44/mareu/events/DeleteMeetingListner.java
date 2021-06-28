package com.pierre44.mareu.events;

import android.view.View;

/**
 * Created by pmeignen on 28/06/2021.
 */
public interface DeleteMeetingListner {
    void MeetingClicked(View imageView, View TextView, int meetingId);

    void deleteMeetingClicked(int meetingId);
}
