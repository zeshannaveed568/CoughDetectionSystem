package com.example.pulmonarydisease;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PatientSignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textSignIn;
    private EditText edtFullName, edtEmail, edtPassword, edtCnic, edtPhone;

    private Button btnSignUp;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);

        edtFullName = findViewById(R.id.editName);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        edtCnic = findViewById(R.id.editCnic);
        edtPhone = findViewById(R.id.editPhone);


        // initialize SignUp Button
        btnSignUp = findViewById(R.id.signup_btn);
        btnSignUp.setOnClickListener(this);


        // Initializing com.example.pulmonarydiseases.com.example.doctorapp.Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        // Initializing the SignIn view
        textSignIn = findViewById(R.id.textSignIn);
        textSignIn.setOnClickListener(this);


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.signup_btn:
                registerUser();
                break;
        }

    }


    private void registerUser(){
        String fullName = edtFullName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String cnic = edtCnic.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        if (fullName.isEmpty()){
            edtFullName.setError("Name is required");
            edtFullName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Please enter a valid email");
            edtEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }
        if (password.length() < 8){
            edtPassword.setError("Minimum length of password should be 8");
            edtPassword.requestFocus();
            return;
        }
        if (cnic.isEmpty()){
            edtCnic.setError("CNIC is required");
            edtCnic.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            edtPhone.setError("Phone is required");
            edtPhone.requestFocus();
            return;
        }
        if (phone.length() != 11){
            edtPhone.setError("Enter Valid Phone Number");
            edtPhone.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    PatientInfoFirebase patient = new PatientInfoFirebase(fullName, email, password, cnic, phone);

                    if (task.isSuccessful()){
                        FirebaseDatabase.getInstance().getReference("PatientInfo")
                                .child(mAuth.getCurrentUser().getUid())
                                .setValue(patient).addOnCompleteListener(task1 -> {
                                    if(task1.isSuccessful()){
                                        Toast.makeText(PatientSignUpActivity.this, "Patient Registered Successfully", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(PatientSignUpActivity.this, "Error: " + task1.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });


                    }
                    else {
                        Toast.makeText(PatientSignUpActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }
}