package com.ahkera.safkalog.eatable;

/**
 * Contains the consumable item and the amount of it consumed in grams.
 * @author Vilhelm
 */
public class EatableUnit extends Eatable {

    private Eatable eatable;
    private int grams;

    public EatableUnit(Eatable eatable, int grams) {
        this.eatable = eatable;
        this.grams = grams;
    }

    /** @return The name of the consumed unit. */
    @Override
    public String getName() {
        return eatable.getName();
    }

    /** @return The kilocalories of the consumed unit. */
    @Override
    public int getKcal() {
        return grams * eatable.getKcal() / 100;
    }

    /** @return The amount of grams that the unit was consumed. */
    public int getGrams() { return grams; }
}
