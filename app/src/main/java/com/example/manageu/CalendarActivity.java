package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalendarActivity extends AppCompatActivity {

    TextView calendartextView;
   // public static String calendar_id;
    //public static ArrayList<String> task_list = new ArrayList();
    //public static ArrayList<String> detail_list = new ArrayList();
    //public static ArrayList<String> time_list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendartextView=findViewById(R.id.calendar);

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
        beginTime.set(2022, monthInNumber-1, dayOfMonth, 00,00,00);

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
                    text+= "ID : "+ cursor.getInt(0)+ "\n";
                  //  text+="Name : "+cursor.getString(1)+"\n";
                    text+="Title : "+cursor.getString(1)+"\n";
                    //task_list.add(cursor.getString(1));
                    text+="Description : "+cursor.getString(2)+"\n\n";
                    //detail_list.add(cursor.getString(2));
                    long duration=cursor.getLong(4)-cursor.getLong(3);
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


                    text+="Duration : "+String.format(" %d Hours %d Minutes", hr, min)+"\n\n";
                    //time_list.add(String.format(" %d Hours %d Minutes", hr, min));
                    cursor.moveToNext();

              //      if(CalendarContract.Calendars.NAME.equals(LoginPage.loggedInUserEmail))
              //          calendar_id=Integer.toString(cursor.getInt(0));

                }
            }
        }
        calendartextView.setText(text);

        insertEventToCalendar("New Event", "This is a test event", 5, 30, 6,30);

    }


    public void insertEventToCalendar(String title, String description, int startHour, int startMinute, int endHour, int endMinute){


        //TODO: Check how to put the event to exact date and time
        //Rest code is working and inserting the event to calendar
       // Calendar start= Calendar.getInstance();
      //  start.set(2022, Calendar.MARCH, 3, startHour, startMinute, 00 );
       // long startMillis=start.getTimeInMillis();

        //Calendar end= Calendar.getInstance();
        //end.set(2022, Calendar.MARCH,3, endHour, endMinute, 00 );
        //long endMillis=end.getTimeInMillis();

        ContentValues values= new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, 1);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        long startMillis = 0;
        long endMillis = 0;

        int dayOfMonth=Integer.parseInt(date.substring(date.length()-2,date.length()));
        //System.out.println(date.substring(date.length()-5,date.length()-3));
        int monthInNumber=Integer.parseInt(date.substring(date.length()-5,date.length()-3));
        Calendar beginTime = Calendar.getInstance();

        beginTime.set(2022, monthInNumber-1, dayOfMonth, startHour,startMinute,00);
        startMillis = beginTime.getTimeInMillis();

        Calendar endTime = Calendar.getInstance();


        endTime.set(2022, monthInNumber-1, dayOfMonth, endHour,endMinute,00);
        endMillis = endTime.getTimeInMillis();




        System.out.println(startMillis);
        System.out.println(endMillis);
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
        values.put(CalendarContract.Events.TITLE, title);
        values.put(CalendarContract.Events.DESCRIPTION, description);

        Uri uri= getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
        System.out.println(uri);

    }
}