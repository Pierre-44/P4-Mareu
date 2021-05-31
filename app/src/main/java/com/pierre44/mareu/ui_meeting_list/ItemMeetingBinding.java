package com.pierre44.mareu.ui_meeting_list;

import com.pierre44.mareu.model.Room;

import java.util.List;

/**
 * Created by pmeignen on 28/05/2021.
 */
public class ItemMeetingBinding {

    private int mRoomImage;
    private String mMeetingTopic;
    private String mMeetingStartTime;
    private String mMeetingDuration;
    private Room mMeetingRoom;
    private List<String> mGuests;

    public ItemMeetingBinding(int roomImage, String meetingTopic, String meetingStartTime, String meetingDuration, Room meetingRoom, List<String> guests) {
        mRoomImage = roomImage;
        mMeetingTopic= meetingTopic;
        mMeetingStartTime = meetingStartTime;
        mMeetingDuration = meetingDuration;
        mMeetingRoom = meetingRoom;
        mGuests = guests;
    }

    public int getRoomImage() {
        return mRoomImage;
    }

    public String getMeetingTopic() {
        return mMeetingTopic;
    }

    public String getMeetingStartTime() {
        return mMeetingStartTime;
    }

    public String getMeetingDuration() {
        return mMeetingDuration;
    }

    public Room getMeetingRoom() {
        return mMeetingRoom;
    }

    public List<String> getGuests() {
        return mGuests;
    }
}
