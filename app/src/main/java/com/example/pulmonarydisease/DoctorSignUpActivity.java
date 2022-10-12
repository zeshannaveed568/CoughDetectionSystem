package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;


public class DoctorSignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtFullName, edtEmail, edtPassword, edtCnic, edtPhone;


    private FirebaseAuth mAuth;

    TextView textSignIn;
    Button btn_SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        edtFullName = findViewById(R.id.editFullName);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        edtCnic = findViewById(R.id.cNic);
        edtPhone = findViewById(R.id.editPhone);

        textSignIn = findViewById(R.id.txtSignIn);
        textSignIn.setOnClickListener(this);


        btn_SignUp = findViewById(R.id.btSignUp);
        btn_SignUp.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btSignUp:
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
            edtFullName.setError("Full Name is required");
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
            edtPhone.setError("Phone Number is required");
            edtPhone.requestFocus();
            return;
        }

        if (phone.length() < 11){
            edtPhone.setError("Minimum length of phone number should be 11");
            edtPhone.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                   DoctorInfoFirebase doctorInfoFirebase = new DoctorInfoFirebase(fullName, email, password, cnic, phone);

                   if (task.isSuccessful()){
                       FirebaseDatabase.getInstance().getReference("DoctorInfoFirebase").child(mAuth.getCurrentUser().getUid())
                               .setValue(doctorInfoFirebase).addOnCompleteListener(task1 -> {
                           if (task1.isSuccessful()){
                               Toast.makeText(DoctorSignUpActivity.this, "Doctor has been registered successfully", Toast.LENGTH_LONG).show();
                           }else {
                               Toast.makeText(DoctorSignUpActivity.this, "Error: " + task1.getException().getMessage(), Toast.LENGTH_LONG).show();
                           }
                       });


    }


    });
    }

    }





