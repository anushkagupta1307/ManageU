package com.example.manageu.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.manageu.Model.Login;

@Dao
public interface LoginDao {

    @Query("select * from Login where email= :email and password= :password")
    Login checkUserLogin(String email, String password);

    @Insert
    void insertAll(Login... login);

}
