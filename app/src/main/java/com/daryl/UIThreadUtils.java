package com.daryl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hypelabs.hype.Hype;

import static android.support.constraint.Constraints.TAG;

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

    static void updateConversation(final Activity activity, final String sender, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ScrollView conversation_scroll = activity.findViewById(R.id.scroll);
                final LinearLayout conversation = activity.findViewById(R.id.conversation);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                final TextView messageView = new TextView(activity.getApplicationContext());
                messageView.setText(sender + " : " + message);
                messageView.setLayoutParams(params);

                Log.i(TAG, "######### SECRET 5 #######");
                SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("secret", Context.MODE_PRIVATE);
                Log.i(TAG, sharedPreferences.getString(activity.getString(R.string.user_name), ""));
                String username = sharedPreferences.getString(activity.getString(R.string.user_name), "");

                if (sender.equals(username)) {
                    messageView.setBackgroundColor(activity.getResources().getColor(R.color.white));
                    messageView.setTextColor(activity.getResources().getColor(R.color.black));
                } else {
                    messageView.setBackgroundColor(activity.getResources().getColor(R.color.gray));
                    messageView.setTextColor(activity.getResources().getColor(R.color.white));
                }
                conversation.addView(messageView);
                conversation_scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}
