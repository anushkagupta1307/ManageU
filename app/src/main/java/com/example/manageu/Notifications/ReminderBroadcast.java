package com.example.manageu.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.manageu.R;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int p = intent.getIntExtra("id",1);
        String title = intent.getStringExtra("title");
        String msg = intent.getStringExtra("msg");
//        Bundle bundle = intent.getExtras();
//        String message = bundle.getString("message");


//        Toast.makeText(context,p+"",Toast.LENGTH_SHORT).show();
//        for(int i=1; i<=2; i++){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, p+"")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(p,builder.build());

//        }
    }
}
