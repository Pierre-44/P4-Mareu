package com.pierre44.mareu;

import com.pierre44.mareu.utils.UtilsTools;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by pmeignen on 20/07/2021.
 */
public class UtilsToolsTest {

    final Calendar mCalendar = Calendar.getInstance();

    @Test
    public void formatDateWithSuccess() {
        mCalendar.set(2021,2 - 1 ,1);
        assertEquals("01/02/2021", UtilsTools.dateFormat(mCalendar, UtilsTools.DATE_FORMAT_DD_MM_YYYY));
    }

    @Test
    public void formatTimeWithSuccess() {
        mCalendar.set(Calendar.HOUR_OF_DAY,9);
        mCalendar.set(Calendar.MINUTE,9);
        assertEquals("09:09", UtilsTools.dateFormat(mCalendar,UtilsTools.TIME_FORMAT_HH_MM));

    }
}
