package com.rezwan_cs.hscictlife.utilities;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rezwan_cs.hscictlife.modelclasses.CCode;
import com.rezwan_cs.hscictlife.modelclasses.LastStudyResult;

@Database(entities = {LastStudyResult.class, CCode.class}, version = 2)
public abstract class ICTDatabase extends RoomDatabase {

    private static final String LOG_TAG = ICTDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "ict_db";
    private static ICTDatabase sInstance;

    public static ICTDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        ICTDatabase.class, ICTDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract LastStudyResultDao lastStudyResultDao();

    public abstract CCodeDao cCodeDao();
}
