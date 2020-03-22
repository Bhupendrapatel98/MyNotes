package com.example.mynotes.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.Model.MyNotes;

import java.util.List;

@Dao   //It is an interface that defines all the operations that we need to perform in our database.
public interface MyNotesDao {

    @Query("SELECT * FROM MyNotes")
    List<MyNotes> getAll();

    @Insert
    void insert(MyNotes myNotes);

    @Delete
    void delete(MyNotes myNotes);

    @Update
    void update(MyNotes myNotes);
}
