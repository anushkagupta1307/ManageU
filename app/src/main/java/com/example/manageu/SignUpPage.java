package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manageu.Controller.RegistrationController;

public class SignUpPage extends AppCompatActivity {

    EditText name, email, age, role, password, confirmPassword;
    Button signup;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        name=findViewById(R.id.editTextTextPersonName12);
        email=findViewById(R.id.editTextTextPersonName13);
        age=findViewById(R.id.editTextTextPersonName14);
        role=findViewById(R.id.editTextTextPersonName15);
        password=findViewById(R.id.editTextTextPersonName16);
        confirmPassword=findViewById(R.id.editTextTextPersonName17);
        signup=findViewById(R.id.button2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationController registrationController= new RegistrationController(context);
                boolean isregistrationSuccessful=registrationController.registerUser(name.getText().toString(), email.getText().toString(), age.getText().toString(), role.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
                if(isregistrationSuccessful){
                    Intent i= new Intent(context, MainActivity.class);
                    context.startActivity(i);

                }
                else{
                    Toast.makeText(context, "Error during registration", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}