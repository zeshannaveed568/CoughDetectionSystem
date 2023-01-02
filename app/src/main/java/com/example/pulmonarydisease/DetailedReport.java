package com.example.pulmonarydisease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pulmonarydisease.Adapter.PatientAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DetailedReport extends AppCompatActivity {

    ImageView btnBack;

    TextView patientName;

    String nameIntent;

    TextView txtAllergies, txtGeneticDisease, txtPhyAct, txtSleep, txtSurgery, txtWeight, txtCovid, txtSmoke, txtDiseases;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Questionnaire");
//    String QUId = databaseReference.push().getKey();
//    DatabaseReference dbReference = databaseReference.child(QUId);


    DatabaseReference patientReference = FirebaseDatabase.getInstance().getReference("users");
//    String uId = patientReference.getKey();

    DatabaseReference symptomsReference = FirebaseDatabase.getInstance().getReference("Symptoms");
//    String SUid = symptomsReference.getKey();


    FirebaseUser firebaseUser;

    PatientAdapter patientAdapter;


    ImageView imgBluishLipsTrue, imgBreathlessnessTrue, imgChangeWeightTrue, imgCoughTrue, imgCyanosisTrue, imgDepressionTrue, imgDiarrheaTrue,
            imgDizzinessTrue, imgFeverTrue, imgHeartProblemTrue, imgHyperventilationTrue, imgLungFunctionTrue, imgNightSweatTrue, imgPhlegmTrue,
            imgRibTightnessTrue, imgRunningNoseTrue, imgSoreThroatTrue, imgSwellingTrue, imgVomitingTrue, imgWheezingTrue, imgWhoopingCoughTrue,
            imgBluishLipsFalse, imgBreathlessnessFalse, imgChangeWeightFalse, imgCoughFalse, imgCyanosisFalse, imgDepressionFalse, imgDiarrheaFalse,
            imgDizzinessFalse, imgFeverFalse, imgHeartProblemFalse, imgHyperventilationFalse, imgLungFunctionFalse, imgNightSweatFalse, imgPhlegmFalse,
            imgRibTightnessFalse, imgRunningNoseFalse, imgSoreThroatFalse, imgSwellingFalse, imgVomitingFalse, imgWheezingFalse, imgWhoopingCoughFalse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report);

        //medical history hooks
        txtAllergies = findViewById(R.id.tvAllergies);
        txtGeneticDisease = findViewById(R.id.tvGeneticDisease);
        txtPhyAct = findViewById(R.id.tvPhysicalActivities);
        txtSleep = findViewById(R.id.tvSleepPattern);
        txtSurgery = findViewById(R.id.tvSurgeries);
        txtWeight = findViewById(R.id.tvWeight);
        txtCovid = findViewById(R.id.tvCovid);
        txtSmoke = findViewById(R.id.tvSmoke);
        txtDiseases = findViewById(R.id.tvDiseases);


        //get uid for patient


        //back button hook
        btnBack = findViewById(R.id.imgBack);

//        get respective patient name
        patientName = findViewById(R.id.txtDetailedReportUserName);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailedReport.this, ViewReportActivity.class);
            startActivity(intent);
        });


        getName();
        getQuestionnaire();
        getSymptoms();




    }

    private void getQuestionnaire() {
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
//                    String uid = snapshot.getKey();
//
//                    patientReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                            String pId = snapshot.getKey();
//                            for (DataSnapshot snapshot: datasnapshot.getChildren()){
//                                if (pId.equals(uid)){
//                                    String allergies = snapshot.child("editAllergies").getValue().toString();
//                                    String geneticDisease = snapshot.child("editGeneticDisease").getValue().toString();
//                                    String physicalActivities = snapshot.child("editPhysicalActivities").getValue().toString();
//                                    String sleepPattern = snapshot.child("editSleep").getValue().toString();
//                                    String surgeries = snapshot.child("editSurgery").getValue().toString();
//                                    String weight = snapshot.child("editWeight").getValue().toString();
//                                    String covid = snapshot.child("spinCovid").getValue().toString();
//                                    String smoke = snapshot.child("spinSmoke").getValue().toString();
//                                    String diseases = snapshot.child("spinDiseases").getValue().toString();
//
//
//
//                                    txtAllergies.setText(allergies);
//                                    txtGeneticDisease.setText(geneticDisease);
//                                    txtPhyAct.setText(physicalActivities);
//                                    txtSleep.setText(sleepPattern);
//                                    txtSurgery.setText(surgeries);
//                                    txtWeight.setText(weight);
//                                    txtCovid.setText(covid);
//                                    txtSmoke.setText(smoke);
//                                    txtDiseases.setText(diseases);
//
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void getSymptoms() {
        symptomsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {

                                    String bluishLips = snapshot.child("BluishLips").getValue().toString();
                                    String breathlessness = snapshot.child("Breathlessness").getValue().toString();
                                    String changeWeight = snapshot.child("ChangeWeight").getValue().toString();

                                    if (bluishLips.equals("True")) {
                                        imgBluishLipsTrue.setVisibility(View.VISIBLE);
                                    } else {
                                        imgBluishLipsFalse.setVisibility(View.VISIBLE);
                                    }

                                    if (breathlessness.equals("True")) {
                                        imgBreathlessnessTrue.setVisibility(View.VISIBLE);
                                    } else {
                                        imgBreathlessnessFalse.setVisibility(View.VISIBLE);
                                    }
                                    if (changeWeight.equals("True")) {
                                        imgChangeWeightTrue.setVisibility(View.VISIBLE);
                                    } else {
                                        imgChangeWeightFalse.setVisibility(View.VISIBLE);
                                    }

                                }
                            }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

    private void getName() {
        nameIntent = getIntent().getStringExtra("name");
        patientName.setText(nameIntent);
    }


}





