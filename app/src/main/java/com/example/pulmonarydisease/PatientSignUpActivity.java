package com.example.pulmonarydisease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PatientSignUpActivity extends AppCompatActivity  {


    private EditText edtFullName, edtEmail, edtPassword, edtCnic, edtPhone;


    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;

    TextView textSignIn;
    Button btn_SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);


        edtFullName = findViewById(R.id.editName);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        edtCnic = findViewById(R.id.editCnic);
        edtPhone = findViewById(R.id.editPhone);

        textSignIn = findViewById(R.id.textSignIn);

        btn_SignUp = findViewById(R.id.signup_btn);

        mAuth = FirebaseAuth.getInstance();


        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PatientSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  fullname = edtFullName.getText().toString().trim();
                final String  email = edtEmail.getText().toString().trim();
                final String  password = edtPassword.getText().toString().trim();
                final String  cnic = edtCnic.getText().toString().trim();
                final String  phone = edtPhone.getText().toString().trim();

                if (fullname.isEmpty()){
                    edtFullName.setError("Name is required");
                    edtFullName.requestFocus();
                }
                if (email.isEmpty()){
                    edtEmail.setError("Email is required");
                    edtEmail.requestFocus();
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtEmail.setError("Please enter a valid email");
                    edtEmail.requestFocus();
                }
                if (password.isEmpty()){
                    edtPassword.setError("Password is required");
                    edtPassword.requestFocus();
                }
                if (password.length() < 8){
                    edtPassword.setError("Minimum length of password should be 8");
                    edtPassword.requestFocus();
                }
                if (cnic.isEmpty()){
                    edtCnic.setError("CNIC is required");
                    edtCnic.requestFocus();
                }
                if (phone.isEmpty()){
                    edtPhone.setError("Phone is required");
                    edtPhone.requestFocus();
                }
                if (phone.length() != 12){
                    edtPhone.setError("Enter Valid Phone Number");
                    edtPhone.requestFocus();
                }

                else{
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                String error = task.getException().toString();
                                Toast.makeText(PatientSignUpActivity.this, "Error"+ error, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                        .child("users").child(currentUserId);

                                HashMap userInfo = new HashMap();
                                userInfo.put("id", currentUserId);
                                userInfo.put("name", fullname);
                                userInfo.put("email", email);
                                userInfo.put("password", password);
                                userInfo.put("phone", phone);
                                userInfo.put("cnic", cnic);
                                userInfo.put("ProfileImage", "");
                                userInfo.put("type", "patient");


                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(PatientSignUpActivity.this, "Patients Data Sent Sucessfully", Toast.LENGTH_SHORT).show();
                                        }

                                        else{
                                            Toast.makeText(PatientSignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }
                    });
                }

            }
        });


    }
}
