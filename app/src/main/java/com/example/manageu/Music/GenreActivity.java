package com.example.manageu.Music;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.manageu.AccountFragment;
import com.example.manageu.DisplayTasks;
import com.example.manageu.FocusTimer.TimerActivity;
import com.example.manageu.ProgressFragment;
import com.example.manageu.R;
import com.example.manageu.StatsFragment;
import com.example.manageu.TaskActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class GenreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;

    Context context=this;

    StudyFragment stdyFrag = new StudyFragment();
    Relax relax = new Relax();
    SleepFragment sleep = new SleepFragment();
    ProgressFragment progressFragment = new ProgressFragment();
    StatsFragment statsFragment = new StatsFragment();
    AccountFragment accountFragment = new AccountFragment(context);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        TextView mus = findViewById(R.id.mus);
        mus.setText("");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Study:
                        mus.setText("Study");
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_genre, stdyFrag).commit();
                        return true;

                    case R.id.Peace:
                        mus.setText("Peace");
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_genre, sleep).commit();
                        return true;
                    case R.id.relax:
                        mus.setText("Relax");
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_genre, relax).commit();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.Study);
        drawerLayout = findViewById(R.id.my_drawer_layout_music);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.i1:
                Intent i1 = new Intent(GenreActivity.this, TaskActivity.class);
                i1.putExtra("tab",1);
                setResult(RESULT_OK,i1);
                Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i2:
                Intent i2 = new Intent(GenreActivity.this, TaskActivity.class);
                i2.putExtra("tab",2);
                setResult(RESULT_OK,i2);
                Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i3:
                Intent i3 = new Intent(GenreActivity.this, TaskActivity.class);
                i3.putExtra("tab",3);
                setResult(RESULT_OK,i3);
                Toast.makeText(this, "Stats", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.i4:
                Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.i5:
                Toast.makeText(this, "Accounts", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(GenreActivity.this, TaskActivity.class);
                i4.putExtra("tab",4);
                setResult(RESULT_OK,i4);
                finish();
                break;
            case R.id.i6:
                Intent i5 = new Intent(GenreActivity.this, TaskActivity.class);
                i5.putExtra("tab",5);
                setResult(RESULT_OK,i5);
                finish();
                break;
            case R.id.i7:
                Intent i6 = new Intent(GenreActivity.this, TaskActivity.class);
                i6.putExtra("tab",6);
                setResult(RESULT_OK,i6);
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
}