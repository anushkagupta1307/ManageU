package com.example.manageu.FocusTimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageu.DisplayTasks;
import com.example.manageu.Music.GenreActivity;
import com.example.manageu.Notifications.editNotification;
import com.example.manageu.R;
import com.example.manageu.TaskActivity;
import com.example.manageu.UsageStatsActivity;
import com.example.manageu.recommender;
import com.example.manageu.utils.PreferenceUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class TimerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;

    Context context=this;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

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


        drawerLayout = findViewById(R.id.my_drawer_layout_music);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.i1:
                Intent i1 = new Intent(TimerActivity.this, TaskActivity.class);
                i1.putExtra("tab",1);
                setResult(RESULT_OK,i1);
//                Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i2:
                Intent i2 = new Intent(TimerActivity.this, TaskActivity.class);
                i2.putExtra("tab",2);
                setResult(RESULT_OK,i2);
                Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i3:
                Intent i3 = new Intent(TimerActivity.this, TaskActivity.class);
                i3.putExtra("tab",3);
                setResult(RESULT_OK,i3);
                Toast.makeText(this, "Stats", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i4:
                Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(TimerActivity.this, TaskActivity.class);
                i4.putExtra("tab",7);
                setResult(RESULT_OK,i4);
                finish();
                break;
            case R.id.i5:
                Toast.makeText(this, "Accounts", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent i5 = new Intent(TimerActivity.this, TaskActivity.class);
                i5.putExtra("tab",4);
                setResult(RESULT_OK,i5);
                finish();
                break;
            case R.id.i6:
                Toast.makeText(this, "Focus", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
//                Intent i5 = new Intent(TimerActivity.this, TaskActivity.class);
//                i5.putExtra("tab",5);
//                setResult(RESULT_OK,i5);
//                finish();
            case R.id.i7:
                Intent i6 = new Intent(TimerActivity.this, TaskActivity.class);
                i6.putExtra("tab",6);
                setResult(RESULT_OK,i6);
                finish();
                break;
            case R.id.i8:
                Intent i7 = new Intent(TimerActivity.this, TaskActivity.class);
                i7.putExtra("tab",8);
                setResult(RESULT_OK,i7);
                finish();
                break;
            case R.id.i9:
                Toast.makeText(this, "Create Reminder", Toast.LENGTH_SHORT).show();
                Intent i8 = new Intent(TimerActivity.this, TaskActivity.class);
                i8.putExtra("tab", 9);
                setResult(RESULT_OK,i8);
                finish();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new DisplayTasks(context)).commit();
        }
        return true;
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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}