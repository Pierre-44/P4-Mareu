package com.pierre44.mareu.repository;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pmeignen on 19/05/2021.
 */
public abstract class DummyMeetingGenerator {

    // TODO : définir le bon format date et durée ?
    // voir  : java.text.DateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy'T'HH:mm'Z'");

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room(1,"Chrome", R.drawable.ic_icons8_chrome_48),
            new Room(2,"Safari",R.drawable.ic_icons8_chrome_48),
            new Room(3,"Edge",R.drawable.ic_icons8_chrome_48),
            new Room(4,"Opera",R.drawable.ic_icons8_chrome_48)
            //...10 salles
    );

    public static List<String> DUMMY_USER = Arrays.asList(
            "alice@lamzone.com",
            "emma@lamzone.com",
            "eric@lamzone.com"
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1,"Meeting 1","01/06/2021 08:00", "00H45", DUMMY_ROOMS.get(1), 2,DUMMY_USER),
            new Meeting(2,"Meeting 2","01/06/2021 10:00", "01H00", DUMMY_ROOMS.get(2),1,DUMMY_USER),
            new Meeting(3,"Meeting 3","01/06/2021 14:00", "00H45", DUMMY_ROOMS.get(3),2,DUMMY_USER),
            new Meeting(4,"Meeting 4","02/06/2021 16:00", "01H00", DUMMY_ROOMS.get(4),1,DUMMY_USER),
            new Meeting(5,"Meeting 5","02/06/2021 08:00", "00H45", DUMMY_ROOMS.get(5),2,DUMMY_USER),
            new Meeting(6,"Meeting 6","02/06/2021 10:00", "01H00", DUMMY_ROOMS.get(6),1,DUMMY_USER),
            new Meeting(7,"Meeting 7","02/06/2021 14:00", "00H45", DUMMY_ROOMS.get(7),2,DUMMY_USER),
            new Meeting(8,"Meeting 8","03/06/2021 16:00", "01H00", DUMMY_ROOMS.get(8),1,DUMMY_USER),
            new Meeting(9,"Meeting 9","03/06/2021 08:00", "00H45", DUMMY_ROOMS.get(9),2,DUMMY_USER),
            new Meeting(10,"Meeting 10","03/06/2021 10:00", "01H00", DUMMY_ROOMS.get(10),1,DUMMY_USER),
            new Meeting(11,"Meeting 11","04/06/2021 08:00", "00H45", DUMMY_ROOMS.get(1),2,DUMMY_USER),
            new Meeting(12,"Meeting 12","04/06/2021 10:00", "01H00", DUMMY_ROOMS.get(2),1,DUMMY_USER)
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}