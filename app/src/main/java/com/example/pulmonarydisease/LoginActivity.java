package com.example.pulmonarydisease;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.pulmonarydisease.PatientDash.PatientDashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LottieAnimationView animationView;
    TextView btnSignUp, btnForgotPassword, btnLogin;
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
        btn_Login.setOnClickListener(this);

        // Initialize SignUp Button
        btnSignUp = findViewById(R.id.txtSignUp);
        btnSignUp.setOnClickListener(this);


        // Initialize Forgot Password Button
        btnForgotPassword = findViewById(R.id.forgotPassword);
        btnForgotPassword.setOnClickListener(this);

        // Initialize com.example.doctorapp.Firebase Auth
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_btn:
                patientLogin();
                break;
            case R.id.txtSignUp:
                startActivity(new Intent(this, DoctorPatientSignUp.class));
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }

    }


    private void patientLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
//        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            edtEmail.setError("Email is required");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Please enter a valid email");
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            edtPassword.setError("Minimum length of password should be 8");
            edtPassword.requestFocus();
            return;
        }



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser patient = FirebaseAuth.getInstance().getCurrentUser();

                    if (patient.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, PatientDashActivity.class));
                        finish();
                    }
                    else {
                        patient.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your Email to Verify your Email", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed! Check your Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}