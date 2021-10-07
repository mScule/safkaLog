package com.ahkera.safkalog.diary;

import androidx.annotation.NonNull;

import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.util.StringFormat;

import java.util.Calendar;

/**
 * Contains the information of single consumed eatableUnit and the time of consumption.
 * @author Vilhelm
 */
public class DiaryLog {

    private final ConsumableUnit eatableUnit;
    private final String time;

    public DiaryLog(ConsumableUnit eatableUnit){
        this.eatableUnit = eatableUnit;

        int hours, minutes;
        hours = Calendar.getInstance().get(Calendar.HOUR);
        minutes = Calendar.getInstance().get(Calendar.MINUTE);

        this.time = StringFormat.time(hours, minutes);
    }

    /** @return The eatableUnit that was consumed. */
    public ConsumableUnit getEatableUnit() { return eatableUnit; }

    /** @return The time of consumption */
    public String getTime() { return time; }

    @NonNull
    @Override
    public String toString() {
        return
            eatableUnit.getName() + " " +
            eatableUnit.getKcal() + "kcal " + getTime();
    }
}
