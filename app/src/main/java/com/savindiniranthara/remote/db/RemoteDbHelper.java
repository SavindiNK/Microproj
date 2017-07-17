package com.savindiniranthara.remote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Charith on 7/17/2017.
 */

public class RemoteDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UniversalRemote.db";

    private static final String SQL_CREATE_TABLE_SAVED_REMOTES =
            "CREATE TABLE " + RemoteContract.SavedRemotes.TABLE_NAME + " (" +
                    RemoteContract.SavedRemotes._ID + " INTEGER PRIMARY KEY," +
                    RemoteContract.SavedRemotes.COLUMN_NAME_REMOTE_ID + " TEXT," +
                    RemoteContract.SavedRemotes.COLUMN_NAME_BUTTON_ID + " TEXT," +
                    RemoteContract.SavedRemotes.COLUMN_NAME_RAW_CODE + " TEXT)";

    private static final String SQL_CREATE_TABLE_RECENTLY_OPENED =
            "CREATE TABLE " + RemoteContract.RecentlyOpened.TABLE_NAME + " (" +
                    RemoteContract.RecentlyOpened.COLUMN_NAME_REMOTE_ID + " TEXT)";

    private static final String SQL_DELETE_SAVED_REMOTES =
            "DROP TABLE IF EXISTS " + RemoteContract.SavedRemotes.TABLE_NAME;

    private static final String SQL_DELETE_RECENTLY_OPENED =
            "DROP TABLE IF EXISTS " + RemoteContract.RecentlyOpened.TABLE_NAME;

    public RemoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_SAVED_REMOTES);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_RECENTLY_OPENED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_RECENTLY_OPENED);
        sqLiteDatabase.execSQL(SQL_DELETE_SAVED_REMOTES);
        onCreate(sqLiteDatabase);
    }
}
