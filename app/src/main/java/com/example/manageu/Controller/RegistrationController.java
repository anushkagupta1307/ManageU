package com.example.manageu.Controller;

import android.content.Context;

import com.example.manageu.Dao.RegistrationDbAccess;
import com.example.manageu.Model.User;

public class RegistrationController {

    Context context;
    public RegistrationController(Context context){
        this.context=context;
    }


    public boolean registerUser(String name, String email, String age, String role, String password, String confirmPassword){

        if(name==null || email== null || role==null|| password==null|| confirmPassword==null)
            return false;

        if(!password.equals(confirmPassword))
            return false;

        User user= new User();
        user.name=name;
        user.email=email;
        user.age=age;
        user.role=role;
        user.password=password;

        new RegistrationDbAccess(context, user).execute();

        return true;

    }

}
