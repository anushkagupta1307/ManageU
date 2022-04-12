package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class UsageStatsActivity extends AppCompatActivity {

    Context context=this;
    TextView instagramtime,facebooktime, whatsapptime, total;
    float hr1, hr2, hr3, min1, min2, min3;
    TextView tips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_stats);

        instagramtime= findViewById(R.id.instagramtime);
        facebooktime=findViewById(R.id.facebooktime);
        whatsapptime=findViewById(R.id.whatsapptime);
        total=findViewById(R.id.totaltime);
        tips=findViewById(R.id.tips);
        tips.setVisibility(View.INVISIBLE);

        if (!checkUsageAllowedOrNot()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            if (checkUsageAllowedOrNot()) {
                List<UsageStats> usageStats = getUsageStatsList();

                for (int i = 0; i < usageStats.size(); i++) {
                    System.out.println(usageStats.get(i).getPackageName() + " " + usageStats.get(i).getTotalTimeInForeground());
                }
            } else {
                Toast.makeText(context, "Please provide access.", Toast.LENGTH_SHORT).show();
            }
        } else {
            List<UsageStats> usageStats = getUsageStatsList();

            boolean instaFlag=false, facebookFlag= false, whatsappFlag=false;

            for (int i = 0; i < usageStats.size(); i++) {

                if (usageStats.get(i).getPackageName().contains("com.instagram.android") && instaFlag==false){
                    final long milliseconds = usageStats.get(i).getTotalTimeInForeground();

                     hr1 = (int)TimeUnit.MILLISECONDS.toHours(milliseconds)
                            - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
                     min1 = (int)TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
                    System.out.println(usageStats.get(i).getPackageName() + " " + hr1 + " Hours " + min1 + " Minutes");
                    String time="";
                    if(hr1!=0){
                        time+=(int)hr1+" Hr ";
                    }
                    if(min1!=0){
                        time+=(int)min1+" Mins";
                    }
                    instagramtime.setText(time);
                    instaFlag=true;

                }
                if (usageStats.get(i).getPackageName().contains("com.facebook.katana") && facebookFlag==false){
                    final long milliseconds = usageStats.get(i).getTotalTimeInForeground();

                    hr2 = (int)TimeUnit.MILLISECONDS.toHours(milliseconds)
                            - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
                    min2 = (int)TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
                    System.out.println(usageStats.get(i).getPackageName() + " " + hr2 + " Hours " + min2 + " Minutes");
                    String time="";
                    if(hr2!=0){
                        time+=(int)hr2+" Hr ";
                    }
                    if(min2!=0){
                        time+=(int)min2+" Mins";
                    }
                    facebooktime.setText(time);
                    facebookFlag=true;

                }
                if (usageStats.get(i).getPackageName().contains("com.whatsapp") && whatsappFlag==false){
                    final long milliseconds = usageStats.get(i).getTotalTimeInForeground();

                    hr3 = (int)TimeUnit.MILLISECONDS.toHours(milliseconds)
                            - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
                    min3 = (int)TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
                    System.out.println(usageStats.get(i).getPackageName() + " " + hr3 + " Hours " + min3 + " Minutes");
                    String time="";
                    if(hr3!=0){
                        time+=(int)hr3+" Hr ";
                    }
                    if(min3!=0){
                        time+=(int)min3+" Mins";
                    }
                    whatsapptime.setText(time);
                    whatsappFlag=true;

                }

                }

            String totalTime="";
            float totalhrs= hr1+hr2+hr3;
            float totalmins=min1+min2+min3;

            int extrahrs= (int)totalmins/60;
            totalhrs=totalhrs+extrahrs;
            totalmins=totalmins%60;

            if(totalhrs!=0){
                totalTime+=(int)totalhrs+" Hr ";
            }
            if(totalmins!=0){
                totalTime+=(int)totalmins+ " Mins";
            }
            total.setText(totalTime);

            if(totalhrs > 15){
                tips.setVisibility(View.VISIBLE);
                tips.setText("Too Much Social Media Usage might affect your Productivity!");
            }

            }
        }



    public boolean checkUsageAllowedOrNot(){
        try{
            PackageManager packageManager=getPackageManager();
            ApplicationInfo appInfo=packageManager.getApplicationInfo(getPackageName(),0);
            AppOpsManager appOpsManager=(AppOpsManager) getSystemService(APP_OPS_SERVICE);
            int mode= appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, appInfo.uid, appInfo.packageName);
            return (mode==AppOpsManager.MODE_ALLOWED);
        }
        catch (Exception e){
            Toast.makeText(context, "error in stats.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }



    @Deprecated
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public List<UsageStats> getUsageStatsList() {
        UsageStatsManager usm = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -1);
        long startTime = calendar.getTimeInMillis();
        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST, startTime, endTime);
        return usageStatsList;
    }
}