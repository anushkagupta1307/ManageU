package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.manageu.Model.CurUser;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddingActivityTab extends AppCompatActivity {

    EditText  actDetail, start, end;
    Button addBtn;
    Context context=this;
    Spinner actDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_tab);

        actDrop = findViewById(R.id.spinner2);

        CurUser cur = CurUser.getInstance();
        String role= cur.rol;

        String[] items;
        if(role.equals("Student")){
            items= new String[]{"Study", "Sports", "Netflix", "Exercise", "Hobby"};
        }else if(role.equals("Working Professional")){
            items=new String[]{"Work", "Meetings","Sports","Exercise","Netflix", "Hobby"};
        }else{
            items=new String[]{"Cooking","Cleaning","Exercise","Netflix","Hobby"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        actDrop.setAdapter(adapter);

        actDetail = findViewById(R.id.activityDetail);
        start = findViewById(R.id.startTime);
        end = findViewById(R.id.endTime);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //new CalendarActivity().insertEventToCalendar("New Event", "This is a test event", 5, 30, 6,30);


                List startTime = Arrays.asList(start.getText().toString().split(":"));
                List endTime = Arrays.asList(end.getText().toString().split(":"));

//                System.out.println(actDrop.getText().toString());
//                System.out.println(actDetail.getText());
//                System.out.println(actDetail.getText().toString());
//                System.out.println(Integer.parseInt(startTime.get(0).toString()));
//                System.out.println(Integer.parseInt(startTime.get(1).toString()));
//                System.out.println(Integer.parseInt(endTime.get(0).toString()));
//                System.out.println(Integer.parseInt(endTime.get(1).toString()));


                insertEventToCalendar(actDrop.getSelectedItem().toString(), actDetail.getText().toString(), Integer.parseInt(startTime.get(0).toString()),Integer.parseInt(startTime.get(1).toString()), Integer.parseInt(endTime.get(0).toString()),Integer.parseInt(endTime.get(1).toString()));
                Intent i= new Intent(context, TaskActivity.class);
                context.startActivity(i);

            }
        });





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