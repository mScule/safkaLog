package com.ahkera.safkalog.global;

import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.eatable.Eatable;
import com.ahkera.safkalog.eatable.EatableUnit;

import java.util.ArrayList;

/**
 * Contains all the information that is accessed,
 * modified, and created while the program is on.
 *
 * @author Vilhelm
 */
public class GlobalInstance {

    /** Holds the consuming logs that are added during some day. */
    public DiaryDate diaryDateToday;

    /** Holds the consumables (Eatables, Recipes) that the user has created */
    public ArrayList<Eatable> eatables;

    /**
     * Holds the information of the recipe that is
     * currently being made in the recipe activity.
     */
    public ArrayList<EatableUnit> currentRecipe;

    // Singleton
    private static GlobalInstance global = new GlobalInstance();

    public static GlobalInstance getInstance() {
        return global;
    }

    private GlobalInstance() {
        diaryDateToday = new DiaryDate();
        eatables       = new ArrayList();
        currentRecipe  = new ArrayList();
    }
}
