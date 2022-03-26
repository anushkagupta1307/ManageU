package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class DeleteTasksDbAccess extends AsyncTask<Void,Void,Void> {

    Context context;
    String user_email;

    public DeleteTasksDbAccess(Context context, String user_email){
        this.context=context;
        this.user_email=user_email;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        TaskDao taskDao = db.taskDao();
        taskDao.deleteTaskByUserEmail(user_email);
        return null;
    }
}
