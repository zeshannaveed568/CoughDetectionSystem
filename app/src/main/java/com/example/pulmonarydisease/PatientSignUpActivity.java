package com.example.pulmonarydisease;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientSignUpActivity extends AppCompatActivity  {


    private EditText edtFullName, edtEmail, edtPassword, edtCnic, edtPhone;

    CircleImageView circleImageView;


    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;

    TextView textSignIn;
    Button btn_SignUp;

    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);



        edtFullName = findViewById(R.id.editName);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        edtCnic = findViewById(R.id.editCnic);
        edtPhone = findViewById(R.id.editPhone);

        circleImageView = findViewById(R.id.circleImageView);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


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
                if (resultUri == null){
                    Toast.makeText(PatientSignUpActivity.this, "Please select your image", Toast.LENGTH_SHORT).show();
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

                                if (resultUri !=null){
                                    final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("ProfileImage").child(currentUserId);
                                    Bitmap bitmap = null;
                                    try {
                                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                                    byte[] data = baos.toByteArray();

                                    UploadTask uploadTask = filePath.putBytes(data);

                                    uploadTask.addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(PatientSignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            if(taskSnapshot.getMetadata() != null){
                                                Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        String imageUri = uri.toString();
                                                        Map newImageMap = new HashMap();
                                                        newImageMap.put("ProfileImage", imageUri);

                                                        userDatabaseRef.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                                            @Override
                                                            public void onComplete(@NonNull Task task) {
                                                                if (task.isSuccessful()){
                                                                    Toast.makeText(PatientSignUpActivity.this, "Reg Sucess", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {
                                                                    Toast.makeText(PatientSignUpActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                        finish();
                                                    }
                                                });
                                            }

                                        }
                                    });

                                    Intent intent = new Intent(PatientSignUpActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                }

                            }
                        }
                    });
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            circleImageView.setImageURI(resultUri);
        }
    }

}
