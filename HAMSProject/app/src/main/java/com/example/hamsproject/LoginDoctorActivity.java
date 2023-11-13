package com.example.hamsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginDoctorActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hams-project-f1259-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);

        final EditText phone = findViewById(R.id.phoneDoctor);
        final EditText password = findViewById(R.id.passwordDoctor);
        final Button loginBtn = findViewById(R.id.loginDoctorBtn);
        final TextView registerNowBtn = findViewById(R.id.registerDoctor);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneText = phone.getText().toString();
                final String passwordText = password.getText().toString();
                if(phoneText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(LoginDoctorActivity.this, "Please enter your phone number and password",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Doctors").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if email is existing in firebase database
                            if(snapshot.hasChild(phoneText)){
                                // email is existing in firebase database
                                // now get password of user from firebase data and match it with user entered password
                                final String getPassword = snapshot.child(phoneText).child("password").getValue(String.class);
                                if(getPassword.equals(passwordText)){
                                    Toast.makeText(LoginDoctorActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                    // open Main
                                    startActivity(new Intent(LoginDoctorActivity.this,LoggedInDoctorActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginDoctorActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginDoctorActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open Patient Register activity
                startActivity(new Intent(LoginDoctorActivity.this,RegisterDoctorActivity.class));
            }
        });
    }
}