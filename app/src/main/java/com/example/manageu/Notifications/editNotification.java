package com.example.manageu.Notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.manageu.DisplayTasks;
import com.example.manageu.Music.GenreActivity;
import com.example.manageu.R;
import com.example.manageu.TaskActivity;
import com.google.android.material.navigation.NavigationView;

public class editNotification extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;

    EditText tit, msg, dt;
    Button submit;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notification);

        tit = findViewById(R.id.ediTit);
        msg = findViewById(R.id.ediMsg);
        dt = findViewById(R.id.ediTime);
        submit = findViewById(R.id.updateBt);
        NotificationClass noti = new NotificationClass(context);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String[] arrOfStr = dt.getText().toString().split(":");
                noti.manualNotification(tit.getText().toString(), msg.getText().toString(),arrOfStr[0],arrOfStr[1]);
                Toast.makeText(getApplicationContext(), "Reminder has been Scheduled", Toast.LENGTH_SHORT).show();
                tit.setText("");
                msg.setText("");
                dt.setText("");
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.i1:
                Intent i1 = new Intent(editNotification.this, TaskActivity.class);
                i1.putExtra("tab",1);
                setResult(RESULT_OK,i1);
                Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i2:
                Intent i2 = new Intent(editNotification.this, TaskActivity.class);
                i2.putExtra("tab",2);
                setResult(RESULT_OK,i2);
                Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i3:
                Intent i3 = new Intent(editNotification.this, TaskActivity.class);
                i3.putExtra("tab",3);
                setResult(RESULT_OK,i3);
                Toast.makeText(this, "Stats", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i4:
                Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show();
                Intent i8 = new Intent(editNotification.this, TaskActivity.class);
                i8.putExtra("tab", 7);
                setResult(RESULT_OK,i8);
                finish();
                break;
            case R.id.i5:
                Toast.makeText(this, "Accounts", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(editNotification.this, TaskActivity.class);
                i4.putExtra("tab",4);
                setResult(RESULT_OK,i4);
                finish();
                break;
            case R.id.i6:
                Intent i5 = new Intent(editNotification.this, TaskActivity.class);
                i5.putExtra("tab",5);
                setResult(RESULT_OK,i5);
                finish();
                break;
            case R.id.i7:
                Intent i6 = new Intent(editNotification.this, TaskActivity.class);
                i6.putExtra("tab",6);
                setResult(RESULT_OK,i6);
                finish();
                break;
            case R.id.i8:
                Intent i7 = new Intent(editNotification.this, TaskActivity.class);
                i7.putExtra("tab",8);
                setResult(RESULT_OK,i7);
                finish();
                break;
            case R.id.i9:
                Toast.makeText(this, "Create Reminder", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new DisplayTasks(context)).commit();
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}