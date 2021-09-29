package com.ahkera.safkalog.diary;

import androidx.annotation.NonNull;

import com.ahkera.safkalog.eatable.EatableUnit;
import com.ahkera.safkalog.util.StringFormat;

import java.util.Calendar;

public class DiaryLog {
    private EatableUnit eatableUnit;
    private String time;

    public DiaryLog(EatableUnit eatableUnit){
        this.eatableUnit = eatableUnit;

        int hours, minutes;
        hours = Calendar.getInstance().get(Calendar.HOUR);
        minutes = Calendar.getInstance().get(Calendar.MINUTE);

        this.time = StringFormat.time(hours, minutes);
    }

    public EatableUnit getEatableUnit() {
        return eatableUnit;
    }

    public String getTime() {
        return time;
    }

    @NonNull
    @Override
    public String toString() {
        return
            eatableUnit.getName() + " " +
            eatableUnit.getKcal() + "kcal " + getTime();
    }
}
