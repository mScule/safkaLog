package com.ahkera.safkalog.eatable;

import androidx.annotation.NonNull;

public class Ingredient {
    private String name;
    private int kcal;

    public Ingredient(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public int getKcal() {
        return kcal;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ingredient: " + getName() + " Calories: " + getKcal();
    }
}
