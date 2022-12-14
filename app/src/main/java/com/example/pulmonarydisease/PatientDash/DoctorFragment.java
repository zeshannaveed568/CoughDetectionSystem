package com.example.pulmonarydisease.PatientDash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pulmonarydisease.Adapter.DoctorAdapter;
import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class DoctorFragment extends Fragment {


    RecyclerView doctorRecyclerView;
    DoctorAdapter doctorAdapter;

    DoctorInfoFirebase doctorInfoFirebase;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance(String param1, String param2) {
        DoctorFragment fragment = new DoctorFragment();
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

        View view = inflater.inflate(R.layout.fragment_doctor, container,false);


//        updateRecyclerView();

        doctorRecyclerView = view.findViewById(R.id.listDoctorRecycler);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Query query =
                firebaseDatabase.getReference().child("users").orderByChild("type").equalTo("doctor");

        FirebaseRecyclerOptions<DoctorInfoFirebase> options =
                new FirebaseRecyclerOptions.Builder<DoctorInfoFirebase>()
                        .setQuery(query, new SnapshotParser<DoctorInfoFirebase>() {
                            @NonNull
                            @Override
                            public DoctorInfoFirebase parseSnapshot(@NonNull DataSnapshot snapshot) {
                                DoctorInfoFirebase doctorInfoFirebase = snapshot.getValue(DoctorInfoFirebase.class);
                                return doctorInfoFirebase;
                            }
                            }).build();




//
//        FirebaseRecyclerOptions<DoctorInfoFirebase> Options =
//                new FirebaseRecyclerOptions.Builder<DoctorInfoFirebase>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"),
//                                DoctorInfoFirebase.class).build();


        doctorAdapter = new DoctorAdapter(options);
        doctorRecyclerView.setAdapter(doctorAdapter);

        return view;
    }



    @Override
    public void onStart(){
        super.onStart();
        doctorAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        doctorAdapter.stopListening();
    }


}