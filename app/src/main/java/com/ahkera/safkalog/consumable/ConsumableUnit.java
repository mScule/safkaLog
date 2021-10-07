package com.ahkera.safkalog.consumable;

/**
 * Contains the consumable item and the amount of it consumed in grams.
 * @author Vilhelm
 */
public class ConsumableUnit extends Consumable {

    private Consumable consumable;
    private int grams;

    public ConsumableUnit(Consumable consumable, int grams) {
        this.consumable = consumable;
        this.grams = grams;
    }

    /** @return The name of the consumed unit. */
    @Override
    public String getName() {
        return consumable.getName();
    }

    /** @return The kilocalories of the consumed unit. */
    @Override
    public int getKcal() {
        return grams * consumable.getKcal() / 100;
    }

    /** @return The amount of grams that the unit was consumed. */
    public int getGrams() { return grams; }
}
