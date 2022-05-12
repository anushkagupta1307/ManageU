package com.example.manageu.FocusTimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageu.R;
import com.example.manageu.utils.PreferenceUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class TimerActivity extends AppCompatActivity {

    public enum TimerState
    {
        Stopped,Paused,Running
    }
    private CountDownTimer timer;
    private long timer_len = 0L;
    private TimerState timer_state = TimerState.Stopped;
    private long seconds_remaining = 0L;

    FloatingActionButton fab_start;
    FloatingActionButton fab_stop;
    FloatingActionButton fab_pause;
    MaterialProgressBar progressBar;
    TextView countdown;
    Button timer_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        fab_start = findViewById(R.id.play_button);
        fab_pause = findViewById(R.id.pause_button);
        fab_stop = findViewById(R.id.stop_button);
        progressBar = findViewById(R.id.progress_bar);
        countdown = findViewById(R.id.textView_countdown);
        timer_settings = findViewById(R.id.timer_settings);

        timer_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });




        // start button
        fab_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
                timer_state = TimerState.Running;
                buttonUpdate();
            }
        });

        // pause button
        fab_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                timer_state = TimerState.Paused;
                buttonUpdate();
            }
        });

        // stop button
        fab_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                onTimerFinish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initialise_timer();
        removeAlarm(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer_state==TimerState.Running)
        {
            timer.cancel();
            // show notification
            long wakeUpTime = setAlarm(this, curr_seconds, seconds_remaining);
        }
        else if(timer_state==TimerState.Paused)
        {
            // show notification
        }
        PreferenceUtil.setPrevTimerLengthSecond(timer_len,this);
        PreferenceUtil.setTimerState(timer_state,this);
        PreferenceUtil.setSecondsRemain(seconds_remaining,this);
    }

    private void initialise_timer()
    {
        timer_state = PreferenceUtil.getTimerState(this);
        if(timer_state==TimerState.Stopped)
        {
            setNewTimerLen();
        }
        else
        {
            setPrevTimerLen();
        }

        seconds_remaining = (timer_state == TimerState.Running || timer_state== TimerState.Paused)?
                PreferenceUtil.getSecondsRemain(this): timer_len;

        //background tasks...
        long alarmSetTime = PreferenceUtil.getAlarmTime(this);
        if (alarmSetTime > 0)  // means alarm is set
        {
            seconds_remaining -= (curr_seconds - alarmSetTime);
        }
        if (seconds_remaining <= 0) {
            onTimerFinish();
        }
        else if(timer_state==TimerState.Running)
            startTimer();

        buttonUpdate();
        updateCountdown();
    }
    private void onTimerFinish()
    {
        timer_state=TimerState.Stopped;
        setNewTimerLen();
        progressBar.setProgress(0);
        PreferenceUtil.setSecondsRemain(timer_len,this);
        seconds_remaining = timer_len;
        buttonUpdate();
        updateCountdown();
    }
    private void startTimer()
    {
        timer_state = TimerState.Running;
        timer = new CountDownTimer(seconds_remaining * 1000, 1000) {
            @Override
            public void onTick(long milliSecUntilFinish) {
                seconds_remaining = milliSecUntilFinish / 1000;
                updateCountdown();
            }

            @Override
            public void onFinish() {
                onTimerFinish();
            }
        }.start();
    }
    private void setNewTimerLen()
    {
        int len_in_min = PreferenceUtil.getTimerLen(this);
        timer_len = len_in_min * 60L;
        progressBar.setMax((int)timer_len);
    }
    private void setPrevTimerLen()
    {
        timer_len = PreferenceUtil.getPrevTimerLengthSecond(this);
        progressBar.setMax((int)timer_len);
    }
    private void updateCountdown()
    {
        long min_until_finish,sec_until_finish;
        String secondStr,second_updated,time_updated;

        min_until_finish = seconds_remaining/60;
        sec_until_finish = seconds_remaining - min_until_finish * 60;
        secondStr = String.valueOf(sec_until_finish);

        //updating the text view countdown..

        second_updated = (secondStr.length() == 2) ?
                secondStr
                : "0" + secondStr;
        time_updated = min_until_finish + ":" + second_updated;
        countdown.setText(time_updated);
        progressBar.setProgress((int)(timer_len - seconds_remaining));
    }
    private void buttonUpdate()
    {
        switch (timer_state)
        {
            case Running:
                fab_start.setEnabled(false);
                fab_pause.setEnabled(true);
                fab_stop.setEnabled(true);
                break;

            case Paused:
                fab_start.setEnabled(true);
                fab_pause.setEnabled(false);
                fab_stop.setEnabled(true);
                break;

            case Stopped:
                fab_start.setEnabled(true);
                fab_pause.setEnabled(false);
                fab_stop.setEnabled(false);
                break;

        }
    }

    public static long curr_seconds = Calendar.getInstance().getTimeInMillis() / 1000;

    public static long setAlarm(Context context, long curr_seconds, long seconds_remaining) {
        long wakeUpTime = (curr_seconds + seconds_remaining) * 1000;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent after_alarm = new Intent(context, TimeReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, after_alarm, 0);

        // Setting the alarm
        // Sets alarm
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent);
        }
        PreferenceUtil.setAlarmTime(curr_seconds, context);
        return wakeUpTime;
    }

    public static void removeAlarm(Context context){
        Intent removeAlarmIntent = new Intent(context, TimeReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, removeAlarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        PreferenceUtil.setAlarmTime(0, context);
    }


}