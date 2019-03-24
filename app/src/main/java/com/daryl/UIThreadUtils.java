package com.daryl;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

class UIThreadUtils {
    static void appendText(Activity activity, final TextView text, final String value) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.append(value);
            }
        });
    }

    static void setText(Activity activity, final TextView text, final String value) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    static void showToast(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final int duration = Toast.LENGTH_SHORT;
                final Context context = activity.getApplicationContext();
                final Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        });
    }
}
