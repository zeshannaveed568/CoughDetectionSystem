package com.example.pulmonarydisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pulmonarydisease.PatientDash.PatientDashActivity;
import com.example.pulmonarydisease.PatientDash.PatientDashFragment;
import com.example.pulmonarydisease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SymptomsActivity extends AppCompatActivity {
    //Variable for submit button
    Button btnSymptoms;

    //Variables for radio buttons

    RadioButton rbFeverTrue, rbFeverFalse, rbCoughTrue, rbCoughFalse, rbBreathlessnessTrue,rbBreathlessnessFalse, rbSoreThroatTrue, rbSoreThroatFalse, rbRibTightnessTrue,rbRibTightnessFalse
            , rbWheezingTrue, getRbWheezingFalse, rbRunningNoseTrue, rbRunningNoseFalse, rbDiarrheaTrue, rbDiarrheaFalse, rbVomitingTrue, rbVomitingFalse, rbLungFunctionTrue, rbLungFunctionFalse,
            rbWhoopingCoughTrue,rbWhoopingCoughFalse, rbPhlegmTrue, rbPhlegmFalse,rbNauseaTrue,rbNauseaFalse, rbHeartProblemTrue,rbHeartProblemFalse, rbBluishLipsTrue,rbBluishLipsFalse, rbCygnosisTrue, rbCygnosisFalse,
            rbHyperventilationTrue,rbHyperventilationFalse,rbChangeWeightTrue, rbChangeWeightFalse, rbDizzinessTrue, rbDizzinessFalse, rbDepressionTrue, rbDepressionFalse, rbSwellingTrue,
            rbSwellingFalse,rbNightSweatTrue,getRbNightSweatFalse;

    FirebaseDatabase firebaseDatabase;

    FirebaseAuth mAuth;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);


        //Assigning variables to the submit button

        btnSymptoms = findViewById(R.id.btnSubmitSypmtoms);



        //Assigning variables to the radio buttons

//        1
        rbFeverTrue = findViewById(R.id.trueFever);
        rbFeverFalse = findViewById(R.id.falseFever);

//2
        rbCoughTrue = findViewById(R.id.trueCough);
        rbCoughFalse = findViewById(R.id.falseCough);

//3
        rbBreathlessnessTrue = findViewById(R.id.trueBreathless);
        rbBreathlessnessFalse = findViewById(R.id.falseBreathless);
//4
        rbSoreThroatTrue = findViewById(R.id.trueSoreThroat);
        rbSoreThroatFalse = findViewById(R.id.falseSoreThroat);
//5
        rbRibTightnessTrue = findViewById(R.id.trueRibTightness);
        rbRibTightnessFalse = findViewById(R.id.falseRibTightness);
//6
        rbWheezingTrue = findViewById(R.id.trueWheezing);
        getRbWheezingFalse = findViewById(R.id.falseWheezing);
//7
        rbRunningNoseTrue = findViewById(R.id.trueRunningNose);
        rbRunningNoseFalse = findViewById(R.id.falseRunningNose);
//8
        rbDiarrheaTrue = findViewById(R.id.trueDiahrrea);
        rbDiarrheaFalse = findViewById(R.id.falseDiahrrea);
//9
        rbVomitingTrue = findViewById(R.id.trueVomiting);
        rbVomitingFalse = findViewById(R.id.falseVomiting);
//10
        rbLungFunctionTrue = findViewById(R.id.trueLungFunction);
        rbLungFunctionFalse = findViewById(R.id.falseLungFunction);
//11
        rbWhoopingCoughTrue = findViewById(R.id.trueWhoopingCough);
        rbWhoopingCoughFalse = findViewById(R.id.falseWhoopingCough);
//12
        rbPhlegmTrue = findViewById(R.id.truePhlegm);
        rbPhlegmFalse = findViewById(R.id.falsePhlegm);
//13
        rbNauseaTrue = findViewById(R.id.trueNausea);
        rbNauseaFalse = findViewById(R.id.falseNausea);
//14
        rbHeartProblemTrue = findViewById(R.id.trueHeartProblem);
        rbHeartProblemFalse = findViewById(R.id.falseHeartProblem);
//15
        rbBluishLipsTrue = findViewById(R.id.trueBluishLips);
        rbBluishLipsFalse = findViewById(R.id.falseBluishLips);
//16
        rbCygnosisTrue = findViewById(R.id.trueCygnosis);
        rbCygnosisFalse = findViewById(R.id.falseCygnosis);
