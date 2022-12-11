package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {

    CircleImageView img;

    EditText fullName, email, phone, cnic;
    String fullNameIntent, emailIntent, phoneIntent, cnicIntent;


    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;


    Button btnUpdate;


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


        //Set data to edit text

        fullName.setText(fullNameIntent);
        email.setText(emailIntent);
        phone.setText(phoneIntent);
        cnic.setText(cnicIntent);

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






//            databaseReference.child("fullName").setValue(fullName.getText().toString());
//            databaseReference.child("email").setValue(email.getText().toString());
//            databaseReference.child("phone").setValue(phone.getText().toString());
//            databaseReference.child("cnic").setValue(cnic.getText().toString());

            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();

        }
   }


