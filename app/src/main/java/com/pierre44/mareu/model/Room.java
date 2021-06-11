package com.pierre44.mareu.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by pmeignen on 18/05/2021.
 */
public class Room implements Serializable {

    private long id;
    private String roomName;
    private int roomImage;

    /**
     * Instantiates a new Room.
     *
     * @param id       the id
     * @param roomName the room name
     *
     */
    public Room(int id, String roomName,int roomImage) {
        this.id = id;
        this.roomName = roomName;
        this.roomImage = roomImage;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getRoomId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setRoomId(long id) {
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

    /**
     * Gets room image.
     *
     * @return the room image
     */
    public int getRoomImage() {
        return roomImage;
    }

    /**
     * Sets room image.
     *
     * @param roomImage the room image
     */
    public void setRoomImage(int roomImage) {
        this.roomImage = roomImage;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}