package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DoctorSignUpActivity extends AppCompatActivity{


    TextView textSignIn;
    Button btn_SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        textSignIn = findViewById(R.id.txtSignIn);
        btn_SignUp = findViewById(R.id.btSignUp);


        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorSignUpActivity.this, DoctorDashActivity.class);
                startActivity(intent);
            }
        });

    }

}




