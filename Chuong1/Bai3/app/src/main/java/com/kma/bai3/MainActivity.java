package com.kma.bai3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btnPlay = findViewById(R.id.button_play);
        btnStop = findViewById(R.id.button_stop);

        setOnClick();
    }

    private void setOnClick() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSong();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSong();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void playSong() {
        Intent myIntent = new Intent(MainActivity.this, MediaService.class);

        this.startService(myIntent);
    }

    private void stopSong() {
        Intent myIntent = new Intent(MainActivity.this, MediaService.class);

        this.stopService(myIntent);
    }
}