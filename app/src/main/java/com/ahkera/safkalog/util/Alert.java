package com.ahkera.safkalog.util;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Alert {
    public static void show(Context context, String msg, String exitBtn) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setMessage(msg);
        alert.setCancelable(true);

        alert.setPositiveButton(exitBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}
