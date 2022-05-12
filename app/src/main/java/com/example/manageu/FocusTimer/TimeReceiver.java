package com.example.manageu.FocusTimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.manageu.utils.PreferenceUtil;

public class TimeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PreferenceUtil.setTimerState(TimerActivity.TimerState.Stopped, context);
        PreferenceUtil.setAlarmTime(0, context);
    }
}