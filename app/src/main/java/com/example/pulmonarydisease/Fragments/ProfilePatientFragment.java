package com.example.pulmonarydisease.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pulmonarydisease.EditActivity;
import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.QuestionaireActivity;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilePatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePatientFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseUser user;

    FirebaseStorage storage;

    ImageButton btnEdit;


    Button btnQuestionnaire;

    TextView txtUserFullName, txtUserEmail,txtUserPhone, txtUserCnic;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilePatientFragment() {
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
    public static ProfilePatientFragment newInstance(String param1, String param2) {
        ProfilePatientFragment fragment = new ProfilePatientFragment();
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


        txtUserFullName = view.findViewById(R.id.txtPatientFullName);
        txtUserEmail = view.findViewById(R.id.txtPatientEmail);
        txtUserPhone = view.findViewById(R.id.txtPatientPhone);
        txtUserCnic = view.findViewById(R.id.txtPatientCnic);


        btnQuestionnaire = view.findViewById(R.id.btnQuestionnaire);


        btnQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionaireActivity.class);
                startActivity(intent);
            }
        });





        btnEdit = view.findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass data to EditActivity

                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra("fullName", txtUserFullName.getText().toString());
                intent.putExtra("email", txtUserEmail.getText().toString());
                intent.putExtra("phone", txtUserPhone.getText().toString());
                intent.putExtra("cnic", txtUserCnic.getText().toString());
                startActivity(intent);



            }
        });



        user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PatientInfoFirebase patientInfoFirebase = snapshot.getValue(PatientInfoFirebase.class);

                txtUserFullName.setText(patientInfoFirebase.getName());
                txtUserEmail.setText(patientInfoFirebase.getEmail());
                txtUserPhone.setText(patientInfoFirebase.getPhone());
                txtUserCnic.setText(patientInfoFirebase.getCnic());






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return view;
    }



    }
