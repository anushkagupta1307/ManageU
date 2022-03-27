package com.example.manageu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.manageu.Dao.FetchDoneTasksDbAccess;
import com.example.manageu.Dao.FetchStatsListDbAccess;
import com.example.manageu.Dao.UpdateStatsListDbAccess;
import com.example.manageu.Dao.UserProfileAccess;
import com.example.manageu.Model.DoneTask;
import com.example.manageu.Model.Task;
import com.example.manageu.Model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgressFragment extends Fragment {



    public ProgressFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TaskActivity.addTask.setVisibility(View.INVISIBLE);
        calculateProductivityPercentage(getContext());
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    public static void calculateProductivityPercentage(Context context){

        List<Task> taskList= TaskActivity.taskList;

        FetchDoneTasksDbAccess fetchDoneTasksDbAccess =new FetchDoneTasksDbAccess(context);
        fetchDoneTasksDbAccess.execute();
        try {
            Thread.sleep(100);
        }
        catch (Exception e){
        }

        List<DoneTask> doneTasks= FetchDoneTasksDbAccess.doneTaskListOfObjects;

       User loggedUser= UserProfileAccess.loggedInUser;
        FetchStatsListDbAccess fetchStatsListDbAccess=new FetchStatsListDbAccess(context);
        fetchStatsListDbAccess.execute();

       if (loggedUser.role.equals("Student")){

           float totalStudy=0;
           float totalSports=0;
           float totalNetflix=0;
           float totalExercise=0;
           float totalHobby=0;

           float totalStudyDone=0;
           float totalSportsDone=0;
           float totalNetflixDone=0;
           float totalExerciseDone=0;
           float totalHobbyDone=0;

           for(int i=0;i<taskList.size();i++){
               switch (taskList.get(i).title){
                   case "Study":
                       float time1=convertTime(taskList.get(i).time);
                       totalStudy+=time1;
                       break;
                   case "Sports":
                       float time2=convertTime(taskList.get(i).time);
                       totalSports+=time2;
                       break;
                   case "Netflix":
                       float time3=convertTime(taskList.get(i).time);
                       totalNetflix+=time3;
                       break;
                   case "Exercise":
                       float time4=convertTime(taskList.get(i).time);
                       totalExercise+=time4;
                       break;
                   case "Hobby":
                       float time5=convertTime(taskList.get(i).time);
                       totalHobby+=time5;
                       break;
               }
           }

           for(int i=0;i<doneTasks.size();i++){
               switch (doneTasks.get(i).title){
                   case "Study":
                       float time1= convertTime(doneTasks.get(i).time);
                       totalStudyDone+=time1;
                       break;
                   case "Sports":
                       float time2= convertTime(doneTasks.get(i).time);
                       totalSportsDone+=time2;
                       break;
                   case "Netflix":
                       float time3= convertTime(doneTasks.get(i).time);
                       totalNetflixDone+=time3;
                       break;
                   case "Exercise":
                       float time4= convertTime(doneTasks.get(i).time);
                       totalExerciseDone+=time4;
                       break;
                   case "Hobby":
                       float time5= convertTime(doneTasks.get(i).time);
                       totalHobbyDone+=time5;
                       break;
               }
           }

           System.out.println("Total Study "+totalStudy);
           System.out.println("Total Sports "+totalSports);
           System.out.println("Total Netflix "+totalNetflix);
           System.out.println("Total Exercise "+totalExercise);
           System.out.println("Total Hobby "+totalHobby);

           System.out.println("Total Study Done"+totalStudyDone);
           System.out.println("Total Sports Done"+totalSportsDone);
           System.out.println("Total Netflix Done "+totalNetflixDone);
           System.out.println("Total Exercise Done"+totalExerciseDone);
           System.out.println("Total Hobby Done "+totalHobbyDone);

           float studyProductivity= totalStudy!=0?(totalStudyDone/totalStudy)*100:0;
           float sportsProductivity=totalSports!=0?(totalSportsDone/totalSports)*100:0;
           float netflixProductivity=totalNetflix!=0?(totalNetflixDone/totalNetflix)*100:0;
           float exerciseProductivity=totalExercise!=0?(totalExerciseDone/totalExercise)*100:0;
           float hobbyProductivity=totalHobby!=0?(totalHobbyDone/totalHobby)*100:0;

           float totalProductivity=0;
           int count=0;
           if(totalStudy!=0) {
               totalProductivity += studyProductivity;
                count++;
           }
           if(totalSports!=0){
               totalProductivity+=sportsProductivity;
               count++;
           }
           if(totalNetflix!=0){
               totalProductivity+=netflixProductivity;
               count++;
           }
           if(totalExercise!=0){
               totalProductivity+=exerciseProductivity;
               count++;
           }
           if(totalHobby!=0){
               totalProductivity+=hobbyProductivity;
               count++;
           }

           float averageProductivity=totalProductivity/count;

           System.out.println("Study Productivity"+studyProductivity);
           System.out.println("Sports Productivity"+sportsProductivity);
           System.out.println("Netflix Productivity "+netflixProductivity);
           System.out.println("Exercise Productivity"+exerciseProductivity);
           System.out.println("Hobby Productivity "+hobbyProductivity);
           System.out.println("Average Productivity "+averageProductivity);

           FetchStatsListDbAccess.user_stats.add(averageProductivity);


        }else if(loggedUser.role.equals("Working Professional"))
       {
           float totalWork=0;
           float totalMeetings=0;
           float totalSports=0;
           float totalExercise=0;
           float totalNetflix=0;
           float totalHobby=0;

           float totalWorkDone=0;
           float totalMeetingsDone=0;
           float totalSportsDone=0;
           float totalExerciseDone=0;
           float totalNetflixDone=0;
           float totalHobbyDone=0;

           for(int i=0;i<taskList.size();i++){
               switch (taskList.get(i).title){
                   case "Work":
                       float time1=convertTime(taskList.get(i).time);
                       totalWork+=time1;
                       break;
                   case "Meetings":
                       float time2=convertTime(taskList.get(i).time);
                       totalMeetings+=time2;
                       break;
                   case "Sports":
                       float time3=convertTime(taskList.get(i).time);
                       totalSports+=time3;
                       break;
                   case "Exercise":
                       float time4=convertTime(taskList.get(i).time);
                       totalExercise+=time4;
                       break;
                   case "Netflix":
                       float time5=convertTime(taskList.get(i).time);
                       totalNetflix+=time5;
                       break;
                   case "Hobby":
                       float time6=convertTime(taskList.get(i).time);
                       totalHobby+=time6;
                       break;
               }
           }
           for(int i=0;i<doneTasks.size();i++){
               switch (doneTasks.get(i).title){
                   case "Work":
                       float time1=convertTime(doneTasks.get(i).time);
                       totalWorkDone+=time1;
                       break;
                   case "Meetings":
                       float time2=convertTime(doneTasks.get(i).time);
                       totalMeetingsDone+=time2;
                       break;
                   case "Sports":
                       float time3=convertTime(doneTasks.get(i).time);
                       totalSportsDone+=time3;
                       break;
                   case "Exercise":
                       float time4=convertTime(doneTasks.get(i).time);
                       totalExerciseDone+=time4;
                       break;
                   case "Netflix":
                       float time5=convertTime(doneTasks.get(i).time);
                       totalNetflixDone+=time5;
                       break;
                   case "Hobby":
                       float time6=convertTime(doneTasks.get(i).time);
                       totalHobbyDone+=time6;
                       break;
               }
           }
           System.out.println("Total Work "+totalWork);
           System.out.println("Total Meetings "+totalMeetings);
           System.out.println("Total Sports "+totalSports);
           System.out.println("Total Exercise "+totalExercise);
           System.out.println("Total Netflix "+totalNetflix);
           System.out.println("Total Hobby "+totalHobby);

           System.out.println("Total Work Done "+totalWorkDone);
           System.out.println("Total Meetings Done"+totalMeetingsDone);
           System.out.println("Total Sports Done"+totalSportsDone);
           System.out.println("Total Exercise Done"+totalExerciseDone);
           System.out.println("Total Netflix Done"+totalNetflixDone);
           System.out.println("Total Hobby Done"+totalHobbyDone);

           float workProductivity= totalWork!=0?(totalWorkDone/totalWork)*100:0;
           float meetingsProductivity=totalMeetings!=0?(totalMeetingsDone/totalMeetings)*100:0;
           float sportsProductivity=totalSports!=0?(totalSportsDone/totalSports)*100:0;
           float exerciseProductivity=totalExercise!=0?(totalExerciseDone/totalExercise)*100:0;
           float netflixProductivity=totalNetflix!=0?(totalNetflixDone/totalNetflix)*100:0;
           float hobbyProductivity=totalHobby!=0?(totalHobbyDone/totalHobby)*100:0;

           float totalProductivity=0;
           int count=0;
           if(totalWork!=0) {
               totalProductivity += workProductivity;
               count++;
           }
           if(totalMeetings!=0){
               totalProductivity+=meetingsProductivity;
               count++;
           }
           if(totalSports!=0){
               totalProductivity+=sportsProductivity;
               count++;
           }
           if(totalNetflix!=0){
               totalProductivity+=netflixProductivity;
               count++;
           }
           if(totalExercise!=0){
               totalProductivity+=exerciseProductivity;
               count++;
           }
           if(totalHobby!=0){
               totalProductivity+=hobbyProductivity;
               count++;
           }
           float averageProductivity=totalProductivity/count;
           System.out.println("Work Productivity"+workProductivity);
           System.out.println("Meetings Productivity"+meetingsProductivity);
           System.out.println("Sports Productivity"+sportsProductivity);
           System.out.println("Exercise Productivity"+exerciseProductivity);
           System.out.println("Netflix Productivity "+netflixProductivity);
           System.out.println("Hobby Productivity "+hobbyProductivity);
           System.out.println("Average Productivity "+averageProductivity);

           FetchStatsListDbAccess.user_stats.add(averageProductivity);

       }else {

           float totalCooking=0;
           float totalCleaning=0;
           float totalExercise=0;
           float totalNetflix=0;
           float totalHobby=0;

           float totalCookingDone=0;
           float totalCleaningDone=0;
           float totalExerciseDone=0;
           float totalNetflixDone=0;
           float totalHobbyDone=0;

           for(int i=0;i<taskList.size();i++){
               switch (taskList.get(i).title){
                   case "Cooking":
                       float time1=convertTime(taskList.get(i).time);
                       totalCooking+=time1;
                       break;
                   case "Cleaning":
                       float time2=convertTime(taskList.get(i).time);
                       totalCleaning+=time2;
                       break;
                   case "Exercise":
                       float time4=convertTime(taskList.get(i).time);
                       totalExercise+=time4;
                       break;
                   case "Netflix":
                       float time3=convertTime(taskList.get(i).time);
                       totalNetflix+=time3;
                       break;
                   case "Hobby":
                       float time5=convertTime(taskList.get(i).time);
                       totalHobby+=time5;
                       break;
               }
           }

           for(int i=0;i<doneTasks.size();i++){
               switch (doneTasks.get(i).title){
                   case "Cooking":
                       float time1=convertTime(doneTasks.get(i).time);
                       totalCookingDone+=time1;
                       break;
                   case "Cleaning":
                       float time2=convertTime(doneTasks.get(i).time);
                       totalCleaningDone+=time2;
                       break;
                   case "Exercise":
                       float time4=convertTime(doneTasks.get(i).time);
                       totalExerciseDone+=time4;
                       break;
                   case "Netflix":
                       float time3=convertTime(doneTasks.get(i).time);
                       totalNetflixDone+=time3;
                       break;
                   case "Hobby":
                       float time5=convertTime(doneTasks.get(i).time);
                       totalHobbyDone+=time5;
                       break;
               }
           }

           System.out.println("Total Cooking "+totalCooking);
           System.out.println("Total Cleaning "+totalCleaning);
           System.out.println("Total Exercise "+totalExercise);
           System.out.println("Total Netflix "+totalNetflix);
           System.out.println("Total Hobby "+totalHobby);

           System.out.println("Total Cooking Done"+totalCookingDone);
           System.out.println("Total Cleaning Done"+totalCleaningDone);
           System.out.println("Total Exercise Done"+totalExerciseDone);
           System.out.println("Total Netflix Done"+totalNetflixDone);
           System.out.println("Total Hobby Done"+totalHobbyDone);

           float cookingProductivity= totalCooking!=0?(totalCookingDone/totalCooking)*100:0;
           float cleaningProductivity=totalCleaning!=0?(totalCleaningDone/totalCleaning)*100:0;
           float exerciseProductivity=totalExercise!=0?(totalExerciseDone/totalExercise)*100:0;
           float netflixProductivity=totalNetflix!=0?(totalNetflixDone/totalNetflix)*100:0;
           float hobbyProductivity=totalHobby!=0?(totalHobbyDone/totalHobby)*100:0;
           float totalProductivity=0;
           int count=0;
           if(totalCooking!=0) {
               totalProductivity += cookingProductivity;
               count++;
           }
           if(totalCleaning!=0){
               totalProductivity+=cleaningProductivity;
               count++;
           }
           if(totalNetflix!=0){
               totalProductivity+=netflixProductivity;
               count++;
           }
           if(totalExercise!=0){
               totalProductivity+=exerciseProductivity;
               count++;
           }
           if(totalHobby!=0){
               totalProductivity+=hobbyProductivity;
               count++;
           }

           float averageProductivity=totalProductivity/count;


           System.out.println("Cooking Productivity"+cookingProductivity);
           System.out.println("Cleaning Productivity"+cleaningProductivity);
           System.out.println("Netflix Productivity "+netflixProductivity);
           System.out.println("Exercise Productivity"+exerciseProductivity);
           System.out.println("Hobby Productivity "+hobbyProductivity);
           System.out.println("Average Productivity "+averageProductivity);

           FetchStatsListDbAccess.user_stats.add(averageProductivity);

       }

        UpdateStatsListDbAccess updateStatsListDbAccess=new UpdateStatsListDbAccess(context, FetchStatsListDbAccess.user_stats);
        updateStatsListDbAccess.execute();

    }

    public static float convertTime(String time){

        float mins=0;
        String[] splited = time.split("\\s+");

        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<Arrays.asList(splited).size();i++){
            if(!splited[i].equals(""))
                list.add(splited[i]);
        }

        if(list.size()==4){
            mins=Float.parseFloat(list.get(0))*60;
            mins+=Float.parseFloat(list.get(2));

        }
        else{
            if(time.indexOf("H")!=-1)
                mins=Float.parseFloat(list.get(0))*60;
            else
                mins=Float.parseFloat(list.get(0));
        }
        return mins;

    }

}