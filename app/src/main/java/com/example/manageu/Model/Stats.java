package com.example.manageu.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Stats {

    @PrimaryKey
    @NonNull
    public String user_email;

    @ColumnInfo(name= "stats_list")
    public String stats_list;


}
