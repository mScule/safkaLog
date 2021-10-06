package com.ahkera.safkalog.util;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

/**
 * Contains methods that makes creating AlertDialogs faster.
 * @author Vilhelm
 */
public class Alert {

    /**
     * Creates AlertDialog with message and button that closes that dialog.
     * @param context The context, where the alert takes place.
     * @param message The message, that will be shown in the alert.
     * @param exitButton The text that will be shown in the button that closes the dialog.
     */
    public static void show(Context context, String message, String exitButton) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setMessage(message);
        alert.setCancelable(true);

        alert.setPositiveButton(exitButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}
