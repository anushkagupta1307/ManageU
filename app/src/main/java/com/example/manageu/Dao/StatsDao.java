package com.example.manageu.Dao;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface StatsDao {

    @Query("SELECT stats_list FROM Stats WHERE user_email= :user_email")
    String getStatsList(String user_email);

    @Query("UPDATE Stats set stats_list= :stats_list where user_email= :user_email")
    void updateStatsList(String stats_list, String user_email);


}
