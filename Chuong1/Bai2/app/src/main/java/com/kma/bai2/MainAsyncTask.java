package com.kma.bai2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Random;

public class MainAsyncTask extends AsyncTask<Void, Void, Void> {

    private Handler handler;
    private int time;

    public MainAsyncTask(Handler handler, int time) {
        this.handler = handler;
        this.time = time;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        Random r = new Random();
        int i = 0;
        while (i < time) {
            int n = r.nextInt(100) + 1;

            Bundle bundle = new Bundle();
            bundle.putInt("someKey", n);
            Message msg = Message.obtain();
            msg.setData(bundle);
            handler.sendMessage(msg);

            i++;
        }

    }
}
