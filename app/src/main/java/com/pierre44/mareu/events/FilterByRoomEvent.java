package com.pierre44.mareu.events;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.MeetingRepository;

/**
 * Created by pmeignen on 10/06/2021.
 */
public class FilterByRoomEvent {

    public Room room;
    private MeetingRepository mMeetingRepository = DI.getMeetingRepository();

    public FilterByRoomEvent(String room) {
        for (Room r : mMeetingRepository.getRooms()) {
            if (r.getRoomName().equals(room)) {
                this.room = r;
            }
        }
    }
}

