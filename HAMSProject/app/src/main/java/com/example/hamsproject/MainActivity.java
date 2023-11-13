package com.example.hamsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonP;
    Button buttonD;
    Button buttonA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonP = (Button) findViewById(R.id.buttonPatient);
        buttonD = (Button) findViewById(R.id.buttonDoctor);
        buttonA = (Button) findViewById(R.id.buttonAdmin);
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPatientActivity();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginDoctorActivity();
            }
        });
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginAdminActivity();
            }
        });
    }
    public void openLoginPatientActivity(){
        Intent intent = new Intent(this,LoginPatientActivity.class);
        startActivity(intent);
    }
    public void openLoginDoctorActivity(){
        Intent intent = new Intent(this,LoginDoctorActivity.class);
        startActivity(intent);
    }
    public void openLoginAdminActivity(){
        Intent intent = new Intent(this,LoginAdminActivity.class);
        startActivity(intent);
    }
}