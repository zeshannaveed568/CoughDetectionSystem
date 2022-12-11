package com.example.pulmonarydisease.DoctorDash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pulmonarydisease.Adapter.PatientAdapter;
import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class PatientFragment extends Fragment {


    RecyclerView patientRecyclerView;
    PatientAdapter patientAdapter;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PatientFragment newInstance(String param1, String param2) {
        PatientFragment fragment = new PatientFragment();
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

        View view = inflater.inflate(R.layout.fragment_patient, container,false);

        patientRecyclerView = view.findViewById(R.id.listPatientRecycler);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //fetch patient from collection user type = patient from firebase


//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren());
//                String type = snapshot.child("type").getValue().toString();
//
//                if (type.equals("patient")){
//                    FirebaseRecyclerOptions<PatientInfoFirebase> options =
//                            new FirebaseRecyclerOptions.Builder<PatientInfoFirebase>()
//                                    .setQuery(FirebaseDatabase.getInstance().getReference(), PatientInfoFirebase.class)
//                                    .build();
//
//                    patientAdapter = new PatientAdapter(options);
//                    patientRecyclerView.setAdapter(patientAdapter);
//                }
//
//
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        FirebaseRecyclerOptions<PatientInfoFirebase> Options =
                new FirebaseRecyclerOptions.Builder<PatientInfoFirebase>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), PatientInfoFirebase.class)
                        .build();



        patientAdapter = new PatientAdapter(Options);
        patientRecyclerView.setAdapter(patientAdapter);

        return view;

    }




    @Override
    public void onStart(){
        super.onStart();
        patientAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        patientAdapter.stopListening();
    }



}