package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

public class AddingActivityTab extends AppCompatActivity {

    EditText actDrop, actDetail, start, end;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_tab);

        actDrop = findViewById(R.id.activityDropDown);
        actDetail = findViewById(R.id.activityDetail);
        start = findViewById(R.id.startTime);
        end = findViewById(R.id.endTime);
        addBtn = findViewById(R.id.addBtn);
    }

    public void addTask(View v){
        CalendarActivity insertValues = new CalendarActivity();
        List startTime = Arrays.asList(start.getText().toString().split(":"));
        List endTime = Arrays.asList(start.getText().toString().split(":"));
        insertValues.insertEventToCalendar(actDrop.getText().toString(), actDetail.getText().toString(), Integer.parseInt(startTime.get(0).toString()),Integer.parseInt(startTime.get(1).toString()), Integer.parseInt(endTime.get(0).toString()),Integer.parseInt(endTime.get(1).toString()));
        finish();
    }
}