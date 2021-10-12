package com.ahkera.safkalog.consumable;

/**
 * The base class for all consumable items.
 * @author Vilhelm
 */
public abstract class Consumable {

    /** @return The kilocalories of the consumable */
    public abstract int getKcal();

    /** @return The name of the consumable */
    public abstract String getName();
}
