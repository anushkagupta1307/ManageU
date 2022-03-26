package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.Model.Task;

import java.util.List;

public class TaskDbAccess extends AsyncTask<Void,Void,Void> {


    Context context;
    List<Task> taskList;

    public TaskDbAccess(Context context, List<Task> taskList){
        this.context=context;
        this.taskList=taskList;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        TaskDao taskDao = db.taskDao();
        taskDao.insertAll(taskList);

        return null;
    }
}
