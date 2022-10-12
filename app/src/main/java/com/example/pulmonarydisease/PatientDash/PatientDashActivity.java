package com.example.pulmonarydisease.PatientDash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pulmonarydisease.LoginActivity;
import com.example.pulmonarydisease.PatientInfoActivity;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class PatientDashActivity extends AppCompatActivity {



    Button btnLogout, patientInfo;

    ChipNavigationBar chipNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dash);

        // Initializing the Sign Out Button
        btnLogout = findViewById(R.id.btnSignOut);


        // Initializing the Info Button
        patientInfo = findViewById(R.id.btnInfoPatient);


        // defining the signout button

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PatientDashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Initializing the Info Button

        patientInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientDashActivity.this, PatientInfoActivity.class);
                startActivity(intent);
            }
        });


        // Initializing the ChipNavigationBar

        chipNavigationBar = findViewById(R.id.bottom_navigation);

        chipNavigationBar.setItemSelected(R.id.bottom_nav_dashboard, true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PatientDashFragment()).commit();
        bottomMenu();

    }


    public void bottomMenu(){
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                Fragment fragment = null;

                switch (i){
                    case R.id.bottom_nav_dashboard:
                        fragment = new PatientDashFragment();
                        break;
                    case R.id.bottom_nav_doctors:
                        fragment = new DoctorFragment();
                        break;
                    case R.id.bottom_nav_profile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.bottom_nav_appointments:
                        fragment = new AppointmentFragment();
                        break;
                    case R.id.bottom_nav_chat:
                        fragment = new ChatFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }


}