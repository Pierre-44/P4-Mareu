package com.pierre44.mareu.model;

import java.util.Objects;

/**
 * Created by pmeignen on 18/05/2021.
 */
public class Room {

    private long id;
    private String roomName;

    /**
     * Instantiates a new Room.
     *
     * @param id       the id
     * @param roomName the room name
     */
    public Room(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets room name.
     *
     * @return the room name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets room name.
     *
     * @param roomName the room name
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}