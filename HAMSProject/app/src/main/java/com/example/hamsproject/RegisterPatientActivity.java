package com.example.hamsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
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

public class RegisterPatientActivity extends AppCompatActivity {
    //create object of DatabaseReference class to access firebase's Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hams-project-f1259-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);
        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText number = findViewById(R.id.number);
        final EditText address = findViewById(R.id.address);
        final EditText health = findViewById(R.id.health);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowBtn = findViewById(R.id.loginNow);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from EditTexts into String variables
                final String nameTxt = name.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String numberTxt = number.getText().toString();
                final String addressTxt = address.getText().toString();
                final String healthTxt = health.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                // check if user fill all the fields before sending data to firebase
                if(nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || numberTxt.isEmpty() || addressTxt.isEmpty() || healthTxt.isEmpty()||conPasswordTxt.isEmpty()){
                    Toast.makeText(RegisterPatientActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                // check if passwords are matching with each other
                // if not matching with each other then show a toast message
                else if(!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(RegisterPatientActivity.this, "Passwords aren't matching", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("Patients").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if phone is not registered before
                            if(snapshot.hasChild(numberTxt)){
                                Toast.makeText(RegisterPatientActivity.this,"Phone is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // sending data to firebase Realtime Database
                                // we are using phone number as unique identity of every user
                                // so all the other details of user comes under phone number
                                databaseReference.child("Patients").child(numberTxt).child("name").setValue(nameTxt);
                                databaseReference.child("Patients").child(numberTxt).child("email").setValue(emailTxt);
                                databaseReference.child("Patients").child(numberTxt).child("number").setValue(numberTxt);
                                databaseReference.child("Patients").child(numberTxt).child("address").setValue(addressTxt);
                                databaseReference.child("Patients").child(numberTxt).child("health").setValue(healthTxt);
                                databaseReference.child("Patients").child(numberTxt).child("password").setValue(passwordTxt);

                                // show a success message then finish the activity
                                Toast.makeText(RegisterPatientActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}