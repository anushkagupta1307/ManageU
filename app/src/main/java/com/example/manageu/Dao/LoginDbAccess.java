package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.Model.Login;

public class LoginDbAccess extends AsyncTask<Void, Void,Void> {

    public static boolean loginFlag=false;

    Context context;
    Login login;
    public LoginDbAccess(Context context, Login login){
       this.context= context;
       this.login=login;
   }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        LoginDao loginDao = db.loginDao();

        Login loginObj=loginDao.checkUserLogin(login.email, login.password);
        if(loginObj!=null)
        loginFlag=true;

        return null;
    }

}
