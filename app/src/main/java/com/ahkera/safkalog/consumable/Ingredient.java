package com.ahkera.safkalog.consumable;

import androidx.annotation.NonNull;

/**
 * Contains the information of single consumable. The name, and kilocalories per 100 grams.
 * @author Vilhelm
 */
public class Ingredient extends Consumable {

    private String name;
    private int kcal;

    public Ingredient(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

    /** @return The kilocalories per 100 grams. */
    @Override
    public int getKcal() {
        return kcal;
    }

    /** @return The name of the ingredient. */
    @Override
    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ingredient: " + getName() + " Calories: " + getKcal();
    }
}
