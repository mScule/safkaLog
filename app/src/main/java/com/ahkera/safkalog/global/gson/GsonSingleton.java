package com.ahkera.safkalog.global.gson;

import com.ahkera.safkalog.consumable.Consumable;
import com.ahkera.safkalog.consumable.gson.ConsumableAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingleton {

    public Gson gson;

    private GsonSingleton() {
        GsonBuilder builder = new GsonBuilder();

        // For future updates, extend the builder with new possible TypeAdapters.
        builder.registerTypeAdapter(Consumable.class, new ConsumableAdapter());

        gson = builder.create();
    }

    public static GsonSingleton getInstance() {
        return instance;
    }

    private static GsonSingleton instance = new GsonSingleton();
}
