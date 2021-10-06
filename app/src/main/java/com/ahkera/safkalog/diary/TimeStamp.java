package com.ahkera.safkalog.diary;

/**
 * Holds a date in immutable form. Contains day of the month, month, and year.
 * @author Vilhelm
 */
public class TimeStamp {

    private final int day, month, year;

    public TimeStamp(int day, int month, int year) {
        this.day   = day;
        this.month = month;
        this.year  = year;
    }

    /** @return The day of the month as an integer value. */
    public int getDay() { return day; }

    /** @return The month of the year as an integer value. */
    public int getMonth() { return month; }

    /** @return The year of the date as an integer value. */
    public int getYear() { return year; }
}
