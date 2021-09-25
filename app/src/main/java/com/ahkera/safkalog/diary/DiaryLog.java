package com.ahkera.safkalog.diary;

import androidx.annotation.NonNull;

import com.ahkera.safkalog.eatable.Ingredient;

import java.util.Calendar;
import java.util.Date;

public class DiaryLog {
    private Ingredient ingredient;
    private String time;

    public DiaryLog(Ingredient ingredient){
        this.ingredient = ingredient;

        int hours, minutes;
        hours = Calendar.getInstance().get(Calendar.HOUR);
        minutes = Calendar.getInstance().get(Calendar.MINUTE);

        this.time = hours + ":" + minutes;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public String getTime() {
        return time;
    }

    @NonNull
    @Override
    public String toString() {
        return ingredient.getName() + " " + ingredient.getKcal() + "kcal " + getTime();
    }
}
