package com.ahkera.safkalog.eatable;

import java.util.ArrayList;

public class Recipe extends Ingredient {

    private ArrayList<EatableUnit> ingredients;

    public Recipe(String name, ArrayList<EatableUnit> ingredients) {
        super(name,0);

        int totalKcal = 0;
        for(EatableUnit ingredient : ingredients) {
            int kcal = ingredient.getIngredient().getKcal();

        }
    }
}
