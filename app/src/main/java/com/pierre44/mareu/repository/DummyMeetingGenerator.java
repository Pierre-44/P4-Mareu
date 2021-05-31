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
            new Room(1, "Chrome", R.drawable.ic_icons8_chrome_48),
            new Room(2, "Safari", R.drawable.ic_icons8_safari_48),
            new Room(3, "Edge", R.drawable.ic_icons8_ms_edge_48),
            new Room(4, "Opera", R.drawable.ic_icons8_opera_48),
            new Room(5, "Firefox", R.drawable.ic_icons_firefox),
            new Room(6, "Ecosia", R.drawable.ic_icons_ecosia),
            new Room(7, "Chromium", R.drawable.ic_icons_chromium),
            new Room(8, "Konkqueror", R.drawable.ic_icons_konqueror),
            new Room(9, "Brave", R.drawable.ic_icons_brave),
            new Room(10, "Netsurf", R.drawable.ic_icons_netsurf)
    );

    public static List<String> DUMMY_USERS_LIST = Arrays.asList(
            "pierre@lamzone.com",
            "deborah@lamzone.com",
            "agathe@lamzone.com"
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "Meeting 1", "01/06/2021 08:00", "00H45", DUMMY_ROOMS.get(1), DUMMY_USERS_LIST),
            new Meeting(2, "Meeting 2", "01/06/2021 10:00", "01H00", DUMMY_ROOMS.get(2), DUMMY_USERS_LIST),
            new Meeting(3, "Meeting 3", "01/06/2021 14:00", "00H45", DUMMY_ROOMS.get(3), DUMMY_USERS_LIST),
            new Meeting(4, "Meeting 4", "02/06/2021 16:00", "01H00", DUMMY_ROOMS.get(4), DUMMY_USERS_LIST),
            new Meeting(5, "Meeting 5", "02/06/2021 08:00", "00H45", DUMMY_ROOMS.get(5), DUMMY_USERS_LIST),
            new Meeting(6, "Meeting 6", "02/06/2021 10:00", "01H00", DUMMY_ROOMS.get(6), DUMMY_USERS_LIST),
            new Meeting(7, "Meeting 7", "02/06/2021 14:00", "00H45", DUMMY_ROOMS.get(7), DUMMY_USERS_LIST),
            new Meeting(8, "Meeting 8", "03/06/2021 16:00", "01H00", DUMMY_ROOMS.get(8), DUMMY_USERS_LIST),
            new Meeting(9, "Meeting 9", "03/06/2021 08:00", "00H45", DUMMY_ROOMS.get(9), DUMMY_USERS_LIST),
            new Meeting(10, "Meeting 10", "03/06/2021 10:00", "01H00", DUMMY_ROOMS.get(10), DUMMY_USERS_LIST),
            new Meeting(11, "Meeting 11", "04/06/2021 08:00", "00H45", DUMMY_ROOMS.get(1), DUMMY_USERS_LIST),
            new Meeting(12, "Meeting 12", "04/06/2021 10:00", "01H00", DUMMY_ROOMS.get(2), DUMMY_USERS_LIST)
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}