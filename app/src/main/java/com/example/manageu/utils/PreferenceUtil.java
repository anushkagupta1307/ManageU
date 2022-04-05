package com.example.manageu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.manageu.FocusTimer.TimerActivity;

public class PreferenceUtil {

    private static final int Timer_default_val = 25;
    private static final String Timer_len_ID="manageUApp_timerLengthID";
    private static final String Prev_timer_len_ID="manageUApp_PrevTimerLengthID";
    private static final String Timer_stateID = "manageUApp_timerState";
    private static final String Second_remain = "manageUApp_sec_remain";
    private static String Alarm_SetTime_ID = "manageUApp_alarm_setTime";


    public static int getTimerLen(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getInt(Timer_len_ID,Timer_default_val);
    }

    public static long getPrevTimerLength(Context context)
    {
        SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getLong(Prev_timer_len_ID,0);
    }

    public static void setPrevTimerLength(long second,Context context)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putLong(Prev_timer_len_ID,second);
        editor.apply();
    }

    public static TimerActivity.TimerState getTimerState(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int ordinal = pref.getInt(Timer_stateID,0);
        return TimerActivity.TimerState.values()[ordinal];
    }

    public static void setTimerState(TimerActivity.TimerState timerState,Context context)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        int ordinal = timerState.ordinal();
        editor.putInt(Timer_stateID,ordinal);
        editor.apply();
    }

    public static long getSecondsRemain(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getLong(Second_remain,0);
    }

    public static void setSecondsRemain(long sec,Context context)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putLong(Second_remain,sec);
        editor.apply();
    }

    //  Code to handle the background timer operation...

    public static long getAlarmTime(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getLong(Alarm_SetTime_ID,0);
    }

    public static void setAlarmTime(long time,Context context)
    {
        SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putLong(Alarm_SetTime_ID,time);
        editor.apply();

    }

}
