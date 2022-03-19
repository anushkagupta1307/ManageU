package com.example.manageu.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.manageu.Model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email IN (:emails)")
    List<User> loadAllByIds(String[] emails);

    @Query("SELECT * FROM user WHERE email= :emai")
    User getUserData(String emai);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}