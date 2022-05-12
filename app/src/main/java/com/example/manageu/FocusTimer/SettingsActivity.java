package com.example.manageu.FocusTimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.manageu.R;
import com.example.manageu.utils.PreferenceUtil;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner work_session = findViewById(R.id.work_session);
        Spinner short_break = findViewById(R.id.short_break);
        Spinner long_break = findViewById(R.id.long_break);

        work_session.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(adapterView.getSelectedItem().toString())
                {
                    case "15 Minutes":
                        PreferenceUtil.Timer_default_val = 15;
                        break;
                    case "25 Minutes":
                        PreferenceUtil.Timer_default_val = 25;
                        break;
                    case "30 Minutes":
                        PreferenceUtil.Timer_default_val = 30;
                        break;
                    case "45 Minutes":
                        PreferenceUtil.Timer_default_val = 45;
                        break;
                    case "60 Minutes":
                        PreferenceUtil.Timer_default_val = 60;
                        break;
                    case "Custom":
                        Intent intent = new Intent(SettingsActivity.this,PopupActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        short_break.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(adapterView.getSelectedItem().toString())
                {
                    case "1 Minute":
                        PreferenceUtil.Timer_default_val = 1;
                        break;
                    case "2 Minutes":
                        PreferenceUtil.Timer_default_val = 2;
                        break;
                    case "5 Minutes":
                        PreferenceUtil.Timer_default_val = 5;
                        break;
                    case "10 Minutes":
                        PreferenceUtil.Timer_default_val = 10;
                        break;
                    case "Custom":
                        Intent intent = new Intent(SettingsActivity.this,PopupActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        long_break.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(adapterView.getSelectedItem().toString())
                {
                    case "5 Minutes":
                        PreferenceUtil.Timer_default_val = 5;
                        break;
                    case "10 Minutes":
                        PreferenceUtil.Timer_default_val = 10;
                        break;
                    case "15 Minutes":
                        PreferenceUtil.Timer_default_val = 15;
                        break;
                    case "30 Minutes":
                        PreferenceUtil.Timer_default_val = 30;
                        break;
                    case "Custom":
                        Intent intent = new Intent(SettingsActivity.this,PopupActivity.class);
                        startActivity(intent);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}