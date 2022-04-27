package com.example.manageu.Notifications;

import static android.content.Context.ALARM_SERVICE;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

import com.example.manageu.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NotificationClass {

    Context mContext;
    public NotificationClass(Context context){
        this.mContext = context;
    }

    public void setNotification(String title, String msg){

        Intent intent = new Intent(mContext, ReminderBroadcast.class);

        intent.putExtra("id", "2");
        intent.putExtra("title",title);
        intent.putExtra("msg", msg);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        long tenSecondsInMillis = 1000;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:00");
//        Date date = new Date(timeAtButtonClick);
//        String time = simpleDateFormat.format(date);
//        Toast.makeText(mContext, time.toString(), Toast.LENGTH_SHORT).show();
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick + tenSecondsInMillis,pendingIntent);
    }
    public void scheduleNotification(String title, String msg){

        Intent intent = new Intent(mContext, ReminderBroadcast.class);

        intent.putExtra("id", "2");
        intent.putExtra("title","Tasks");
        intent.putExtra("msg", "Complete Your Tasks");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(mContext, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        Date dat  = new Date();//initializes to now
        Calendar cal_alarm = Calendar.getInstance();
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,15);//set the alarm time
        cal_alarm.set(Calendar.MINUTE, 00);
        cal_alarm.set(Calendar.SECOND,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                cal_alarm.getTimeInMillis(),pendingIntent);

        cal_alarm.set(Calendar.HOUR_OF_DAY,20);//set the alarm time
        cal_alarm.set(Calendar.MINUTE, 00);
        cal_alarm.set(Calendar.SECOND,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                cal_alarm.getTimeInMillis(),pendingIntent1);
    }

    public void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "LemubitReminderChannel";
            String description = "Channel for Lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            NotificationChannel channel2 = new NotificationChannel("2", name, importance);
            channel.setDescription(description);
            channel2.setDescription(description);

            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(channel2);
        }
    }
}
