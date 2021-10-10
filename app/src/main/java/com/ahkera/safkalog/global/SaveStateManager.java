package com.ahkera.safkalog.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.ahkera.safkalog.global.gson.GsonSingleton;
import com.google.gson.Gson;

/**
 * Contains static methods for save state management.
 * It accesses the GlobalInstance by itself.
 * @author Vilhelm
 */
public class SaveStateManager {

    private static final String
        SAVE_STATE =
            "com.ahkera.safkalog.global.SaveStateManager.SAVE_STATE",
        SHARED_PREFERENCE =
            "com.ahkera.safkalog.global.SaveStateManager.SHARED_PREFERENCE";

    /** @return True, if save state already exists, false otherwise. */
    public static boolean stateExists(Context context) {
        SharedPreferences sharedPrefs =
            context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);

        return sharedPrefs.contains(SAVE_STATE);
    }

    public static void saveState(Context context) {
        SharedPreferences sharedPrefs =
            context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Gson gson = GsonSingleton.getInstance().gson;
        String json = gson.toJson(GlobalInstance.getInstance());

        editor.putString(SAVE_STATE, json);
        editor.commit();
    }

    public static void loadState(Context context) {

        SharedPreferences sharedPrefs =
            context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);

        String saveState = sharedPrefs.getString(SAVE_STATE, "");

        Gson gson = GsonSingleton.getInstance().gson;

        GlobalInstance.setInstance(gson.fromJson(saveState, GlobalInstance.class));
    }
}