//17
        rbHyperventilationTrue = findViewById(R.id.trueHyperventilation);
        rbHyperventilationFalse = findViewById(R.id.falseHyperventilation);
//18
        rbChangeWeightTrue = findViewById(R.id.trueWeightChange);
        rbChangeWeightFalse = findViewById(R.id.falseWeightChange);
//19
        rbDizzinessTrue = findViewById(R.id.trueDizziness);
        rbDizzinessFalse = findViewById(R.id.falseDizziness);
//20
        rbDepressionTrue = findViewById(R.id.trueDepression);
        rbDepressionFalse = findViewById(R.id.falseDepression);
//21
        rbSwellingTrue = findViewById(R.id.trueSwelling);
        rbSwellingFalse = findViewById(R.id.falseSwelling);
//22
        rbNightSweatTrue = findViewById(R.id.trueNightSweats);
        getRbNightSweatFalse = findViewById(R.id.falseNightSweats);

        rbFeverTrue.setChecked(LoadFromSharedPreference( "Fever1"));
        rbFeverFalse.setChecked(LoadFromSharedPreference( "Fever2"));

        rbCoughTrue.setChecked(LoadFromSharedPreference( "Cough1"));
        rbCoughFalse.setChecked(LoadFromSharedPreference( "Cough2"));

        rbBreathlessnessTrue.setChecked(LoadFromSharedPreference( "rbBreathlessnessTrue"));
        rbBreathlessnessFalse.setChecked(LoadFromSharedPreference( "rbBreathlessnessFalse"));

        rbSoreThroatTrue.setChecked(LoadFromSharedPreference( "rbSoreThroatTrue"));
        rbSoreThroatFalse.setChecked(LoadFromSharedPreference( "rbSoreThroatFalse"));

        rbRibTightnessTrue.setChecked(LoadFromSharedPreference( "rbRibTightnessTrue"));
        rbRibTightnessFalse.setChecked(LoadFromSharedPreference( "rbRibTightnessFalse"));

        rbWheezingTrue.setChecked(LoadFromSharedPreference( "rbWheezingTrue"));
        getRbWheezingFalse.setChecked(LoadFromSharedPreference( "getRbWheezingFalse"));

        rbRunningNoseTrue.setChecked(LoadFromSharedPreference( "rbRunningNoseTrue"));
        rbRunningNoseFalse.setChecked(LoadFromSharedPreference( "rbRunningNoseFalse"));

        rbDiarrheaTrue.setChecked(LoadFromSharedPreference( "rbDiarrheaTrue"));
        rbDiarrheaFalse.setChecked(LoadFromSharedPreference( "rbDiarrheaFalse"));

        rbVomitingTrue.setChecked(LoadFromSharedPreference( "rbVomitingTrue"));
        rbVomitingFalse.setChecked(LoadFromSharedPreference( "rbVomitingFalse"));

        rbLungFunctionTrue.setChecked(LoadFromSharedPreference( "rbLungFunctionTrue"));
        rbLungFunctionFalse.setChecked(LoadFromSharedPreference( "rbLungFunctionFalse"));

        rbWhoopingCoughTrue.setChecked(LoadFromSharedPreference( "rbWhoopingCoughTrue"));
        rbWhoopingCoughFalse.setChecked(LoadFromSharedPreference( "rbWhoopingCoughFalse"));

        rbPhlegmTrue.setChecked(LoadFromSharedPreference( "rbPhlegmTrue"));
        rbPhlegmFalse.setChecked(LoadFromSharedPreference( "rbPhlegmFalse"));

        rbNauseaTrue.setChecked(LoadFromSharedPreference( "rbNauseaTrue"));
        rbNauseaFalse.setChecked(LoadFromSharedPreference( "rbNauseaFalse"));

        rbHeartProblemTrue.setChecked(LoadFromSharedPreference( "rbHeartProblemTrue"));
        rbHeartProblemFalse.setChecked(LoadFromSharedPreference( "rbHeartProblemFalse"));

        rbBluishLipsTrue.setChecked(LoadFromSharedPreference( "rbBluishLipsTrue"));
        rbBluishLipsFalse.setChecked(LoadFromSharedPreference( "rbBluishLipsFalse"));

        rbCygnosisTrue.setChecked(LoadFromSharedPreference( "rbCygnosisTrue"));
        rbCygnosisFalse.setChecked(LoadFromSharedPreference( "rbCygnosisFalse"));

        rbHyperventilationTrue.setChecked(LoadFromSharedPreference( "rbHyperventilationTrue"));
        rbHyperventilationFalse.setChecked(LoadFromSharedPreference( "rbHyperventilationFalse"));

        rbChangeWeightTrue.setChecked(LoadFromSharedPreference( "rbChangeWeightTrue"));
        rbChangeWeightFalse.setChecked(LoadFromSharedPreference( "rbChangeWeightFalse"));

        rbDizzinessTrue.setChecked(LoadFromSharedPreference( "rbDizzinessTrue"));
        rbDizzinessFalse.setChecked(LoadFromSharedPreference( "rbDizzinessFalse"));

        rbDepressionTrue.setChecked(LoadFromSharedPreference( "rbDepressionTrue"));
        rbDepressionFalse.setChecked(LoadFromSharedPreference( "rbDepressionFalse"));

        rbSwellingTrue.setChecked(LoadFromSharedPreference( "rbSwellingTrue"));
        rbSwellingFalse.setChecked(LoadFromSharedPreference( "rbSwellingFalse"));

        rbNightSweatTrue.setChecked(LoadFromSharedPreference( "rbNightSweatTrue"));
        getRbNightSweatFalse.setChecked(LoadFromSharedPreference( "getRbNightSweatFalse"));



        //save to shared preferences
        rbFeverTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("Fever1", isChecked);
            }
        });
        rbFeverFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("Fever2", isChecked);
            }
        });

        rbCoughTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("Cough1", isChecked);
            }
        });
        rbCoughFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("Cough2", isChecked);
            }
        });

        rbBreathlessnessTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbBreathlessnessTrue", isChecked);
            }
        });
        rbBreathlessnessFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbBreathlessnessFalse", isChecked);
            }
        });
        rbSoreThroatTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbSoreThroatTrue", isChecked);
            }
        });
        rbSoreThroatFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbSoreThroatFalse", isChecked);
            }
        });
        rbRibTightnessTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbRibTightnessTrue", isChecked);
            }
        });
        rbRibTightnessFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbRibTightnessFalse", isChecked);
            }
        });
        rbWheezingTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbWheezingTrue", isChecked);
            }
        });
        getRbWheezingFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("getRbWheezingFalse", isChecked);
            }
        });
        rbRunningNoseTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbRunningNoseTrue", isChecked);
            }
        });
        rbRunningNoseFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbRunningNoseFalse", isChecked);
            }
        });
        rbDiarrheaTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbDiarrheaTrue", isChecked);
            }
        });
        rbDiarrheaFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbDiarrheaFalse", isChecked);
            }
        });
        rbVomitingTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbVomitingTrue", isChecked);
            }
        });
        rbVomitingFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbVomitingFalse", isChecked);
            }
        });
        rbLungFunctionTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbLungFunctionTrue", isChecked);
            }
        });
        rbLungFunctionFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbLungFunctionFalse", isChecked);
            }
        });
        rbWhoopingCoughTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbWhoopingCoughTrue", isChecked);
            }
        });
        rbWhoopingCoughFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbWhoopingCoughFalse", isChecked);
            }
        });
        rbPhlegmTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbPhlegmTrue", isChecked);
            }
        });
        rbPhlegmFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbPhlegmFalse", isChecked);
            }
        });
        rbNauseaTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbNauseaTrue", isChecked);
            }
        });
        rbNauseaFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbNauseaFalse", isChecked);
            }
        });
        rbHeartProblemTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbHeartProblemTrue", isChecked);
            }
        });
        rbHeartProblemFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbHeartProblemFalse", isChecked);
            }
        });
        rbBluishLipsTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbBluishLipsTrue", isChecked);
            }
        });
        rbBluishLipsFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbBluishLipsFalse", isChecked);
            }
        });
        rbCygnosisTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbCygnosisTrue", isChecked);
            }
        });
        rbCygnosisFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbCygnosisFalse", isChecked);
            }
        });
        rbHyperventilationTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbHyperventilationTrue", isChecked);
            }
        });
        rbHyperventilationFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbHyperventilationFalse", isChecked);
            }
        });
        rbChangeWeightTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbChangeWeightTrue", isChecked);
            }
        });
        rbChangeWeightFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbChangeWeightFalse", isChecked);
            }
        });
        rbDizzinessTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbDizzinessTrue", isChecked);
            }
        });
        rbDizzinessFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbDizzinessFalse", isChecked);
            }
        });
        rbDepressionTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbDepressionTrue", isChecked);
            }
        });
        rbDepressionFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbDepressionFalse", isChecked);
            }
        });
        rbSwellingTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbSwellingTrue", isChecked);
            }
        });
        rbSwellingFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbSwellingFalse", isChecked);
            }
        });
        rbNightSweatTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("rbNightSweatTrue", isChecked);
            }
        });
        getRbNightSweatFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SaveIntoSharedPreference("getRbNightSweatFalse", isChecked);
            }
        });



        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Symptoms");

        //Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Submit button
        btnSymptoms = findViewById(R.id.btnSubmitSypmtoms);

        //save radio button state in shared preferences



        //Submit button click listener
        btnSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Put condition on Radio Buttons
