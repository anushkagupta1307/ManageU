package com.example.manageu.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.manageu.Model.DoneTask;


import java.util.List;

@Dao
public interface DoneTaskDao {

    @Query("SELECT id FROM DoneTask")
    List<Integer> getAll();

    @Query("SELECT * FROM DoneTask where user_email= :email")
    List<DoneTask> getAllTasks(String email);

    @Insert
    void insert(DoneTask donetask);


}
