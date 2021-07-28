package com.pierre44.mareu.repository;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyGenerator {

    public static List<Room> DUMMY_ROOMS_LIST = Arrays.asList(
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

    public static List<User> DUMMY_USERS_LIST = Arrays.asList(
            new User(1, "Pierre", "pierre@lamzone.com"),
            new User(2, "Deborah", "deborah@lamzone.com"),
            new User(3, "Agathe", "agathe@lamzone.com")
    );

    public static List<Meeting> DUMMY_MEETINGS_LIST = Arrays.asList(
            new Meeting(1, "Réunion 1", "01/07/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(0), DUMMY_USERS_LIST),
            new Meeting(2, "Réunion 2", "01/07/2021", "10:00", "01H00", DUMMY_ROOMS_LIST.get(1), DUMMY_USERS_LIST),
            new Meeting(3, "Réunion 3 ", "01/07/2021", " 14:00", "00H45", DUMMY_ROOMS_LIST.get(2), DUMMY_USERS_LIST),
            new Meeting(4, "Réunion 4 et une longue description", "02/07/2021", " 08:00", "01H00", DUMMY_ROOMS_LIST.get(3), DUMMY_USERS_LIST),
            new Meeting(5, "Réunion 5 et une longue description", "02/07/2021", "10:00", "00H45", DUMMY_ROOMS_LIST.get(4), DUMMY_USERS_LIST),
            new Meeting(6, "Réunion 6 et une longue description", "02/07/2021", "16:00", "01H00", DUMMY_ROOMS_LIST.get(5), DUMMY_USERS_LIST),
            new Meeting(7, "Réunion 7", "05/07/2021", "14:00", "00H45", DUMMY_ROOMS_LIST.get(6), DUMMY_USERS_LIST),
            new Meeting(8, "Réunion 8", "05/07/2021", "16:00", "01H00", DUMMY_ROOMS_LIST.get(7), DUMMY_USERS_LIST),
            new Meeting(9, "Réunion 9", "06/07/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(8), DUMMY_USERS_LIST),
            new Meeting(10, "Réunion 10 et une longue description", "06/07/2021", "10:00", "01H00", DUMMY_ROOMS_LIST.get(9), DUMMY_USERS_LIST),
            new Meeting(11, "Réunion 11 et une longue description", "07/07/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(0), DUMMY_USERS_LIST),
            new Meeting(12, "Réunion 12 et une longue description", "07/07/2021", "10:00", "01H30", DUMMY_ROOMS_LIST.get(1), DUMMY_USERS_LIST)
    );

    public static final List<String> DURATION_LIST = Arrays.asList("", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00");

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS_LIST);
    }

    public static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS_LIST);
    }

    public static List<User> generateUsers() {
        return new ArrayList<>(DUMMY_USERS_LIST);
    }
}