package com.example.mynotes.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mynotes.Model.MyNotes;

@Database(entities = {MyNotes.class}, version = 1) //It is an abstract class where we define all our entities
public abstract class AppDatabase extends RoomDatabase {

    public abstract MyNotesDao taskDao();

}
