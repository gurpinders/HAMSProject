package com.example.hamsproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginAdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.passwordAdmin);
        final Button loginBtn = findViewById(R.id.loginAdminBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String adminUsername = "admin";
                final String adminPassword = "admin";

                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                if(adminUsername.isEmpty() || adminPassword.isEmpty()){
                    Toast.makeText(LoginAdminActivity.this, "Please enter your username and password",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(enteredUsername.equals(adminUsername) && enteredPassword.equals(adminPassword)){
                        Toast.makeText(LoginAdminActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginAdminActivity.this,LoggedInAdminActivity.class));
                        finish();

                    }
                    else{
                        Toast.makeText(LoginAdminActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}