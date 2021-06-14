package com.pierre44.mareu.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by pmeignen on 18/05/2021.
 */
public class Room implements Serializable {

    private long roomId;
    private String roomName;
    private int roomImage;


    /**
     * Instantiates a new Room.
     *
     * @param roomId    the room id
     * @param roomName  the room name
     * @param roomImage the room image
     */
    public Room(int roomId, String roomName,int roomImage) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomImage = roomImage;
    }


    /**
     * Gets room id.
     *
     * @return the room id
     */
    public long getRoomId() {
        return roomId;
    }


    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(long roomId) {
        this.roomId = roomId;
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
        return roomId == room.roomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}