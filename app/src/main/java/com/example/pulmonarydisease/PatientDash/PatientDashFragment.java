package com.example.pulmonarydisease.PatientDash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.pulmonarydisease.SymptomsActivity;
import com.example.pulmonarydisease.LoginActivity;
import com.example.pulmonarydisease.PatientInfoActivity;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;


public class PatientDashFragment extends Fragment {

    Button btnLogout, patientInfo, btnSymptoms;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientDashFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PatientDashFragment newInstance(String param1, String param2) {
        PatientDashFragment fragment = new PatientDashFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_patient_dash, container, false);

        // Initializing the Sign Out Button
        btnLogout = view.findViewById(R.id.btnPatientSignOut);


        // Initializing the Info Button
        patientInfo =view.findViewById(R.id.btnInfoPatient);

        // Initializing the Symptoms Button
        btnSymptoms = view.findViewById(R.id.btnAddSymptoms);

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


//         Initializing the Symptoms Button

        btnSymptoms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addSymptoms();

            }
        });





        return view;

    }

    private void addSymptoms() {

        Intent intent = new Intent(getActivity(), SymptomsActivity.class);
        startActivity(intent);

    }


}