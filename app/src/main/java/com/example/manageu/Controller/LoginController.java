package com.example.manageu.Controller;
import android.content.Context;
import android.os.AsyncTask;

import com.example.manageu.Dao.LoginDbAccess;
import com.example.manageu.Model.Login;

public class LoginController {

    Context context;
    public LoginController(Context context){
        this.context=context;
    }


    public boolean checkUserLogin(String email, String password)
    {
        Login login= new Login();
        login.email=email;
        login.password=password;

        LoginDbAccess loginDbAccess= new LoginDbAccess(context,login);
        loginDbAccess.execute();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(LoginDbAccess.loginFlag==true){
            return true;
        }
        return false;
    }
}
