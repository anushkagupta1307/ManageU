package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.manageu.Dao.AppDatabase;
import com.example.manageu.Dao.DbAccess;
import com.example.manageu.Dao.UserDao;
import com.example.manageu.Model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DbAccess(getApplicationContext()).execute();

    }
}