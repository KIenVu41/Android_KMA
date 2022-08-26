package com.kma.bai7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    Movie m;
    public static final String CONTENT_URI = "content://kienvu.com.own.contentprovider";
    public static final int MATCH_ALL = 1;
    public static final int MATCH_ID = 2;
    public static final String AUTHORITY = "kienvu.com.own.contentprovider";
    private UriMatcher uriMatcher;

    public int delete(Uri arg0, String arg1, String[] arg2) {
        int ret = -1;
        switch (uriMatcher.match(arg0)) {
            case MATCH_ALL:
                ret = m.deleteAll();
                break;
            case MATCH_ID:
                ret = m.delete(arg0.getLastPathSegment());
                break;
        }
        return -1;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
//        return 0;
//    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
