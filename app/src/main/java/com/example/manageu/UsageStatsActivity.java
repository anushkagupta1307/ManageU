package com.example.manageu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageu.FocusTimer.TimerActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class UsageStatsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;

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

            drawerLayout = findViewById(R.id.my_drawer_layout_music);
            toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

            NavigationView navigationView = findViewById(R.id.navView);
            navigationView.setNavigationItemSelectedListener(this);

            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.i1:
                Intent i1 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i1.putExtra("tab",1);
                setResult(RESULT_OK,i1);
//                Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i2:
                Intent i2 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i2.putExtra("tab",2);
                setResult(RESULT_OK,i2);
                Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i3:
                Intent i3 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i3.putExtra("tab",3);
                setResult(RESULT_OK,i3);
                Toast.makeText(this, "Stats", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i4:
                Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i4.putExtra("tab",7);
                setResult(RESULT_OK,i4);
                finish();
                break;
            case R.id.i5:
                Toast.makeText(this, "Accounts", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent i5 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i5.putExtra("tab",4);
                setResult(RESULT_OK,i5);
                finish();
                break;
            case R.id.i6:
                Toast.makeText(this, "Focus", Toast.LENGTH_SHORT).show();
                Intent i6 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i6.putExtra("tab",5);
                setResult(RESULT_OK,i6);
                finish();
                break;
            case R.id.i7:
                Toast.makeText(this, "Social Media Stats", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.i8:
                Intent i7 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i7.putExtra("tab",8);
                setResult(RESULT_OK,i7);
                finish();
                break;
            case R.id.i9:
                Toast.makeText(this, "Create Reminder", Toast.LENGTH_SHORT).show();
                Intent i8 = new Intent(UsageStatsActivity.this, TaskActivity.class);
                i8.putExtra("tab", 9);
                setResult(RESULT_OK,  i8);
                finish();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new DisplayTasks(context)).commit();
        }
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}