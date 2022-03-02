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
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    TextView calendartextView;
   // public static String calendar_id;

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
       // beginTime.set(2017, 11, 15, 6, 00);
        beginTime.set(2022, Calendar.MONTH, Calendar.DATE, 00,00,00);

        System.out.println(beginTime);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2022, Calendar.MONTH, Calendar.DATE, 23,59,59);
        System.out.println(endTime);
        endMillis = endTime.getTimeInMillis();
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);


        Cursor cursor= getContentResolver().query(builder.build(),
                new String[]{CalendarContract.Calendars._ID,
                CalendarContract.Events.TITLE,  CalendarContract.Events.DESCRIPTION}, null, null, null);

        String text="";
        if(null!=cursor){
            if(cursor.moveToFirst()){
                for(int i=0;i<cursor.getCount();i++){
                    text+= "ID : "+ cursor.getInt(0)+ "\n";
                  //  text+="Name : "+cursor.getString(1)+"\n";
                    text+="Title : "+cursor.getString(1)+"\n";
                    text+="Description : "+cursor.getString(2)+"\n\n";
                    cursor.moveToNext();

              //      if(CalendarContract.Calendars.NAME.equals(LoginPage.loggedInUserEmail))
              //          calendar_id=Integer.toString(cursor.getInt(0));

                }
            }
        }
        calendartextView.setText(text);

        insertEventToCalendar("Testing Event", "This is a test event", 5, 30, 6,30);

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
        values.put(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis());
        values.put(CalendarContract.Events.DTEND, Calendar.getInstance().getTimeInMillis()+60*60*1000);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
        values.put(CalendarContract.Events.TITLE, title);
        values.put(CalendarContract.Events.DESCRIPTION, description);

        Uri uri= getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
        System.out.println(uri);

    }
}