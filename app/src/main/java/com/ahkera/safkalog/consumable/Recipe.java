package com.ahkera.safkalog.consumable;

import com.ahkera.safkalog.util.Alert;

import java.util.ArrayList;

/**
 * Contains an ArrayList of consumable units. It counts the total kcal from the list.
 * @author Vilhelm
 */
public class Recipe extends Consumable {

    private String name;
    private ArrayList<ConsumableUnit> ingredients;
    private int kcalTotal;

    public Recipe(String name, ArrayList<ConsumableUnit> ingredients) {
        this.name = name;
        this.ingredients = ingredients;

        int gramsTotal = 0;

        for(ConsumableUnit unit : ingredients) {
            kcalTotal += unit.getKcal();
            gramsTotal += unit.getGrams();
        }

        if(gramsTotal != 0)
            kcalTotal = kcalTotal * 100 / gramsTotal;
    }

    /** @return The total kilocalorie amount. */
    @Override
    public int getKcal() {
        return kcalTotal;
    }

    /** @return The name of the recipe. */
    @Override
    public String getName() {
        return name;
    }

    public ArrayList<ConsumableUnit> getIngredients() {
        return ingredients;
    }
}
