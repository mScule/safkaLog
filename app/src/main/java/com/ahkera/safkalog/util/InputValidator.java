package com.ahkera.safkalog.util;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ahkera.safkalog.R;

/**
 * Class used for validating user input from various input fields.
 * The android AlertDialogs are used by this class.
 * @author Vilhelm
 */
public class InputValidator {

    /** @return True if char is number, false otherwise. */
    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


    /** @return True if string exists, and isn't empty */
    private static boolean stringExists(String string) {
        if(string != null && !string.equals(""))
            return true;
        return false;
    }

    /**
     * stringExists with generic error messages.
     * @return True if string exists, and isn't empty
     */
    private static boolean stringExists(Context context, @NonNull String fieldName, String string) {
        if(stringExists(string))
            return true;

        Alert.show(context, fieldName + " field can't be empty!", "Ok");
        return false;
    }

    /** @return True if string has also other characters than spaces, tabs, and newlines */
    public static boolean isContentful(String string) {
        if(stringExists(string)) {
            int count = 0;

            char[] carr = string.toCharArray();

            for(char c : carr)
                if(c != '\n' && c != ' ' && c != '\t')
                    count++;

            if(count > 0)
                return true;
        }

        return false;
    }

    /**
     * isContentful with generic error messages
     * @return True if string has also other characters than spaces, tabs, and newlines
     */
    public static boolean isContentful(Context context, @NonNull String fieldName, String string) {

        if(stringExists(context, fieldName, string)) {
            int count = 0;

            char[] carr = string.toCharArray();

            for(char c : carr)
                if(c != '\n' && c != ' ' && c != '\t')
                    count++;

            if(count > 0)
                return true;
        }

        return false;
    }

    /** @return True if string is integer value (can be signed), false otherwise. */
    public static boolean isInteger(String string) {

        if(stringExists(string)) {

            char[] carr = string.toCharArray();

            // Check if the value is signed.
            int start = 0;

            if (carr[0] == '-')
                start++;

            for (int i = start; i < carr.length; i++) {
                char c = carr[i];

                if (!isNumber(c))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * isInteger with generic error messages
     * @return True if string is integer value (can be signed), false otherwise.
     */
    public static boolean isInteger(Context context, @NonNull String fieldName, String string) {

        if(stringExists(context, fieldName, string)) {

            char[] carr = string.toCharArray();

            // Check if the value is signed.
            int start = 0;

            if (carr[0] == '-')
                start++;

            for (int i = start; i < carr.length; i++) {
                char c = carr[i];

                if (!isNumber(c)) {
                    Alert.show(context, context.getString(R.string.inputValidator_err_int), "Ok");
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /** @return True if string is unsigned integer value, false otherwise. */
    public static boolean isUnsignedInteger(String string) {

        if(stringExists(string)) {

            char[] carr = string.toCharArray();

            for (char c : carr) {
                if (!isNumber(c))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * isUnsignedInteger with generic error messages
     * @return True if string is unsigned integer value, false otherwise.
     */
    public static boolean isUnsignedInteger(Context context, @NonNull String fieldName, String string) {

        if(stringExists(context, fieldName, string)) {

            char[] carr = string.toCharArray();

            for (char c : carr) {
                if (!isNumber(c)) {
                    Alert.show(context, context.getString(R.string.inputValidator_err_uInt), "Ok");
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
