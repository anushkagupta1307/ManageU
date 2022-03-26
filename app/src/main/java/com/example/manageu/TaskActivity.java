package com.example.manageu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.manageu.Controller.LoginController;
import com.example.manageu.Dao.DeleteTasksDbAccess;
import com.example.manageu.Dao.TaskDbAccess;
import com.example.manageu.Model.CurUser;
import com.example.manageu.Model.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TaskActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Context context=this;

    ProgressFragment progressFragment = new ProgressFragment();
    StatsFragment statsFragment = new StatsFragment();
    AccountFragment accountFragment = new AccountFragment(context);

    public static Button addTask;

    public static ArrayList<String> task_list = new ArrayList();
    public static ArrayList<String> detail_list = new ArrayList();
    public static ArrayList<String> time_list = new ArrayList();
    public static ArrayList<Integer> id_list = new ArrayList();
    public static List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, 1);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 2);
        }

        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        int dayOfMonth=Integer.parseInt(date.substring(date.length()-2,date.length()));

        int monthInNumber=Integer.parseInt(date.substring(date.length()-5,date.length()-3));

        beginTime.set(2022, monthInNumber-1, dayOfMonth, 00,00,10);

        System.out.println(beginTime);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2022, monthInNumber-1, dayOfMonth, 23,59,59);
        System.out.println(endTime);
        endMillis = endTime.getTimeInMillis();
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);


        Cursor cursor= getContentResolver().query(builder.build(),
                new String[]{CalendarContract.Calendars._ID,
                        CalendarContract.Events.TITLE,  CalendarContract.Events.DESCRIPTION, CalendarContract.Events.DTSTART,CalendarContract.Events.DTEND}, null, null, null);

        String text="";
        taskList=new ArrayList<>();
        if(null!=cursor){
            if(cursor.moveToFirst()){
                for(int i=0;i<cursor.getCount();i++){
                    System.out.println("already existing details");
                    System.out.println(id_list);
                    System.out.println("checking deleted lists");
                    System.out.println(RecyclerAdapter.deleted_list);

                            text += "ID : " + cursor.getInt(0) + "\n";
                            //  text+="Name : "+cursor.getString(1)+"\n";
                            id_list.add(cursor.getInt(0));
                            text += "Title : " + cursor.getString(1) + "\n";
                            task_list.add(cursor.getString(1));
                            text += "Description : " + cursor.getString(2) + "\n\n";
                            detail_list.add(cursor.getString(2));
                            long duration = cursor.getLong(4) - cursor.getLong(3);


                            final long milliseconds = duration;

                            final long hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
                                    - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
                            final long min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                                    - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));


                    String time;
                            if (hr == 0 && min > 1) {
                                text += "Duration : " + String.format(" %d Minutes",min) + "\n\n";
                                time_list.add(String.format(" %d Minutes", min));
                                time=String.format(" %d Minutes", min);
                            } else if (hr == 0 && min == 1) {
                                text += "Duration : " + String.format(" %d Minute", min) + "\n\n";
                                time_list.add(String.format(" %d Minute", min));
                                time= String.format(" %d Minute", min);
                            } else if (hr > 1 && min == 1) {
                                text += "Duration : " + String.format(" %d Hours %d Minute", hr, min) + "\n\n";
                                time_list.add(String.format(" %d Hours %d Minute", hr, min));
                                time= String.format(" %d Hours %d Minute", hr, min);
                            } else if (hr == 1 && min == 0) {
                                text += "Duration : " + String.format(" %d Hour", hr) + "\n\n";
                                time_list.add(String.format(" %d Hour", hr));
                                time= String.format(" %d Hour", hr);
                            } else if (hr == 1 && min > 1) {
                                text += "Duration : " + String.format(" %d Hour %d Minutes", hr, min) + "\n\n";
                                time_list.add(String.format(" %d Hour %d Minutes", hr, min));
                                time= String.format(" %d Hour %d Minutes", hr, min);
                            } else if (min == 0 && hr > 1) {
                                text += "Duration : " + String.format(" %d Hours", hr) + "\n\n";
                                time_list.add(String.format(" %d Hours", hr));
                                time= String.format(" %d Hours", hr);
                            } else {
                                text += "Duration : " + String.format(" %d Hours %d Minutes", hr, min) + "\n\n";
                                time_list.add(String.format(" %d Hours %d Minutes", hr, min));
                                time= String.format(" %d Hours %d Minutes", hr, min);
                            }

                    Task task= new Task();
                    task.id=cursor.getInt(0);
                    task.title=cursor.getString(1);
                    task.detail=cursor.getString(2);
                    task.time=time;
                    task.user_email= LoginPage.loggedInUserEmail;
                    taskList.add(task);
                    cursor.moveToNext();

                }
            }
        }
        DeleteTasksDbAccess deleteTasksDbAccess=new DeleteTasksDbAccess(context, LoginPage.loggedInUserEmail);
        deleteTasksDbAccess.execute();

        TaskDbAccess taskDbAccess=new TaskDbAccess(context,taskList);
        taskDbAccess.execute();

        setContentView(R.layout.activity_task);

        addTask= findViewById(R.id.button3);

        addTask.setVisibility(View.VISIBLE);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context, AddingActivityTab.class);
                context.startActivity(i);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tasks:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new DisplayTasks(context)).commit();
                        return true;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,accountFragment).commit();
                        return true;

                    case R.id.progress:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,progressFragment).commit();
                        return true;

                    case R.id.stats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,statsFragment).commit();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.tasks);
    }

}