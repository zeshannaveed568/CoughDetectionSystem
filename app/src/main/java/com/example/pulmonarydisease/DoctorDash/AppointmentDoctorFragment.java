package com.example.pulmonarydisease.DoctorDash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pulmonarydisease.Adapter.AppointmentDoctorAdapter;
import com.example.pulmonarydisease.Adapter.AppointmentPatientAdapter;
import com.example.pulmonarydisease.Firebase.Appointment;
import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentDoctorFragment extends Fragment {

    RecyclerView appointmentRecyclerView;
    AppointmentDoctorAdapter appointmentDoctorAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentDoctorFragment newInstance(String param1, String param2) {
        AppointmentDoctorFragment fragment = new AppointmentDoctorFragment();
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
        View view =
                inflater.inflate(R.layout.fragment_appointment_doctor, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();


        //get current user email
        String email = user.getEmail();


        appointmentRecyclerView = view.findViewById(R.id.appointmentListDoctor);
        appointmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        Query query = FirebaseDatabase.getInstance().getReference().child("Appointments").orderByChild("doctorEmail").equalTo(email);


        FirebaseRecyclerOptions<Appointment> Options =
                new FirebaseRecyclerOptions.Builder<Appointment>()
                        .setQuery(query, Appointment.class)
                        .build();


        appointmentDoctorAdapter = new AppointmentDoctorAdapter(Options);
        appointmentRecyclerView.setAdapter(appointmentDoctorAdapter);




        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        appointmentDoctorAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        appointmentDoctorAdapter.stopListening();
    }

}