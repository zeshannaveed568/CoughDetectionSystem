package com.example.pulmonarydisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {

    CircleImageView img;

    EditText fullName, email, phone, cnic;
    String fullNameIntent, emailIntent, phoneIntent, cnicIntent;

    Button btnUpdate;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
    FirebaseUser firebaseUser;

    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");


        btnUpdate = findViewById(R.id.updateData);

        fullName= findViewById(R.id.txtUserFullName);
        email= findViewById(R.id.txtUserEmail);
        phone= findViewById(R.id.txtUserPhone);
        cnic= findViewById(R.id.txtUserCnic);

        img = findViewById(R.id.profileImg);

        //update existing Image
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get image from gallery
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1000);
            }
        });



        getData();



        btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //update data in firebase

                    updateDate();

                }
        });


    }

    private void getData() {

        //Get data from intent

        fullNameIntent = getIntent().getStringExtra("fullName");
        emailIntent = getIntent().getStringExtra("email");
        phoneIntent = getIntent().getStringExtra("phone");
        cnicIntent = getIntent().getStringExtra("cnic");

        //get image from intent
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("image");

        //Set data to edit text
        fullName.setText(fullNameIntent);
        email.setText(emailIntent);
        phone.setText(phoneIntent);
        cnic.setText(cnicIntent);

        //set image to image view
        img.setImageBitmap(bitmap);

    }

   private void updateDate(){

        //user wants to update particular data in firebase

        //get data from edit text
        String fullNameUpdate = fullName.getText().toString();
        String emailUpdate = email.getText().toString();
        String phoneUpdate = phone.getText().toString();
        String cnicUpdate = cnic.getText().toString();

        //set data to firebase
       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       String userId = firebaseUser.getUid();

       databaseReference.child(userId).child("name").setValue(fullNameUpdate);
       databaseReference.child(userId).child("email").setValue(emailUpdate);
       databaseReference.child(userId).child("phone").setValue(phoneUpdate);
       databaseReference.child(userId).child("cnic").setValue(cnicUpdate);


        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();

    }

}




