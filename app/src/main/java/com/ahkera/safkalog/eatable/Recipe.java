package com.ahkera.safkalog.eatable;

import java.util.ArrayList;

/**
 * Contains an ArrayList of consumable units. It counts the total kcal from the list.
 * @author Vilhelm
 */
public class Recipe extends Eatable {

    private String name;
    private ArrayList<EatableUnit> ingredients;

    public Recipe(String name, ArrayList<EatableUnit> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    /** @return The total kilocalorie amount. */
    @Override
    public int getKcal() {
        int kcalTotal = 0;

        for(EatableUnit unit : ingredients)
            kcalTotal += unit.getKcal();

        return kcalTotal;
    }

    /** @return The name of the recipe. */
    @Override
    public String getName() {
        return name;
    }
}
