package com.example.manageu.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Login {

    @PrimaryKey
    @NonNull
    public String email;

    @ColumnInfo(name="password")
    public String password;


}
