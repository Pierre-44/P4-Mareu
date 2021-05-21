package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;

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


}
