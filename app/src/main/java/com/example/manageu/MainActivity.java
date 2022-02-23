package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manageu.Dao.RegistrationDbAccess;

public class MainActivity extends AppCompatActivity {

    Button login, signup;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login= findViewById(R.id.button);
        signup= findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(context, LoginPage.class);
                context.startActivity(loginIntent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent=new Intent(context, SignUpPage.class);
                context.startActivity(signupIntent);
            }
        });

    }
}