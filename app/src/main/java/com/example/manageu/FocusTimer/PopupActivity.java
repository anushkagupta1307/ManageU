package com.example.manageu.FocusTimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manageu.R;
import com.example.manageu.utils.PreferenceUtil;

public class PopupActivity extends AppCompatActivity {

    Button set_timer;
    EditText timer_len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        set_timer = (Button) findViewById(R.id.set_button);
        timer_len = (EditText) findViewById(R.id.timerlength);

        set_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(timer_len.getText().toString()))
                    timer_len.setError("This field is empty");
                else {
                    int len = Integer.parseInt(String.valueOf(timer_len.getText()));
                    PreferenceUtil.Timer_default_val = len;
                    Intent intent = new Intent(PopupActivity.this, TimerActivity.class);
                    startActivity(intent);
                }
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.4));


    }
}