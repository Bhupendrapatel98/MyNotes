package com.example.mynotes.room;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient Instance;
    private AppDatabase appDatabase;  // database object

    public DatabaseClient(Context context) {
        this.context = context;

        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyNotes").build();
        //creating the database with Room database builder
        //MyNotes is the name of the database
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (Instance == null) {
            Instance = new DatabaseClient(mCtx);
        }
        return Instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
