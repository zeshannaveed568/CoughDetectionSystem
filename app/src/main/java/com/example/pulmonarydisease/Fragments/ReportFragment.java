package com.example.pulmonarydisease.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }


    Button btnReport;

    EditText etReport;

    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();



    String name, email, type, phone;

    public ReportFragment(String name, String email, String type, String phone) {

        this.name = name;
        this.email = email;
        this.type = type;
        this.phone = phone;

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
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
        View view =  inflater.inflate(R.layout.fragment_report, container, false);

        TextView userEmail = view.findViewById(R.id.reportEmail);

        userEmail.setText(email);


        btnReport = view.findViewById(R.id.btnReport);


        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Submit Report for Doctor

                etReport = view.findViewById(R.id.etReport);

                String report = etReport.getText().toString();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Reports");

                String key = databaseReference.push().getKey();

                databaseReference.child(key).child("Name").setValue(name);

                databaseReference.child(key).child("Email").setValue(email);

                databaseReference.child(key).child("Type").setValue(type);

                databaseReference.child(key).child("Phone").setValue(phone);

                databaseReference.child(key).child("Report").setValue(report);

                Toast.makeText(getActivity(), "Report Submitted", Toast.LENGTH_SHORT).show();

                etReport.setText("");



            }


        });




        return view;
    }


    public void onBackPress(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_doctor, new ProfilePatientFragment()).addToBackStack(null).commit();

    }

}