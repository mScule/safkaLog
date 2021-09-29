package com.ahkera.safkalog.util;

public class StringFormat {
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
}
