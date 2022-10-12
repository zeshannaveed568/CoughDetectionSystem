package com.example.pulmonarydisease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class LoginSignUpActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        btnLogin = findViewById(R.id.login_btn);
        btnSignUp = findViewById(R.id.signup_btn);

        lottieAnimationView = findViewById(R.id.lottie4);
        lottieAnimationView.animate().setDuration(900000000).start();


        //onClickListener for login button

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        //onClickListener for signup button

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSignUpActivity.this, DoctorPatientSignUp.class);
                startActivity(intent);
            }
        });



    }
}