package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.Model.Login;
import com.example.manageu.Model.User;

import java.util.List;

public class RegistrationDbAccess extends AsyncTask<Void, Void, Void> {

    Context context;
    User user;
    public RegistrationDbAccess(Context context, User user){
        this.context=context;
        this.user=user;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        UserDao userDao = db.userDao();
        userDao.insertAll(user);

        LoginDao loginDao=db.loginDao();
        Login login= new Login();
        login.email=user.email;
        login.password=user.password;
        loginDao.insertAll(login);

        List<User> users= userDao.getAll();

        return null;
    }
}
