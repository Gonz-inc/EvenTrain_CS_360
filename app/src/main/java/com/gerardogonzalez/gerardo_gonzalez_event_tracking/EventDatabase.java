package com.gerardogonzalez.gerardo_gonzalez_event_tracking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eventrain_database";
    private static final int DATABASE_VERSION = 1;

    private static final class EventTable {
        private static final String TABLE;
        private static final String COL_ID;
        private static final String COL_TITLE;


    }
    private static final class UserTable {

    }
    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL();
    }
}
