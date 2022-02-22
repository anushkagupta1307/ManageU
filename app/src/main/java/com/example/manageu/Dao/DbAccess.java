package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.Model.User;

import java.util.List;

public class DbAccess extends AsyncTask<Void, Void, Void> {

    Context c;
    public DbAccess(Context c){
        this.c=c;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(c,
                AppDatabase.class, "database-name").build();

        UserDao userDao = db.userDao();

        User u= new User();
        u.name="anushka";
        u.email="anushka21134@iiitd.ac.in";
        u.role="student";
        u.age=24;
        u.password="pass";
        userDao.insertAll(u);

        List<User> users = userDao.getAll();

        return null;
    }
}
