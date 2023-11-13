package com.example.hamsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterDoctorActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hams-project-f1259-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);
        final EditText name = findViewById(R.id.nameDoctor);
        final EditText email = findViewById(R.id.emailDoctor);
        final EditText password = findViewById(R.id.passwordDoctor);
        final EditText number = findViewById(R.id.numberDoctor);
        final EditText address = findViewById(R.id.addressDoctor);
        final EditText employee = findViewById(R.id.employee);
        final EditText specialities = findViewById(R.id.specialities);
        final EditText conPassword = findViewById(R.id.conPasswordDoctor);

        final Button registerBtn = findViewById(R.id.registerBtnDoctor);
        final TextView loginNowBtn = findViewById(R.id.loginNowDoctor);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data from EditTexts into String variables
                final String nameTxt = name.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String numberTxt = number.getText().toString();
                final String addressTxt = address.getText().toString();
                final String employeeTxt = employee.getText().toString();
                final String specialitiesTxt = specialities.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                // check if user fill all the fields before sending data to firebase
                if(nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || numberTxt.isEmpty() || addressTxt.isEmpty() || employeeTxt.isEmpty() || specialitiesTxt.isEmpty()||conPasswordTxt.isEmpty()){
                    Toast.makeText(RegisterDoctorActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                // check if passwords are matching with each other
                // if not matching with each other then show a toast message
                else if(!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(RegisterDoctorActivity.this, "Passwords aren't matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Doctors").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if phone is not registered before
                            if(snapshot.hasChild(numberTxt)){
                                Toast.makeText(RegisterDoctorActivity.this,"Phone is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                // sending data to firebase Realtime Database
                                // we are using phone number as unique identity of every user
                                // so all the other details of user comes under phone number
                                databaseReference.child("Doctors").child(numberTxt).child("name").setValue(nameTxt);
                                databaseReference.child("Doctors").child(numberTxt).child("email").setValue(emailTxt);
                                databaseReference.child("Doctors").child(numberTxt).child("number").setValue(numberTxt);
                                databaseReference.child("Doctors").child(numberTxt).child("address").setValue(addressTxt);
                                databaseReference.child("Doctors").child(numberTxt).child("employee number").setValue(employeeTxt);
                                databaseReference.child("Doctors").child(numberTxt).child("specialities").setValue(employeeTxt);
                                databaseReference.child("Doctors").child(numberTxt).child("password").setValue(passwordTxt);

                                // show a success message then finish the activity
                                Toast.makeText(RegisterDoctorActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
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