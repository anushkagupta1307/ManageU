package com.example.manageu.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DoneTask {

    @PrimaryKey
    @NonNull
    public Integer id;

    @ColumnInfo(name="user_email")
    public String user_email;

    @ColumnInfo(name="done")
    public Integer done;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "time")
    public String time;

}

