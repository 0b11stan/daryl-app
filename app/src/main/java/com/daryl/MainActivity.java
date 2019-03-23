package com.daryl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long survivalDays = getSurvivalDays();
        TextView left = findViewById(R.id.status_left);
        left.setText(String.valueOf(survivalDays).concat(" days"));

        findViewById(R.id.zombie_spotter).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent zombieSpotter = new Intent(MainActivity.this, ZombieSpotterActivity.class);
                startActivity(zombieSpotter);
            }
        });

        findViewById(R.id.chat).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent chat = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(chat);
            }
        });

    }

    protected long getSurvivalDays() {
        LocalDate doomDate = LocalDate.of(2019, 3, 20);
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(doomDate, currentDate);
    }


}
