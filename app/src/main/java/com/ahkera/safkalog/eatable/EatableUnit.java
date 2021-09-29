package com.ahkera.safkalog.eatable;

public class EatableUnit extends Eatable {
    private Eatable eatable;
    private int grams;

    public EatableUnit(Eatable eatable, int grams) {
        this.eatable = eatable;
        this.grams = grams;
    }

    @Override
    public String getName() {
        return eatable.getName();
    }

    @Override
    public int getKcal() {
        return grams * eatable.getKcal() / 100;
    }
}
