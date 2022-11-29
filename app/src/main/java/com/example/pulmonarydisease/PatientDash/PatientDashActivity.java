package com.example.pulmonarydisease.PatientDash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pulmonarydisease.Fragments.AppointmentFragment;
import com.example.pulmonarydisease.Fragments.ChatFragment;
import com.example.pulmonarydisease.Fragments.ProfileFragment;
import com.example.pulmonarydisease.LoginActivity;
import com.example.pulmonarydisease.PatientInfoActivity;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class PatientDashActivity extends AppCompatActivity {


    ChipNavigationBar chipNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dash);




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