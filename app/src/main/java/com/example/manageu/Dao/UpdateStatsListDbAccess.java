package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.LoginPage;

import java.util.List;

public class UpdateStatsListDbAccess extends AsyncTask<Void,Void,Void> {

    Context context;
    List<Float> user_stats;

    public UpdateStatsListDbAccess(Context context, List<Float> user_stats){
        this.context=context;
        this.user_stats=user_stats;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        StatsDao statsDao=db.statsDao();
        statsDao.updateStatsList(convertListToString(user_stats), LoginPage.loggedInUserEmail);
        return null;
    }

    public String convertListToString(List<Float> user_stats){
        return user_stats.toString();
    }
}
