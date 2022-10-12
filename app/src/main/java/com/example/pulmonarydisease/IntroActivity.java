package com.example.pulmonarydisease;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;


public class IntroActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    TextView textView5;
    ImageView imageView;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        textView5 = findViewById(R.id.textView5);
        textView5.animate().translationY(-1400).setDuration(1000).setStartDelay(3000);

        imageView = findViewById(R.id.imageView);
        imageView.animate().translationY(-2000).setDuration(1000).setStartDelay(3000);

        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(3000) ;



        //new Handler

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, LoginSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);





    }





}