package com.example.manageu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TaskActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ProgressFragment progressFragment = new ProgressFragment();
    StatsFragment statsFragment = new StatsFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tasks:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new DisplayTasks()).commit();
                        return true;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,accountFragment).commit();
                        return true;

                    case R.id.progress:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,progressFragment).commit();
                        return true;

                    case R.id.stats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,statsFragment).commit();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.tasks);
    }
}