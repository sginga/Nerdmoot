package com.example.caleb.nerdmoot.data;

import android.provider.BaseColumns;

/**
 * Created by steve on 4/12/2017.
 */

public class ForumContract {
    public static final class ForumEntry implements BaseColumns {
        public final static String _ID = BaseColumns._ID;
        public final static String TABLE_NAME = "User Data";
        public final static String COLUMN_USER_PROFILE="Profile:";
        public final static String COLUMN_FORUM_NAME="Post Title:";
        public final static String COLUMN_FORUM_CONTENT="Post Body:";
        public final static String COLUMN_FORUM_CODE="Forum Connect Code:";

    }
}
