package com.ahkera.safkalog.global;

import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.consumable.Consumable;

import java.util.ArrayList;

/**
 * Contains all the information that is accessed, modified, and created while the program is on.
 * @author Vilhelm
 * @author Konsta
 */
public class GlobalInstance {

    /** Holds the dates when there has been logs. */
    public ArrayList<DiaryDate> dates;

    /** Holds the consuming logs that are added during some day. */
    public DiaryDate diaryDateToday;

    /** Holds the consumables (Eatables, Recipes) that the user has created. */
    public ArrayList<Consumable> consumables;

    /** Holds the information of the recipe that is currently being made in the recipe activity. */
    public ArrayList<ConsumableUnit> currentRecipe;

    private static GlobalInstance global = new GlobalInstance();

    public static GlobalInstance getInstance() {
        return global;
    }

    private GlobalInstance() {
        diaryDateToday = new DiaryDate();
        consumables    = new ArrayList();
        currentRecipe  = new ArrayList();
        dates          = new ArrayList();
    }
}
