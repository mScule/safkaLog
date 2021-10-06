package com.ahkera.safkalog.diary;

import androidx.annotation.NonNull;

import com.ahkera.safkalog.util.StringFormat;

import java.util.Calendar;
import java.util.ArrayList;

/**
 * Used for holding diary logs and the date where these diary logs take place.
 * @author Vilhelm
 */
public class DiaryDate {

    private ArrayList<DiaryLog> logs;
    private final TimeStamp date;
    private int kcalSum;

    public DiaryDate() {
        Calendar calendar = Calendar.getInstance();

        date = new TimeStamp(
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.YEAR)
        );

        logs = new ArrayList();
        kcalSum = 0;
    }

    private void refreshKcalTotal() {
        kcalSum = 0;

        for(DiaryLog log : logs)
            kcalSum += log.getEatableUnit().getKcal();
    }

    public void addLog(DiaryLog log) {
        logs.add(log);
        refreshKcalTotal();
    }

    public void removeLog(int i) {
        logs.remove(i);
        refreshKcalTotal();
    }

    /** @return The logs of the date. Use this only for accessing information. */
    public final ArrayList<DiaryLog> getLogs() { return  logs; }

    /** @return The date in formatted form */
    public String getDate() {
        return StringFormat.date(date.getDay(), date.getMonth(), date.getYear());
    }

    /** @return The day of the month as an integer value */
    public int getDay() { return date.getDay(); }

    /** @return The total amount of kilocalories gained in the date */
    public int getKcalTotal() { return kcalSum; }

    @NonNull
    @Override
    public String toString() {
        return date.toString() + " | " + getKcalTotal() + "kcal";
    }
}
