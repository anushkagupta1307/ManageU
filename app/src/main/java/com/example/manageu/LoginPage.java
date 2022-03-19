package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manageu.Controller.LoginController;

public class LoginPage extends AppCompatActivity {

    EditText email, password;
    Button login;
    Context context=this;
    public static String loggedInUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email= (EditText) findViewById(R.id.editTextTextPersonName);
        password= (EditText) findViewById(R.id.editTextTextPersonName2);
        login=findViewById(R.id.button2);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginController loginController=new LoginController(context);
                boolean isLoginSuccessful= loginController.checkUserLogin(email.getText().toString(),password.getText().toString());
                loggedInUserEmail=email.getText().toString();
                if(isLoginSuccessful){
                    //Intent i= new Intent(context, CalendarActivity.class);
                    Intent i= new Intent(context, TaskActivity.class);
                    context.startActivity(i);
                }
                else{
                    Toast.makeText(context, "Incorrect email or password.", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}