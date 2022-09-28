package com.kma.bai1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    ViewPagerAdapter adapter;
    private String[] labels = new String[]{"SMS", "Manage"};
    List<Message> messageList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        viewPager2.setCurrentItem(0, false);

        receiveSms();
    }

    public void receiveSms() {
        if(!checkPermission(Manifest.permission.RECEIVE_SMS)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 222);
        }

    }

    private void displaySendSmsLog() {
        Uri allMessages = Uri.parse("content://sms/sent");
        Cursor cursor = this.getContentResolver().query(allMessages, null,
                null, null," date DESC limit 5");
        while (cursor.moveToNext()) {
            Message message = new Message();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                if(cursor.getString(9).equals("2")) {
                    message.setFrom(cursor.getString(2));
                    message.setTo("A20s");
                    message.setType("Tin nhắn gửi");
                    message.setContent(cursor.getString(12));
                }
            }
            messageList.add(message);
        }

    }

    private void displayReceiveSmsLog() {
        Uri allMessages = Uri.parse("content://sms/inbox");
        //Cursor cursor = managedQuery(allMessages, null, null, null, null); Both are same
        Cursor cursor = this.getContentResolver().query(allMessages, null,
                null, null," date DESC limit 10");
        while (cursor.moveToNext()) {
            Message message = new Message();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                if(cursor.getString(9).equals("1")) {
                    message.setFrom(cursor.getString(2));
                    message.setTo("A20s");
                    message.setType("Tin nhắn đến");
                    message.setContent(cursor.getString(12));
                }
            }
            messageList.add(message);
        }

    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    private void init() {
        messageList = new ArrayList<>();
        displaySendSmsLog();
        displayReceiveSmsLog();
        // initialize tabLayout
        tabLayout = findViewById(R.id.tab_layout);
        // initialize viewPager2
        viewPager2 = findViewById(R.id.view_pager2);
        // create adapter instance
        adapter = new ViewPagerAdapter(this, messageList);
        // set adapter to viewPager2
        viewPager2.setAdapter(adapter);

    }
}