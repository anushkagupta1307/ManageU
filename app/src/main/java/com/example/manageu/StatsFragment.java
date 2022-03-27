package com.example.manageu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manageu.Dao.FetchStatsListDbAccess;


public class StatsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // ProgressFragment.calculateProductivityPercentage(getContext());

        FetchStatsListDbAccess fetchStatsListDbAccess=new FetchStatsListDbAccess(getContext());
        fetchStatsListDbAccess.execute();

        try{
            Thread.sleep(100);
        }
        catch(Exception e){

        }

        int size= FetchStatsListDbAccess.user_stats.size();

        if(size < 3){
            for(int i=0;i<size;i++){
                System.out.println("Productivity of day "+i+" is "+FetchStatsListDbAccess.user_stats.get(i));
            }

        }else{
            float lastday= FetchStatsListDbAccess.user_stats.get(size-1);
            float secondlast=FetchStatsListDbAccess.user_stats.get(size-2);
            float thirdlast=FetchStatsListDbAccess.user_stats.get(size-3);

            System.out.println("Last three days productivity ");
            System.out.println("Yesterday's Productivity "+lastday);
            System.out.println("Day before Yesterday's Productivity "+secondlast);
            System.out.println("Third last day's Productivity "+thirdlast);

            float avg= (lastday+secondlast+thirdlast)/3;
            System.out.println("Average of last three days "+avg);

        }

        TaskActivity.addTask.setVisibility(View.INVISIBLE);
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }
}