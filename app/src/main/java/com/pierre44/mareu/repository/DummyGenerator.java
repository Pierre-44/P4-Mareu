package com.pierre44.mareu.repository;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pmeignen on 19/05/21.
 */
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
            new User(3, "Agathe", "agathe@lamzone.com"),
            new User(4, "Marc", "marc@lamzone.com"),
            new User(5, "Aurélie", "aurelie@lamzone.com"),
            new User(6, "Hélène", "helene@lamzone.com"),
            new User(7, "Guillaume", "guillaume@lamzone.com"),
            new User(8, "Marine", "marie@lamzone.com"),
            new User(9, "Jordan", "jordan@lamzone.com"),
            new User(10, "Julie", "julie@lamzone.com")
    );

    public static List<Meeting> DUMMY_MEETINGS_LIST = Arrays.asList(
            new Meeting(1, "Meeting 1", "01/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(0), DUMMY_USERS_LIST),
            new Meeting(2, "Meeting 2", "01/06/2021", "10:00", "01H00", DUMMY_ROOMS_LIST.get(1), DUMMY_USERS_LIST),
            new Meeting(3, "Meeting 3 ", "01/06/2021", " 14:00", "00H45", DUMMY_ROOMS_LIST.get(2), DUMMY_USERS_LIST),
            new Meeting(4, "Meeting 4 and a very long description ", "02/06/2021", " 16:00", "01H00", DUMMY_ROOMS_LIST.get(3), DUMMY_USERS_LIST),
            new Meeting(5, "Meeting 5 and a very long description", "02/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(4), DUMMY_USERS_LIST),
            new Meeting(6, "Meeting 6 and a very long description", "02/06/2021", "10:00", "01H00", DUMMY_ROOMS_LIST.get(5), DUMMY_USERS_LIST),
            new Meeting(7, "Meeting 7", "02/06/2021", "14:00", "00H45", DUMMY_ROOMS_LIST.get(6), DUMMY_USERS_LIST),
            new Meeting(8, "Meeting 8", "03/06/2021", "16:00", "01H00", DUMMY_ROOMS_LIST.get(7), DUMMY_USERS_LIST),
            new Meeting(9, "Meeting 9", "03/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(8), DUMMY_USERS_LIST),
            new Meeting(10, "Meeting 10 and a very long description", "03/06/2021", "10:00", "01H00", DUMMY_ROOMS_LIST.get(9), DUMMY_USERS_LIST),
            new Meeting(11, "Meeting 11 and a very long description", "04/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(0), DUMMY_USERS_LIST),
            new Meeting(12, "Meeting 12 and a very long description", "04/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(1), DUMMY_USERS_LIST),
            new Meeting(13, "Meeting 13", "04/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(2), DUMMY_USERS_LIST),
            new Meeting(14, "Meeting 14", "07/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(3), DUMMY_USERS_LIST),
            new Meeting(15, "Meeting 15", "07/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(4), DUMMY_USERS_LIST),
            new Meeting(16, "Meeting 16 and a very long description", "07/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(5), DUMMY_USERS_LIST),
            new Meeting(17, "Meeting 17 and a very long description", "08/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(6), DUMMY_USERS_LIST),
            new Meeting(18, "Meeting 18 and a very long description", "08/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(7), DUMMY_USERS_LIST),
            new Meeting(19, "Meeting 19", "09/06/2021", "08:00", "00H45", DUMMY_ROOMS_LIST.get(8), DUMMY_USERS_LIST),
            new Meeting(20, "Meeting 20", "09/06/2021", "10:00", "01H00", DUMMY_ROOMS_LIST.get(9), DUMMY_USERS_LIST)
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS_LIST);
    }

    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS_LIST);
    }

    static List<User> generateUsers() {
        return new ArrayList<>(DUMMY_USERS_LIST);
    }
}