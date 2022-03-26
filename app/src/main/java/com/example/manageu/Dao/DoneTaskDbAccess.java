package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import com.example.manageu.Model.DoneTask;

public class DoneTaskDbAccess extends AsyncTask<Void, Void, Void> {

    Context context;
    DoneTask doneTask;

    public DoneTaskDbAccess(Context context, DoneTask doneTask){
        this.context=context;
        this.doneTask=doneTask;
    }



    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        DoneTaskDao doneTaskDao = db.doneTaskDao();
        doneTaskDao.insert(doneTask);
        return null;
    }
}
