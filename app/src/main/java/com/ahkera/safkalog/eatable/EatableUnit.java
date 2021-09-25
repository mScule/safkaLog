package com.ahkera.safkalog.eatable;

public class EatableUnit {
    private Ingredient ingredient;
    private int grams;

    public EatableUnit(Ingredient ingredient, int grams) {
        this.ingredient = ingredient;
        this.grams = grams;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getGrams() {
        return grams;
    }
}
