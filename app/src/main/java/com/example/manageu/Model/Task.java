package com.example.manageu.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"id","user_email"})
public class Task {

    @ColumnInfo(name="id")
    @NonNull
    public Integer id;

    @ColumnInfo(name="user_email")
    @NonNull
    public String user_email;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "detail")
    public String detail;

    @ColumnInfo(name = "time")
    public String time;

}
