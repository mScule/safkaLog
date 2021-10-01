package com.ahkera.safkalog.eatable;

import java.util.ArrayList;

public class Recipe extends Eatable {

    private String name;

    private ArrayList<EatableUnit> ingredients;

    public Recipe(String name, ArrayList<EatableUnit> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public int getKcal() {
        int kcalTotal = 0;

        for(EatableUnit unit : ingredients)
            kcalTotal += unit.getKcal();

        return kcalTotal;
    }

    @Override
    public String getName() {
        return name;
    }
}
