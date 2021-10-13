package com.ahkera.safkalog.consumable;

/**
 * Used for storing kcal in integer value, and boolean that tells if the limit is toggled.
 * @author Vilhelm
 */
public class ConsumptionLimit {
    private boolean toggled;
    private int kcal;

    public ConsumptionLimit(int kcal, boolean toggled){
        this.toggled = toggled;
        this.kcal = kcal;
    }

    /** @return kcal limit in integer value. */
    public int getKcal() {
        return kcal;
    }

    /** @return the boolean state that tells is the limit on, or off. */
    public boolean isToggled() {
        return toggled;
    }

    /** Setter for the kcal */
    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    /** Setter for the toggled boolean */
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
}
