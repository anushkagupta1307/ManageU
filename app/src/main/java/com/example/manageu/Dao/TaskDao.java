package com.example.manageu.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.manageu.Model.Task;


import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Insert
    void insertAll(List<Task> tasks);

    @Query("SELECT * FROM Task WHERE user_email= :user_email")
    List<Task> getTasks(String user_email);

    @Query("DELETE FROM Task where user_email= :email")
    void deleteTaskByUserEmail(String email);

}
