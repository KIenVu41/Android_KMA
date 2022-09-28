package com.kma.bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telecom.Call;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kma.bai2.model.CallLog;
import com.kma.bai2.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    ViewPagerAdapter adapter;
    private String[] labels = new String[]{"Danh bạ", "Cuộc gọi", "Gọi nhỡ"};
    List<Contact> contactList = null;
    List<CallLog> callLogs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        viewPager2.setCurrentItem(0, false);
    }

    public void getContacts() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
        }

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null, null, null, null, null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact(name, phone);
                contactList.add(contact);
            }
        }
    }

    public void readCallLog() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, 1);
        }

        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Uri callUri = Uri.parse("content://call_log/calls");
        Cursor cur = getContentResolver().query(callUri, null, null, null, strOrder);
        // loop through cursor
        while (cur.moveToNext()) {
            @SuppressLint("Range") String callName = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));
            @SuppressLint("Range") String callDate = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DATE));
            @SuppressLint("Range") String callType = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.TYPE));
            @SuppressLint("Range") String duration = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DURATION));
            CallLog callLog = new CallLog(callName, callDate, duration, callType);
            callLogs.add(callLog);
        }

    }

    private void init() {
        contactList = new ArrayList<>();
        getContacts();
        callLogs = new ArrayList<>();
        readCallLog();
        // initialize tabLayout
        tabLayout = findViewById(R.id.tab_layout);
        // initialize viewPager2
        viewPager2 = findViewById(R.id.view_pager2);
        // create adapter instance
        adapter = new ViewPagerAdapter(this, contactList, callLogs);
        // set adapter to viewPager2
        viewPager2.setAdapter(adapter);

    }
}