package com.kma.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvProgress;
    private ProgressBar progressBar;
    private Button btnStart;
    private Handler handler;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnclick();
        handleMessage();
    }

    private void init() {
        tvProgress = findViewById(R.id.tvProgress);
        progressBar = findViewById(R.id.progress_bar);
        btnStart = findViewById(R.id.btnStart);

        progressBar.setProgress(0);
    }

    private void setOnclick() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread background = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i = 1; i <= 100 && isRunning; i++)
                            {
                                Thread.sleep(1000);
                                handler.sendMessage(handler.obtainMessage());
                            }
                        } catch (Throwable t){}
                    }
                });

                isRunning = true;
                background.start();
            }
        });
    }

    private void handleMessage() {
        handler = new Handler() {
            @Override
            public void handleMessage (Message msg) {
                progressBar.incrementProgressBy(1);
                tvProgress.setText(String.valueOf(progressBar.getProgress()) + "%");
            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }
}