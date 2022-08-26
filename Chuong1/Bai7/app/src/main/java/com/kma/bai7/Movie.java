package com.kma.bai7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Movie {
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public final static String MOVIE_TABLE = "favorite_movie";
    public final static String MOVIE_NAME = "name";
    public final static String MOVIE_DESC = "description";
    public final static String MOVIE_ID = "_id";

    public Movie(Context context) {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long createMovie(String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, name);
        contentValues.put(MOVIE_DESC, desc);
        return sqLiteDatabase.insert(MOVIE_TABLE, null, contentValues);
    }

    public void createMovie(ContentValues contentValues) {
        sqLiteDatabase.insert(MOVIE_TABLE, null, contentValues);
    }

    public Cursor fetchAllMovies() {
        String[] cols = new String[] {MOVIE_NAME, MOVIE_DESC, MOVIE_ID};
        Cursor mCursor = sqLiteDatabase.query(true, MOVIE_TABLE, cols, null, null, null, null, null, null);
        if(mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchOneMovie(String id) {
        String[] cols = new String[] {MOVIE_NAME, MOVIE_DESC, MOVIE_ID};
        Cursor c = sqLiteDatabase.query(MOVIE_TABLE, cols, MOVIE_ID + "=?", new String[] {id}, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public int delete(String id) {
        return sqLiteDatabase.delete(MOVIE_TABLE, MOVIE_ID + "=" + id, null);
    }

    public int deleteAll() {
        return sqLiteDatabase.delete(MOVIE_TABLE, null, null);
    }

    public int updateMovie(ContentValues values, String id) {
        return sqLiteDatabase.update(MOVIE_TABLE, values, MOVIE_ID + "=?", new String[]{id});
    }

    public int updateMovie(ContentValues values, String selection, String[] selectionArgs) {
        return sqLiteDatabase.update(MOVIE_TABLE, values, selection, selectionArgs);
    }
}
