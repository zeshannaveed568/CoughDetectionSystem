package com.example.pulmonarydisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pulmonarydisease.Adapter.PatientAdapter;
import com.example.pulmonarydisease.Adapter.ViewReportAdapter;
import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ViewReportActivity extends AppCompatActivity {

    RecyclerView viewReportRecyclerView;
    ViewReportAdapter viewReportAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);


        viewReportRecyclerView = findViewById(R.id.listViewReportRecycler);
        viewReportRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Query query =
                FirebaseDatabase.getInstance().getReference().child("users").orderByChild("type").equalTo("patient");

        //fetch data from firebase to recycler view

        FirebaseRecyclerOptions<PatientInfoFirebase> Options =
                new FirebaseRecyclerOptions.Builder<PatientInfoFirebase>()
                        .setQuery(query, new SnapshotParser<PatientInfoFirebase>() {
                            @NonNull
                            @Override
                            public PatientInfoFirebase parseSnapshot(@NonNull DataSnapshot snapshot) {
                                PatientInfoFirebase patientInfoFirebase = snapshot.getValue(PatientInfoFirebase.class);
                                return patientInfoFirebase;

                            }
                        })
                        .build();


        viewReportAdapter = new ViewReportAdapter(Options);
        viewReportRecyclerView.setAdapter(viewReportAdapter);

    }

    @Override
    public void onStart(){
        super.onStart();
        viewReportAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        viewReportAdapter.stopListening();
    }

}