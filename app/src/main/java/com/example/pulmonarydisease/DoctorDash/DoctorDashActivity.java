package com.example.pulmonarydisease.DoctorDash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.pulmonarydisease.Fragments.ProfileDoctorFragment;
import com.example.pulmonarydisease.Fragments.ProfilePatientFragment;
import com.example.pulmonarydisease.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DoctorDashActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBarDoc;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dash);








        chipNavigationBarDoc  = findViewById(R.id.bottom_navigation_doc);

        chipNavigationBarDoc.setItemSelected(R.id.bottom_nav_dashboard_doc, true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new DoctorDashFragment()).commit();
        bottomMenu();

    }

    public void bottomMenu(){
        chipNavigationBarDoc.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;

                switch (i){
                    case R.id.bottom_nav_dashboard_doc:
                        fragment = new DoctorDashFragment();
                        break;

                    case R.id.bottom_nav_patients:
                        fragment = new PatientFragment();
                        break;
                    case R.id.bottom_nav_profile:
                        fragment = new ProfileDoctorFragment();
                        break;
                    case R.id.bottom_nav_appointments:
                        fragment = new AppointmentDoctorFragment();
                        break;


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, fragment).commit();


            }
        });
    }

}