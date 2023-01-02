package com.example.pulmonarydisease;

import android.content.Intent;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.pulmonarydisease.DoctorDash.DoctorDashActivity;
import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.PatientDash.PatientDashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {


    LottieAnimationView animationView;
    TextView btnSignUp, btnForgotPassword;
    EditText edtEmail;
    TextInputEditText edtPassword;

    Button btn_Login;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Initialize edtEmail and edtPassword
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);


        animationView = findViewById(R.id.lottie);
        animationView.animate().setDuration(900000000).start();

        // Initialize Login Button
        btn_Login = findViewById(R.id.login_btn);


        final LoadingDialogActivity loadingDialogActivity = new LoadingDialogActivity(LoginActivity.this);


        // Initialize SignUp Button
        btnSignUp = findViewById(R.id.txtSignUp);


        // Initialize Forgot Password Button
        btnForgotPassword = findViewById(R.id.forgotPassword);


        // Initialize com.example.doctorapp.Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, LoginSignUpActivity.class);
            startActivity(intent);
        });

        btnForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });


        btn_Login.setOnClickListener(v -> {


            loadingDialogActivity.startLoadingDialog();


            final String email = edtEmail.getText().toString().trim();
            final String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                edtEmail.setError("Email is required");

            }
            if (TextUtils.isEmpty(password)) {
                edtPassword.setError("Password is required");


            } else {

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            checkUserType(user);
                            loadingDialogActivity.dismissDialog();


//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//
//                            if (user.isEmailVerified()){
//
//
//                                String uId = task.getResult().getUser().getUid();
//
//                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//
//
//
//                                firebaseDatabase.getReference().child("users").child(uId).child("type").addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        int userType = snapshot.getValue(Integer.class);
//
//                                        if (userType == 1){
//
//                                            Intent intent = new Intent(LoginActivity.this, PatientDashActivity.class);
//                                            startActivity(intent);
//
//                                            finish();
////                                            loadingDialogActivity.dismissDialog();
//
//
//
//
//                                        }
//                                        if (userType == 0){
//                                            Intent intent = new Intent(LoginActivity.this, DoctorDashActivity.class);
//                                            startActivity(intent);
//                                            finish();
////                                            loadingDialogActivity.dismissDialog();
//
//
//
//                                        }
//
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });
//
//                            }
//                            else {
//                                user.sendEmailVerification();
//                                Toast.makeText(LoginActivity.this, "Check your Email to Verify your Email", Toast.LENGTH_SHORT).show();
//                            }


                        } else {
                            loadingDialogActivity.dismissDialog();
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

    }


    private void checkUserType(FirebaseUser user) {

        if (user != null) {
            try {
                String uId = user.getUid();

                if (user.isEmailVerified()) {


                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


                    firebaseDatabase.getReference().child("users").child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            DoctorInfoFirebase doctorInfoFirebase = snapshot.getValue(DoctorInfoFirebase.class);
                            PatientInfoFirebase patientInfoFirebase = snapshot.getValue(PatientInfoFirebase.class);

                            if (patientInfoFirebase.getType().equals("patient")) {
                                Intent intent = new Intent(LoginActivity.this, PatientDashActivity.class);
                                startActivity(intent);


                                finish();
                            }
                            if (doctorInfoFirebase.getType().equals("doctor")) {
                                Intent intent = new Intent(LoginActivity.this, DoctorDashActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    user.sendEmailVerification();
                    Toast.makeText(LoginActivity.this, "Check your Email to Verify your Email", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }


        }


    }
}

