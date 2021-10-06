package com.ahkera.safkalog.eatable;

/**
 * The base class for all consumable items.
 * @author Vilhelm
 */
public abstract class Eatable {

    /** @return The kilocalories of the consumable */
    public abstract int getKcal();

    /** @return The name of the consumable */
    public abstract String getName();
}
