package com.rokomari_coding_test.db_access;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rokomari_coding_test.model.Task;
@Database(entities = {Task.class},version = 1)
public abstract class MasterDb extends RoomDatabase {
    private static MasterDb instance;

    public abstract TaskDAO taskDAO();

    public static synchronized MasterDb getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MasterDb.class,"task_app")
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
