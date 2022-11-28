package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pulmonarydisease.R;

public class PatientInfoActivity extends AppCompatActivity {

    Button btnYoutube;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        btnYoutube = findViewById(R.id.btnYoutube);

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.youtube.com/watch?v=Hc280bHGTpA");
            }
        });

    }

    private void gotoUrl(String inURL){
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

        startActivity( browse );

    }
}