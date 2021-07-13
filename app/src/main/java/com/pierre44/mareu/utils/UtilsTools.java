package com.pierre44.mareu.utils;

import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.MeetingRepository;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pmeignen on 01/07/2021.
 */
public abstract class UtilsTools {

    public static final MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    public static final Room MEETING_ROOM_0 = mMeetingRepository.getRooms().get(0);

    public static final String DATE_FORMAT_DD_MM_YYYY = "DD/MM/YYYY";
    public static final String DATE_FORMAT_EEE_MMM_HH_MM = "EEE MMM HH:mm";
    public static final String TIME_FORMAT_HH_MM = "HH:mm";
    private static SimpleDateFormat format = null;
    private static final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();


    /**
     * Date format string to display.
     *
     * @param calendar   the calendar
     * @param dateFormat the date format
     * @return the string
     */
    public static String dateFormat(Calendar calendar, String dateFormat) {
        format = new SimpleDateFormat(dateFormat);
        String formattedDate = format.format(calendar.getTime());
        formattedDate = formattedDate.substring(0,1).toUpperCase() + formattedDate.substring(1);
        return formattedDate;
    }

    /**
     * Date format string to en long .
     *
     * @param timeMillis the time millis
     * @return the string
     */
    public static String dateFormat(long timeMillis) {
        format = new SimpleDateFormat(DATE_FORMAT_EEE_MMM_HH_MM,dateFormatSymbols);
        Date dateTime = new Date(timeMillis);
        dateFormatSymbols.setShortWeekdays(new String[]{"LUN", "MAR", "MER", "JEU", "VEN", "SAM" ,"DIM"});
        dateFormatSymbols.setShortMonths(new String[]{"JAN", "FEV", "MAR", "AVR", "MAI", "JUIN", "JUIL", "AOU", "SEP", "OCT", "NOV", "DEC"});
        return format.format(dateTime);
    }

    /**
     * Time format string to HH:MM
     *
     * @param input the input value HH or MM
     * @return the string
     */
    public static String timeFormat(int input)
    {
        String str = "";
        if (input > 10) {
            str = Integer.toString(input);
        } else {
            str = "0" + Integer.toString(input);
        }
        return str;
    }

    public enum FilterType {
        BY_ROOM, BY_DATE , NONE
    }
}
