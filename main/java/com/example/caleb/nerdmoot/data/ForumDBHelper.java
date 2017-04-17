package com.example.caleb.nerdmoot.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caleb.nerdmoot.data.ForumContract.ForumEntry;

/**
 * Created by steve on 4/12/2017.
 */

public class ForumDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Forum.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ForumEntry.TABLE_NAME + " ("
                    +ForumEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT, "
                    +ForumEntry.COLUMN_USER_PROFILE + TEXT_TYPE + " NOT NULL" + COMMA_SEP
                    +ForumEntry.COLUMN_FORUM_NAME + TEXT_TYPE + " NOT NULL" + COMMA_SEP
                    +ForumEntry.COLUMN_FORUM_CONTENT + TEXT_TYPE + COMMA_SEP
                    +ForumEntry.COLUMN_FORUM_CODE + INTEGER_TYPE + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ForumEntry.TABLE_NAME;

    public ForumDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
