package com.example.pulmonarydisease.PatientDash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.pulmonarydisease.LoadingDialogActivity;
import com.example.pulmonarydisease.LoginActivity;
import com.example.pulmonarydisease.PatientInfoActivity;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;


public class PatientDashFragment extends Fragment {

    Button btnLogout, patientInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_patient_dash, container, false);

        // Initializing the Sign Out Button
        btnLogout = view.findViewById(R.id.btnPatientSignOut);




        // Initializing the Info Button
        patientInfo =view.findViewById(R.id.btnInfoPatient);


        // defining the sign out button

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        });


        // Initializing the Info Button

        patientInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PatientInfoActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }
}