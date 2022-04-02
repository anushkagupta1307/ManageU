package com.example.manageu.Music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.manageu.DisplayTasks;
import com.example.manageu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class GenreActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    StudyFragment stdyFrag = new StudyFragment();
    SleepFragment sleepFrag = new SleepFragment();


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
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_genre, sleepFrag).commit();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.Study);
    }
}