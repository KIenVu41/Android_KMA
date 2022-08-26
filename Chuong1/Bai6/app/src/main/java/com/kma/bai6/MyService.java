package com.kma.bai6;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    public static final int REQUEST_READ_CONTACTS = 79;
    ArrayList mobileArray;
    ArrayList numberArray;
    private final IBinder iBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        numberArray = new ArrayList();
        return iBinder;
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public List<Message> getAllSms() {
        Cursor cur = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        List<Message> sms = new ArrayList<Message>();
        cur.moveToFirst();
        while (cur.moveToNext()) {
            @SuppressLint("Range") String id = cur.getString(cur.getColumnIndex("_id"));
            @SuppressLint("Range") String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndexOrThrow("body"));
            Message message = new Message(address, body);
            Log.d("TAG", id.toString());
            sms.add(message);
        }
        return sms;
    }

    @SuppressLint("Range")
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> nameList = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                @SuppressLint("Range") String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numberArray.add(phoneNo);


                    }
                    nameList.add(new Contact(id, name, numberArray));
                    numberArray = new ArrayList();
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return nameList;
    }
}
