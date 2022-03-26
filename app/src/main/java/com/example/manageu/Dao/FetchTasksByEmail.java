package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class FetchTasksByEmail extends AsyncTask<Void, Void, Void> {

    Context context;
    String email;

    public FetchTasksByEmail(Context context, String email){
        this.context=context;
        this.email=email;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        TaskDao taskDao=db.taskDao();

        return null;
    }
}
