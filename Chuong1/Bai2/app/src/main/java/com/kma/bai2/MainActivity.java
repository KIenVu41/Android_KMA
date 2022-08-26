package com.kma.bai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private EditText edtNum;
    private LinearLayout llRandom, llPrime;
    private Handler handler;
    private MainAsyncTask mainAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setOnclick();
    }

    private void init() {
        btnStart = findViewById(R.id.btnStart);
        llRandom = findViewById(R.id.ll_random);
        llPrime = findViewById(R.id.ll_prime);
        edtNum = findViewById(R.id.edtNum);
        handler = new Handler() {
            @Override
            public void handleMessage (Message msg) {
                int data = msg.getData().getInt("someKey");
                Button btn = new Button(MainActivity.this);
                Button btn2 = new Button(MainActivity.this);
                if(isPrime(data)) {
                    btn.setText(data + "");
                    btn2.setText(data + "");
                    llRandom.addView(btn);
                    llPrime.addView(btn2);
                } else {
                    btn.setText(data + "");
                    llRandom.addView(btn);
                }
                btn.setGravity(Gravity.CENTER);
                btn2.setGravity(Gravity.CENTER);
            }
        };

    }

    private boolean isPrime(int num) {
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }

        if (!flag)
            return true;
        return false;
    }

    private void setOnclick() {
        if(edtNum.getText().equals("")) {
            return;
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.valueOf(edtNum.getText().toString());
                mainAsyncTask = new MainAsyncTask(handler, time);
                mainAsyncTask.execute();
            }
        });
    }

}