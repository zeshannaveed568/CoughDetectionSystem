package com.example.pulmonarydisease.PatientDash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pulmonarydisease.Fragments.ProfilePatientFragment;
import com.example.pulmonarydisease.R;
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
                        fragment = new ProfilePatientFragment();
                        break;
                    case R.id.bottom_nav_appointments:
                        fragment = new AppointmentPatientFragment();
                        break;


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }


}