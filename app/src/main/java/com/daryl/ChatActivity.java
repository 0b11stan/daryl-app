package com.daryl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hypelabs.hype.Error;
import com.hypelabs.hype.Hype;
import com.hypelabs.hype.Instance;
import com.hypelabs.hype.Message;
import com.hypelabs.hype.MessageInfo;
import com.hypelabs.hype.MessageObserver;
import com.hypelabs.hype.NetworkObserver;
import com.hypelabs.hype.StateObserver;
import com.hypelabs.hype.Version;

import java.nio.charset.StandardCharsets;


public class ChatActivity extends Activity implements StateObserver, NetworkObserver, MessageObserver {

    private static final String TAG = ChatActivity.class.getName();
    private static final int REQUEST_ACCESS_COARSE_LOCATION_ID = 0;
    private Instance instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION_ID);
        }
        startHype();

        findViewById(R.id.button_message).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private void startHype() {
        Log.i(TAG, "Hype is starting");
        Hype.setContext(getApplicationContext());
        Hype.addMessageObserver(this);
        Hype.addNetworkObserver(this);
        Hype.addStateObserver(this);
        Hype.setAppIdentifier("b36a27c7");
        Hype.setAnnouncement(new byte[10]);

        Hype.start();

        Log.i(TAG, "Version = " + Version.getVersionString());
    }

    protected Message sendMessage(/*boolean acknowledge*/) {
        boolean acknowledge = false;

        EditText message_input = findViewById(R.id.input_message);
        String message_text = message_input.getText().toString() + "\n";

        TextView conversation = findViewById(R.id.conversation);
        UIThreadUtils.appendText(this, conversation, message_text);
        UIThreadUtils.setText(this, message_input, "");

        byte[] data = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            data = message_text.getBytes(StandardCharsets.UTF_8);
        }

        return Hype.send(data, this.instance, acknowledge);
    }


    protected void requestHypeToStart() {
        Hype.setContext(getApplicationContext());
        Hype.addStateObserver(this);
        Hype.addNetworkObserver(this);
        Hype.addMessageObserver(this);
        Hype.setAppIdentifier("b36a27c7");
        Hype.start();
    }

    @Override
    public void onHypeMessageReceived(Message message, Instance instance) {
        String text = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            text = new String(message.getData(), StandardCharsets.UTF_8);
        }

        Log.i(TAG, String.format("Hype received a message from: %s %s", instance.getStringIdentifier(), text));
        TextView conversation = findViewById(R.id.conversation);
        UIThreadUtils.appendText(this, conversation, text);
    }


    @Override
    public void onHypeMessageFailedSending(MessageInfo messageInfo, Instance instance, Error error) {

    }

    @Override
    public void onHypeMessageSent(MessageInfo messageInfo, Instance instance, float v, boolean b) {

    }

    @Override
    public void onHypeMessageDelivered(MessageInfo messageInfo, Instance instance, float v, boolean b) {

    }

    @Override
    public void onHypeInstanceFound(Instance instance) {
        Log.i(TAG, "User = " + Hype.getHostInstance().getStringIdentifier());
        Log.i(TAG, String.format("Hype found instance: %s", instance.getStringIdentifier()));
        if (shouldResolveInstance(instance)) {
            Hype.resolve(instance);
        }
    }

    private boolean shouldResolveInstance(Instance instance) {
        return true;
    }

    @Override
    public void onHypeInstanceLost(Instance instance, Error error) {
        Log.i(TAG, String.format("Hype lost instance: %s [%s]", instance.getStringIdentifier(), error.toString()));
    }

    @Override
    public void onHypeInstanceResolved(Instance instance) {
        Log.i(TAG, String.format("Hype resolved instance: %s", instance.getStringIdentifier()));
        this.instance = instance;

        UIThreadUtils.showToast(this, "Connected to " + instance.getStringIdentifier());

    }

    @Override
    public void onHypeInstanceFailResolving(Instance instance, Error error) {

    }

    @Override
    public void onHypeStart() {
        Log.i(TAG, "Hype started");
    }

    @Override
    public void onHypeStop(Error error) {

    }

    @Override
    public void onHypeFailedStarting(Error error) {
        Log.i(TAG, String.format("Hype failed starting [%s]", error.toString()));
    }

    @Override
    public void onHypeReady() {

    }

    @Override
    public void onHypeStateChange() {

    }

    @Override
    public String onHypeRequestAccessToken(int i) {
        return "627faf69e4636246";
    }
}
