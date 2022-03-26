package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.Model.CurUser;
import com.example.manageu.Model.User;

public class   UserProfileAccess extends AsyncTask<Void, Void,Void> {

    public static boolean loginFlag=false;

    public static User loggedInUser;

    Context context;
    String em;
    public UserProfileAccess(Context context, String em){
        this.context= context;
        this.em=em;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        UserDao userDao = db.userDao();
        User user=userDao.getUserData(this.em);
        loggedInUser=user;
        CurUser curUser = CurUser.getInstance();
        curUser.nam=user.name;
        curUser.em= user.email;
        curUser.age=user.age;
        curUser.rol = user.role;
        return null;
    }

}
