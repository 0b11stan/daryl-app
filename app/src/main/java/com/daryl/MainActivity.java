package com.daryl;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

        final ImageButton button = findViewById(R.id.zombie_spotter);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent zombieSpotter = new Intent(MainActivity.this, ZombieSpotterActivity.class);
                startActivity(zombieSpotter);
                System.out.println("test ok");
            }
        });

    }

}
