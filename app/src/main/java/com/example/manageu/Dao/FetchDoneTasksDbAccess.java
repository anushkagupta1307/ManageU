package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.LoginPage;
import com.example.manageu.Model.DoneTask;

import java.util.ArrayList;
import java.util.List;

public class FetchDoneTasksDbAccess extends AsyncTask<Void, Void, Void> {

    public static List<DoneTask> doneTaskListOfObjects=new ArrayList<>();

    Context context;
    public FetchDoneTasksDbAccess(Context context){
        this.context=context;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        DoneTaskDao doneTaskDao=db.doneTaskDao();
        doneTaskListOfObjects=doneTaskDao.getAllTasks(LoginPage.loggedInUserEmail);
        return null;
    }
}
