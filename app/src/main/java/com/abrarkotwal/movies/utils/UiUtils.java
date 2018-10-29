package com.abrarkotwal.movies.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import timber.log.Timber;


public class UiUtils {

    public static void handleThrowable(Throwable throwable,Context context) {
        Timber.e(throwable, throwable.toString());

        if (throwable instanceof IOException){
            internetConnectionErrorAlert(context,(dialog, which) -> dialog.dismiss());
        }
        else {
            timeoutErrorAlert(context,(dialog, which) -> dialog.dismiss());
        }
    }

    public static ProgressDialog showProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.show();
        return progressDialog;
    }

    private static void internetConnectionErrorAlert(Context context, DialogInterface.OnClickListener onClickTryAgainButton) {
        String message = "Please Turn On Your Internet? Or Try Again!!!";
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle("Network Error")
                .setMessage(message)
                .setPositiveButton("Cancel", onClickTryAgainButton)
                .setNegativeButton("Turn Internet On",(dialog, which) -> turnOnInternet(context))
                .show();
    }

    private static void turnOnInternet(Context context){
        Intent i = new Intent(Settings.ACTION_SETTINGS);
        ((Activity) context).startActivityForResult(i, 0);
    }

    private static void timeoutErrorAlert(Context context, DialogInterface.OnClickListener onClickTryAgainButton) {
        String message = "Network time out error?";
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setTitle("Network Error")
                .setMessage(message)
                .setPositiveButton("Try after sometime", onClickTryAgainButton)
                .show();
    }
}
