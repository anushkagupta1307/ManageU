package com.example.manageu.Dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.manageu.Model.DoneTask;
import com.example.manageu.Model.Login;
import com.example.manageu.Model.Stats;
import com.example.manageu.Model.Task;
import com.example.manageu.Model.User;

@Database(entities = {User.class, Login.class, Task.class, DoneTask.class, Stats.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract LoginDao loginDao();
    public abstract TaskDao taskDao();
    public abstract DoneTaskDao doneTaskDao();
    public abstract StatsDao statsDao();
}