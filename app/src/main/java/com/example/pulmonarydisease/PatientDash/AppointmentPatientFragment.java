package com.example.pulmonarydisease.PatientDash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Adapter.AppointmentPatientAdapter;
import com.example.pulmonarydisease.CreateAppointmentActivity;
import com.example.pulmonarydisease.Firebase.Appointment;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class AppointmentPatientFragment extends Fragment {

    RecyclerView appointmentRecyclerView;
    AppointmentPatientAdapter appointmentPatientAdapter;


    ImageButton btnCreateAppointment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentPatientFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AppointmentPatientFragment newInstance(String param1, String param2) {
        AppointmentPatientFragment fragment = new AppointmentPatientFragment();
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

        View view = inflater.inflate(R.layout.fragment_appointment, container,false);

        //Recycler View
        appointmentRecyclerView = view.findViewById(R.id.appointmentPatientList);
        appointmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Appointment> options =
                    new FirebaseRecyclerOptions.Builder<Appointment>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Appointments"), Appointment.class)
                            .build();



        appointmentPatientAdapter = new AppointmentPatientAdapter(options);
        appointmentRecyclerView.setAdapter(appointmentPatientAdapter);




        //Create Appointment Button

        btnCreateAppointment = view.findViewById(R.id.createAppointment);


        btnCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateAppointmentActivity.class);
                startActivity(intent);
            }
        });




        return view;




    }

    @Override
    public void onStart(){
        super.onStart();
        appointmentPatientAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        appointmentPatientAdapter.stopListening();
    }



}
