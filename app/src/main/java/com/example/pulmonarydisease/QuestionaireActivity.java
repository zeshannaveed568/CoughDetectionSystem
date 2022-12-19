package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pulmonarydisease.Firebase.Questionnaire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionaireActivity extends AppCompatActivity {

    Spinner spinDiseases, spinSmoke, spinIndustrial, spinCovid;

    EditText editWeight, editPhysicalActivities, editSurgery, editGeneticDisease, editAllergies, editSleep;

    Button btnSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);

        //Hook for Buttons
        btnSubmit = findViewById(R.id.btnSubmitQuestionnaire);

        //Hooks for Edit Text
        editWeight = findViewById(R.id.edtWeight);
        editPhysicalActivities = findViewById(R.id.edtPhysicalActivities);
        editSurgery = findViewById(R.id.edtAnySurgery);
        editGeneticDisease = findViewById(R.id.edtGeneticDisease);
        editAllergies = findViewById(R.id.edtAnyAllergies);
        editSleep = findViewById(R.id.edtSleepingHours);



        // Hooks for spinners
        spinDiseases = findViewById(R.id.spinnerDisease);
        spinSmoke = findViewById(R.id.spinnerSmoke);
        spinIndustrial = findViewById(R.id.spinnerIndustrialArea);
        spinCovid = findViewById(R.id.spinnerCovid);

        //Initialize the array adapter for spinners

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.disease, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDiseases.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.smoke, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSmoke.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.industrial, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIndustrial.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.covid, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCovid.setAdapter(adapter4);


        //Initialize the Button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get all the values
                String SpinDiseases = spinDiseases.getSelectedItem().toString();
                String SpinSmoke = spinSmoke.getSelectedItem().toString();
                String SpinIndustrial = spinIndustrial.getSelectedItem().toString();
                String SpinCovid = spinCovid.getSelectedItem().toString();
                String EditWeight = editWeight.getText().toString();
                String EditPhysicalActivities = editPhysicalActivities.getText().toString();
                String EditSurgery = editSurgery.getText().toString();
                String EditGeneticDisease = editGeneticDisease.getText().toString();
                String EditAllergies = editAllergies.getText().toString();
                String EditSleep = editSleep.getText().toString();


                if (SpinDiseases.isEmpty()){
                    spinDiseases.setPrompt("Please select an option");
                    spinDiseases.requestFocus();
                }
                if (SpinSmoke.isEmpty()){
                    spinSmoke.setPrompt("Please select an option");
                    spinSmoke.requestFocus();
                }
                if (SpinIndustrial.isEmpty()){
                    spinIndustrial.setPrompt("Please select an option");
                    spinIndustrial.requestFocus();
                }
                if (SpinCovid.isEmpty()){
                    spinCovid.setPrompt("Please select an option");
                    spinCovid.requestFocus();
                }
                if (EditWeight.isEmpty()){
                    editWeight.setError("Please enter your weight");
                    editWeight.requestFocus();
                }
                if (EditPhysicalActivities.isEmpty()){
                    editPhysicalActivities.setError("Please enter your physical activities");
                    editPhysicalActivities.requestFocus();
                }
                if (EditSurgery.isEmpty()){
                    editSurgery.setError("Please enter your surgery");
                    editSurgery.requestFocus();
                }
                if (EditGeneticDisease.isEmpty()){
                    editGeneticDisease.setError("Please enter your genetic disease");
                    editGeneticDisease.requestFocus();
                }
                if (EditAllergies.isEmpty()){
                    editAllergies.setError("Please enter your allergies");
                    editAllergies.requestFocus();
                }
                if (EditSleep.isEmpty()){
                    editSleep.setError("Please enter your sleeping hours");
                    editSleep.requestFocus();
                }

                //check hours of sleep
                if (Integer.parseInt(EditSleep) > 24 && Integer.parseInt(EditSleep) < 0){
                    Toast.makeText(QuestionaireActivity.this, "Sleep Value cannot be greater than 24", Toast.LENGTH_SHORT).show();
                }


//                if (Integer.parseInt(EditSleep) >= 24 && Integer.parseInt(EditSleep) <= 0){
//                    editSleep.setError("Please enter a valid number");
//                    editSleep.requestFocus();
//                }
//
//                if (Integer.parseInt(EditWeight) >= 24 && Integer.parseInt(EditWeight) <= 0){
//                    editSleep.setError("Please enter a valid number");
//                    editSleep.requestFocus();
//                }

                else {
                    Toast.makeText(QuestionaireActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();


                    //Create a new user
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();

                    //Create a new Questionnaire
                    Questionnaire questionnaire = new Questionnaire(SpinDiseases, SpinSmoke, SpinIndustrial, SpinCovid, EditWeight, EditPhysicalActivities, EditSurgery, EditGeneticDisease, EditAllergies, EditSleep);

                    //Save the Questionnaire to the database
                    FirebaseDatabase.getInstance().getReference("Questionnaire").child(uid).setValue(questionnaire);

                    //Show Success Message
                    Toast.makeText(QuestionaireActivity.this, "Questionnaire Submitted Successfully", Toast.LENGTH_SHORT).show();


                    //Go back to the main activity
                    finish();


                }
            }
        });


    }
}