//                1
                if (rbFeverTrue.isChecked()) {
                    //Save radio button state in shared preferences
                    //send data to firebase for current user
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Fever").setValue("True");
                } else if (rbFeverFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Fever").setValue("False");
//
                }
//2
                if (rbCoughTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Cough").setValue("True");
                } else if (rbCoughFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Cough").setValue("False");
                }
//   3
                if (rbBreathlessnessTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Breathlessness").setValue("True");
                } else if (rbBreathlessnessFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Breathlessness").setValue("False");
                }
//      4
                if (rbSoreThroatTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("SoreThroat").setValue("True");
                } else if (rbSoreThroatFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Sore Throat").setValue("False");
                }

//5
                if (rbRibTightnessTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("RibTightness").setValue("True");
                } else if (rbRibTightnessFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("RibTightness").setValue("False");
                }
//   6
                if (rbWheezingTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Wheezing").setValue("True");
                } else if (getRbWheezingFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Wheezing").setValue("False");
                }
//      7
                if (rbRunningNoseTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("RunningNose").setValue("True");
                } else if (rbRunningNoseFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("RunningNose").setValue("False");
                }
//         8
                if (rbDiarrheaTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Diarrhea").setValue("True");
                } else if (rbDiarrheaFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Diarrhea").setValue("False");
                }
