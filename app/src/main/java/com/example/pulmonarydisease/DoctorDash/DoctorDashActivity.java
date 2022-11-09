package com.example.pulmonarydisease.DoctorDash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pulmonarydisease.DoctorInfoActivity;
import com.example.pulmonarydisease.LoginActivity;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DoctorDashActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBarDoc;
    Button btnLogout, doctorInfo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dash);

        btnLogout = findViewById(R.id.btnDocDashSignOut);
        doctorInfo = findViewById(R.id.btnInfoDoctor);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DoctorDashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        doctorInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDashActivity.this, DoctorInfoActivity.class);
                startActivity(intent);
            }
        });


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
                        fragment = new DoctorProfileFragment();
                        break;
                    case R.id.bottom_nav_appointments:
                        fragment = new AppointmentFragment();
                        break;
                    case R.id.bottom_nav_chat:
                        fragment = new ChatFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, fragment).commit();


            }
        });
    }

}