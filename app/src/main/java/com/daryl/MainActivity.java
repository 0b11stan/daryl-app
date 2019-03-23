package com.daryl;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        while (true) {
//            final Intent intent = new Intent("Test");
//            sendBroadcast(intent);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.err.println("Error");
//            }
//        }

    }
}
