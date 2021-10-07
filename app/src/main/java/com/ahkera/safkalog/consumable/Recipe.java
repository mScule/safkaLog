package com.ahkera.safkalog.consumable;

import java.util.ArrayList;

/**
 * Contains an ArrayList of consumable units. It counts the total kcal from the list.
 * @author Vilhelm
 */
public class Recipe extends Consumable {

    private String name;
    private ArrayList<ConsumableUnit> ingredients;

    public Recipe(String name, ArrayList<ConsumableUnit> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    /** @return The total kilocalorie amount. */
    @Override
    public int getKcal() {
        int kcalTotal = 0;

        for(ConsumableUnit unit : ingredients)
            kcalTotal += unit.getKcal();

        return kcalTotal;
    }

    /** @return The name of the recipe. */
    @Override
    public String getName() {
        return name;
    }
}
