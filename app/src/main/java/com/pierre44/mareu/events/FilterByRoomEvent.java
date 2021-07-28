package com.pierre44.mareu.events;

import androidx.annotation.NonNull;

import com.pierre44.mareu.model.Room;

public class FilterByRoomEvent {

    public Room room;

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
