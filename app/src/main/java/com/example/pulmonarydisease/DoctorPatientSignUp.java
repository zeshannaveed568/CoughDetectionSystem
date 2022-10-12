package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorPatientSignUp extends AppCompatActivity {


    Button SignUpPatient, SignUpDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_sign_up);

        SignUpPatient = findViewById(R.id.btnPatientSignUp);
        SignUpDoctor = findViewById(R.id.btnDoctorSignUp);


        SignUpDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorPatientSignUp.this, DoctorSignUpActivity.class);
                startActivity(intent);
            }
        });






        SignUpPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorPatientSignUp.this, PatientSignUpActivity.class);
                startActivity(intent);

            }
        });





    }
}