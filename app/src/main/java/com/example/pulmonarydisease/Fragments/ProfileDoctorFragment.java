package com.example.pulmonarydisease.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.pulmonarydisease.EditActivity;
import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileDoctorFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseUser user;

    ImageButton btnEdit;

    CircleImageView profileImg;



    TextView txtUserFullName, txtUserEmail,txtUserPhone, txtUserCnic;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileDoctorFragment newInstance(String param1, String param2) {
        ProfileDoctorFragment fragment = new ProfileDoctorFragment();
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
                inflater.inflate(R.layout.fragment_profile_doctor, container, false);

        txtUserFullName = view.findViewById(R.id.txtDoctorFullName);
        txtUserEmail = view.findViewById(R.id.txtDoctorEmail);
        txtUserPhone = view.findViewById(R.id.txtDoctorPhone);
        txtUserCnic = view.findViewById(R.id.txtDoctorCnic);
        btnEdit = view.findViewById(R.id.btnEdit);
        profileImg = view.findViewById(R.id.docProfileImg);

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //pass data to  Edit Activity

                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra("fullName", txtUserFullName.getText().toString());
                intent.putExtra("email", txtUserEmail.getText().toString());
                intent.putExtra("phone", txtUserPhone.getText().toString());
                intent.putExtra("cnic", txtUserCnic.getText().toString());

                //send image
                profileImg.setDrawingCacheEnabled(true);
                profileImg.buildDrawingCache();
                Bitmap bitmap = profileImg.getDrawingCache();
                intent.putExtra("image", bitmap);

                startActivity(intent);

                startActivity(intent);

            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DoctorInfoFirebase doctorInfoFirebase = snapshot.getValue(DoctorInfoFirebase.class);

                txtUserFullName.setText(doctorInfoFirebase.getName());
                txtUserEmail.setText(doctorInfoFirebase.getEmail());
                txtUserPhone.setText(doctorInfoFirebase.getPhone());
                txtUserCnic.setText(doctorInfoFirebase.getCnic());


                //get image from firebase
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReference();
                StorageReference profileRef = storageReference.child("ProfileImage/").child(uid);
                profileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Glide.with(getContext())
                            .load(uri)
                            .transform(new CircleCrop())
                            .into(profileImg);
                });





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}