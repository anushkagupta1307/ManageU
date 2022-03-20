package com.example.manageu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TaskActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Context context=this;

    ProgressFragment progressFragment = new ProgressFragment();
    StatsFragment statsFragment = new StatsFragment();
    AccountFragment accountFragment = new AccountFragment();

    public static Button addTask;

    public static ArrayList<String> task_list = new ArrayList();
    public static ArrayList<String> detail_list = new ArrayList();
    public static ArrayList<String> time_list = new ArrayList();
    public static ArrayList<Integer> id_list = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_calendar);
        //calendartextView=findViewById(R.id.calendar);

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

        //System.out.println(date);
        //System.out.println(date.substring(date.length()-2,date.length()));
        int dayOfMonth=Integer.parseInt(date.substring(date.length()-2,date.length()));
        //System.out.println(date.substring(date.length()-5,date.length()-3));
        int monthInNumber=Integer.parseInt(date.substring(date.length()-5,date.length()-3));
        //System.out.println(monthInNumber);

        // beginTime.set(2017, 11, 15, 6, 00);
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
        if(null!=cursor){
            if(cursor.moveToFirst()){
                for(int i=0;i<cursor.getCount();i++){
                    if(!id_list.contains(cursor.getInt(0))) {
                        text += "ID : " + cursor.getInt(0) + "\n";
                        //  text+="Name : "+cursor.getString(1)+"\n";
                        id_list.add(cursor.getInt(0));
                        text += "Title : " + cursor.getString(1) + "\n";
                        task_list.add(cursor.getString(1));
                        text += "Description : " + cursor.getString(2) + "\n\n";
                        detail_list.add(cursor.getString(2));
                        long duration = cursor.getLong(4) - cursor.getLong(3);
                        //System.out.println(cursor.getLong(4));
                        //System.out.println(cursor.getLong(3));

                        final long milliseconds = duration;
                        //final long dy = TimeUnit.MILLISECONDS.toDays(milliseconds);
                        final long hr = TimeUnit.MILLISECONDS.toHours(milliseconds)
                                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
                        final long min = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
//                    final long sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
//                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
//                    final long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
//                            - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));

                        if(hr==0 && min>1){
                            text += "Duration : " + String.format(" %d Minutes", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Minutes", hr, min));
                        }
                        else if(hr==0 && min==1){
                            text += "Duration : " + String.format(" %d Minute", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Minute", hr, min));
                        }
                        else if(hr>1 && min==1){
                            text += "Duration : " + String.format(" %d Hours %d Minute", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Hours %d Minute", hr, min));
                        }
                        else if(hr==1 && min==0){
                            text += "Duration : " + String.format(" %d Hour", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Hour", hr, min));
                        }else if(hr==1 && min>1){
                            text += "Duration : " + String.format(" %d Hour %d Minutes", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Hour %d Minutes", hr, min));
                        }
                        else if(min==0 && hr>1){
                            text += "Duration : " + String.format(" %d Hours", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Hours", hr, min));
                        }else {
                            text += "Duration : " + String.format(" %d Hours %d Minutes", hr, min) + "\n\n";
                            time_list.add(String.format(" %d Hours %d Minutes", hr, min));
                        }
                    }
                    cursor.moveToNext();

                    //      if(CalendarContract.Calendars.NAME.equals(LoginPage.loggedInUserEmail))
                    //          calendar_id=Integer.toString(cursor.getInt(0));

                }
            }
        }
        //calendartextView.setText(text);

        //insertEventToCalendar("New Event", "This is a test event", 5, 30, 6,30);


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