//            9
                if (rbVomitingTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Vomiting").setValue("True");
                } else if (rbVomitingFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Vomiting").setValue("False");
                }
//               10
                if (rbLungFunctionTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("LungFunction").setValue("True");
                } else if (rbLungFunctionFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("LungFunction").setValue("False");
                }
// 11
                if (rbWhoopingCoughTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("WhoopingCough").setValue("True");
                } else if (rbWhoopingCoughFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Whooping Cough").setValue("False");
                }
//     12
                if (rbPhlegmTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Phlegm").setValue("True");
                } else if (rbPhlegmFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Phlegm").setValue("False");
                }

//         13
                if (rbNauseaTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Nausea").setValue("True");
                } else if (rbNauseaFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Nausea").setValue("False");
                }

                //14
                if (rbHeartProblemTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("HeartProblem").setValue("True");
                } else if (rbHeartProblemFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("HeartProblem").setValue("False");
                }
//15
                if (rbBluishLipsTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("BluishLips").setValue("True");
                } else if (rbBluishLipsFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("BluishLips").setValue("False");
                }
//    16
                if (rbCygnosisTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Cygnosis").setValue("True");
                } else if (rbCygnosisFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Cygnosis").setValue("False");
                }
//        17
                if (rbHyperventilationTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Hyperventilation").setValue("True");
                } else if (rbHyperventilationFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Hyperventilation").setValue("False");
                }
//            18
                if (rbChangeWeightTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("ChangeWeight").setValue("True");
                } else if (rbChangeWeightFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("ChangeWeight").setValue("False");
                }
//19
                if (rbDizzinessTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Dizziness").setValue("True");
                } else if (rbDizzinessFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Dizziness").setValue("False");
                }
//    20
                if (rbSwellingTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Swelling").setValue("True");
                } else if (rbSwellingFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Swelling").setValue("False");
                }
//        21
                if (rbNightSweatTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("NightSweat").setValue("True");
                } else if (getRbNightSweatFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("NightSweat").setValue("False");
                }

//            22
                if (rbDepressionTrue.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Depression").setValue("True");
                } else if (rbDepressionFalse.isChecked()) {
                    databaseReference.child(mAuth.getCurrentUser().getUid()).child("Depression").setValue("False");
                }


                //Print Success Message


                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();




                //Redirect to the previous Activity
                finish();




            }
        });

        }

        private void SaveIntoSharedPreference(String key, Boolean value) {
            SharedPreferences sharedPreferences = getSharedPreferences("SymptomsSp", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean(key, value);
            myEdit.apply();
        }

        private boolean LoadFromSharedPreference(String key) {
            SharedPreferences sharedPreferences = getSharedPreferences("SymptomsSp", MODE_PRIVATE);
            return sharedPreferences.getBoolean(key, false);
        }



    }

