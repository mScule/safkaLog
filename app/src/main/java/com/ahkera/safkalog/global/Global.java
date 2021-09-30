package com.ahkera.safkalog.global;

import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.eatable.EatableUnit;
import com.ahkera.safkalog.eatable.Ingredient;
import com.ahkera.safkalog.eatable.Recipe;

import java.util.ArrayList;

public class Global {
    public DiaryDate diaryDateToday;

    public ArrayList<Ingredient> ingredients;
    public ArrayList<Recipe> recipes;

    private static Global global = new Global();

    public static Global getInstance() {
        return global;
    }

    private Global() {
        diaryDateToday = new DiaryDate();

        ingredients = new ArrayList();
        recipes     = new ArrayList();

        diaryDateToday.addLog(new DiaryLog(new EatableUnit(
                new Ingredient("Remix karkkipussi", 355), 25
        )));

        diaryDateToday.addLog(new DiaryLog(new EatableUnit(
                new Ingredient("Big mac", 500), 150
        )));

        diaryDateToday.addLog(new DiaryLog(new EatableUnit(
                new Ingredient("Limu", 45), 330
        )));
    }
}
