package com.ahkera.safkalog.util;

/**
 * Contains static methods that can be used for formatting values to specific formats.
 * @author Vilhelm
 */
public class StringFormat {

    /** @return The time in 00:00 format */
    public static String time(int hours, int minutes) {
        String stringHours = "", stringMinutes = "";

        if(hours < 10)
            stringHours += '0';

        if(minutes < 10)
            stringMinutes += '0';

        stringHours   += Integer.toString(hours);
        stringMinutes += Integer.toString(minutes);

        return stringHours + ':' + stringMinutes;
    }

    /** @return The date in 00.00.0000 format */
    public static String date(int day, int month, int year) {
        String stringDay = "", stringMonth = "", stringYear = Integer.toString(year);

        if(day < 10)
            stringDay += '0';

        if(month < 10)
            stringMonth += '0';

        stringDay   += Integer.toString(day);
        stringMonth += Integer.toString(month);

        return stringDay + '.' + stringMonth + '.' + stringYear;
    }
}
