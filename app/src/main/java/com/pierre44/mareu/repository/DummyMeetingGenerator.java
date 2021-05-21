package com.pierre44.mareu.repository;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pmeignen on 19/05/2021.
 */
public abstract class DummyMeetingGenerator {

    private Object Room;
    private Object Host;
    private Object Guest;
    public static User host;
    public static List<User> guestsList;
    //format date ?
    // java.text.DateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy'T'HH:mm'Z'");


    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room(1,"Chrome"),
            new Room(2,"Safari"),
            new Room(3,"Edge"),
            new Room(4,"Opera")
    );

    public static List<User> DUMMY_USER = Arrays.asList(
            new User(1, "Pierre","pierre@lamzone.com"),
            new User(2, "Marc","marc@lamzone.com"),
            new User(3, "Sonia","sonia@lamzone.com"),
            new User(4, "Francois","françois@lamzone.com"),
            new User(5, "Eric","eric@lamzone.com"),
            new User(6, "Emma","emma@lamzone.com"),
            new User(7, "Thomas","tomas@lamzone.com"),
            new User(8, "Sandrine","sandrine@lamzone.com"),
            new User(9, "Steve","steve@lamzone.com"),
            new User(10, "Alice","alice@lamzone.com")
    );

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            //new Meeting(1,"Meeting 1","01/06/2021 08:00", "01/06/2021 09:00", 1,1,2),
            //new Meeting(2,"Meeting 1","01/06/2021 09:00", "01/06/2021 10:00", 2,2,4),
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETING);
    }


}