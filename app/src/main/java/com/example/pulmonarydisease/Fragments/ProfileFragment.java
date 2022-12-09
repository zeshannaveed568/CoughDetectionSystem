package com.example.pulmonarydisease.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pulmonarydisease.EditActivity;
import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseUser userPatient;

    ImageButton btnEdit;


    TextView txtPatientFullName, txtPatientEmail,txtPatientPhone, txtPatientCnic;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        View view = inflater.inflate(R.layout.fragment_profile, container,false);


        txtPatientFullName = view.findViewById(R.id.txtPatientFullName);
        txtPatientEmail = view.findViewById(R.id.txtPatientEmail);
        txtPatientPhone = view.findViewById(R.id.txtPatientPhone);
        txtPatientCnic = view.findViewById(R.id.txtPatientCnic);

        btnEdit = view.findViewById(R.id.btnEdit);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                startActivity(intent);
            }
        });



        userPatient = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userPatient.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PatientInfoFirebase patientInfoFirebase = snapshot.getValue(PatientInfoFirebase.class);

                txtPatientFullName.setText(patientInfoFirebase.getName());
                txtPatientEmail.setText(patientInfoFirebase.getEmail());
                txtPatientPhone.setText(patientInfoFirebase.getPhone());
                txtPatientCnic.setText(patientInfoFirebase.getCnic());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return view;
    }



    }
