package com.pierre44.mareu.events;

import androidx.annotation.NonNull;

import com.pierre44.mareu.model.Room;

/**
 * Created by pmeignen on 10/06/2021.
 */
public class FilterByRoomEvent {


    private final Room room;


    public FilterByRoomEvent(@NonNull Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FilterByRoomEvent that = (FilterByRoomEvent) obj;
        return room == that.room;
    }
}

