package com.example.manageu.Dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.manageu.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class FetchStatsListDbAccess extends AsyncTask<Void, Void, Void> {

    Context context;

    public FetchStatsListDbAccess(Context context){
        this.context=context;
    }

    public static List<Float> user_stats=new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").build();

        StatsDao statsDao=db.statsDao();
        String stats=statsDao.getStatsList(LoginPage.loggedInUserEmail);
        convertStringToList(stats);

        return null;
    }

    public void convertStringToList(String stats) {

        if (stats != null) {
            String[] arr = stats.split(",");

            for (int i = 0; i < arr.length; i++) {
                user_stats.add(Float.parseFloat(arr[i]));
            }
        }
    }

}
