package com.example.manageu;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manageu.Dao.FetchStatsListDbAccess;


public class StatsFragment extends Fragment {


    public static TextView productivity1,productivityMessage1;
    public static TextView productivity2,productivityMessage2;
    public static TextView productivity3,productivityMessage3;
    public static LinearLayout firstProductivity,secondProductivity,thirdProductivity,logLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // ProgressFragment.calculateProductivityPercentage(getContext());
        TaskActivity.addTask.setVisibility(View.INVISIBLE);
        View view=inflater.inflate(R.layout.fragment_stats, container, false);

        FetchStatsListDbAccess fetchStatsListDbAccess=new FetchStatsListDbAccess(getContext());
        fetchStatsListDbAccess.execute();

        try{
            Thread.sleep(100);
        }
        catch(Exception e){

        }

        int size= FetchStatsListDbAccess.user_stats.size();

        if(size < 3){
            if(size==0)
            {
                firstProductivity=(LinearLayout) view.findViewById(R.id.firstProductivity);
                firstProductivity.setVisibility(View.GONE);
                secondProductivity=(LinearLayout) view.findViewById(R.id.secondProductivity);
                secondProductivity.setVisibility(View.GONE);
                thirdProductivity=(LinearLayout) view.findViewById(R.id.thirdProductivity);
                thirdProductivity.setVisibility(View.GONE);
                logLayout=(LinearLayout) view.findViewById(R.id.logLayout);
                logLayout.setVisibility(View.VISIBLE);

            }
            else if(size==1)
            {
                firstProductivity=(LinearLayout) view.findViewById(R.id.firstProductivity);
                firstProductivity.setVisibility(View.VISIBLE);
                secondProductivity=(LinearLayout) view.findViewById(R.id.secondProductivity);
                secondProductivity.setVisibility(View.GONE);
                thirdProductivity=(LinearLayout) view.findViewById(R.id.thirdProductivity);
                thirdProductivity.setVisibility(View.GONE);
                logLayout=(LinearLayout) view.findViewById(R.id.logLayout);
                logLayout.setVisibility(View.GONE);

                productivity1=view.findViewById(R.id.timeView);
                productivityMessage1=view.findViewById(R.id.detailView);
                productivity1.setText(String.valueOf(FetchStatsListDbAccess.user_stats.get(0)));

                if(Float.compare(FetchStatsListDbAccess.user_stats.get(0),0)==0)
                {
                    productivity1.setTextColor(Color.parseColor("#FF0000"));
                    productivityMessage1.setText("No logs Found for the day");
                }
                else if(FetchStatsListDbAccess.user_stats.get(0)>70)
                {
                    productivity1.setTextColor(Color.parseColor("#3CB371"));
                    productivityMessage1.setText("You're Doing Great!! Keep Going!!");

                }
                else if(FetchStatsListDbAccess.user_stats.get(0)>40)
                {
                    productivity1.setTextColor(Color.parseColor("#3CB371"));
                    productivityMessage1.setText("SLOW AND STEADY WINS THE RACE!");
                }
                else{
                    productivity1.setTextColor(Color.parseColor("#FF0000"));
                    productivityMessage1.setText("WORK HARDER! YOU'RE ALMOST THERE");
                }

            }
            else if(size==2)
            {
                firstProductivity=(LinearLayout) view.findViewById(R.id.firstProductivity);
                firstProductivity.setVisibility(View.VISIBLE);
                secondProductivity=(LinearLayout) view.findViewById(R.id.secondProductivity);
                secondProductivity.setVisibility(View.VISIBLE);
                thirdProductivity=(LinearLayout) view.findViewById(R.id.thirdProductivity);
                thirdProductivity.setVisibility(View.GONE);
                logLayout=(LinearLayout) view.findViewById(R.id.logLayout);
                logLayout.setVisibility(View.GONE);
                productivity1.setText(String.valueOf(FetchStatsListDbAccess.user_stats.get(0)));


                productivity1=view.findViewById(R.id.timeView);
                productivityMessage1=view.findViewById(R.id.detailView);

                if(Float.compare(FetchStatsListDbAccess.user_stats.get(0),0)==0)
                {
                    productivity1.setTextColor(Color.parseColor("#FF0000"));
                    productivityMessage1.setText("No logs Found for the day");
                }
                else if(FetchStatsListDbAccess.user_stats.get(0)>70)
                {
                    productivity1.setTextColor(Color.parseColor("#3CB371"));
                    productivityMessage1.setText("You're Doing Great!! Keep Going!!");

                }
                else if(FetchStatsListDbAccess.user_stats.get(0)>=40)
                {
                    productivity1.setTextColor(Color.parseColor("#3CB371"));
                    productivityMessage1.setText("SLOW AND STEADY WINS THE RACE!");
                }
                else{
                    productivity1.setTextColor(Color.parseColor("#FF0000"));
                    productivityMessage1.setText("WORK HARDER! YOU'RE ALMOST THERE");
                }



                productivity2=view.findViewById(R.id.timeView2);
                productivityMessage2=view.findViewById(R.id.detailView2);
                productivity2.setText(String.valueOf(FetchStatsListDbAccess.user_stats.get(1)));

                if(Float.compare(FetchStatsListDbAccess.user_stats.get(1),0)==0)
                {
                    productivity2.setTextColor(Color.parseColor("#FF0000"));
                    productivityMessage2.setText("No logs Found for the day");
                }
                else if(FetchStatsListDbAccess.user_stats.get(1)>70)
                {
                    productivity2.setTextColor(Color.parseColor("#3CB371"));
                    productivityMessage2.setText("You're Doing Great!! Keep Going!!");

                }
                else if(FetchStatsListDbAccess.user_stats.get(1)>=40)
                {
                    productivity2.setTextColor(Color.parseColor("#3CB371"));
                    productivityMessage2.setText("SLOW AND STEADY WINS THE RACE!");
                }
                else{
                    productivity2.setTextColor(Color.parseColor("#FF0000"));
                    productivityMessage2.setText("WORK HARDER! YOU'RE ALMOST THERE");
                }

            }


            for(int i=0;i<size;i++){
                System.out.println("Productivity of day "+i+" is "+FetchStatsListDbAccess.user_stats.get(i));
            }

        }else{

            firstProductivity=(LinearLayout) view.findViewById(R.id.firstProductivity);
            firstProductivity.setVisibility(View.VISIBLE);
            secondProductivity=(LinearLayout) view.findViewById(R.id.secondProductivity);
            secondProductivity.setVisibility(View.VISIBLE);
            thirdProductivity=(LinearLayout) view.findViewById(R.id.thirdProductivity);
            thirdProductivity.setVisibility(View.VISIBLE);
            logLayout=(LinearLayout) view.findViewById(R.id.logLayout);
            logLayout.setVisibility(View.GONE);


            productivity1=view.findViewById(R.id.timeView);
            productivity2=view.findViewById(R.id.timeView2);
            productivity3=view.findViewById(R.id.timeView3);

            productivityMessage1=view.findViewById(R.id.detailView);
            productivityMessage2=view.findViewById(R.id.detailView2);
            productivityMessage3=view.findViewById(R.id.detailView3);

            float lastday= FetchStatsListDbAccess.user_stats.get(size-1);
            float secondlast=FetchStatsListDbAccess.user_stats.get(size-2);
            float thirdlast=FetchStatsListDbAccess.user_stats.get(size-3);

            productivity1.setText(String.valueOf(lastday));
            productivity2.setText(String.valueOf( secondlast));
            productivity3.setText(String.valueOf( thirdlast));

            if(Float.compare(lastday,0)==0)
            {
                productivity1.setTextColor(Color.parseColor("#FF0000"));
                productivityMessage1.setText("No logs Found for the day");
            }
            else if(lastday>70)
            {
                productivity1.setTextColor(Color.parseColor("#3CB371"));
                productivityMessage1.setText("You're Doing Great!! Keep Going!!");

            }
            else if(lastday>=40)
            {
                productivity1.setTextColor(Color.parseColor("#3CB371"));
                productivityMessage1.setText("SLOW AND STEADY WINS THE RACE!");
            }
            else{
                productivity1.setTextColor(Color.parseColor("#FF0000"));
                productivityMessage1.setText("WORK HARDER! YOU'RE ALMOST THERE");
            }

            if(Float.compare(secondlast,0)==0)
            {
                productivity2.setTextColor(Color.parseColor("#FF0000"));
                productivityMessage2.setText("No logs Found for the day");
            }
            else if(secondlast>70)
            {
                productivity2.setTextColor(Color.parseColor("#3CB371"));
                productivityMessage2.setText("You're Doing Great!! Keep Going!!");

            }
            else if(secondlast>40)
            {
                productivity2.setTextColor(Color.parseColor("#3CB371"));
                productivityMessage2.setText("SLOW AND STEADY WINS THE RACE!");
            }
            else{
                productivity2.setTextColor(Color.parseColor("#FF0000"));
                productivityMessage2.setText("WORK HARDER! YOU'RE ALMOST THERE");
            }

            if(Float.compare(thirdlast,0)==0)
            {
                productivity3.setTextColor(Color.parseColor("#FF0000"));
                productivityMessage3.setText("No logs Found for the day");
            }
            else if(thirdlast>70)
            {
                productivity3.setTextColor(Color.parseColor("#3CB371"));
                productivityMessage3.setText("You're Doing Great!! Keep Going!!");

            }
            else if(thirdlast>=40)
            {
                productivity3.setTextColor(Color.parseColor("#3CB371"));
                productivityMessage3.setText("SLOW AND STEADY WINS THE RACE!");
            }
            else{
                productivity3.setTextColor(Color.parseColor("#FF0000"));
                productivityMessage3.setText("WORK HARDER! YOU'RE ALMOST THERE");
            }



            System.out.println("Last three days productivity ");
            System.out.println("Yesterday's Productivity "+lastday);
            System.out.println("Day before Yesterday's Productivity "+secondlast);
            System.out.println("Third last day's Productivity "+thirdlast);

            float avg= (lastday+secondlast+thirdlast)/3;
            System.out.println("Average of last three days "+avg);

        }


        return view;
    }
}