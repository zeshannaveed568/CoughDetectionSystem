package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {

    CircleImageView img;

    EditText fullName, email, phone, cnic;
    String fullNameIntent, emailIntent, phoneIntent, cnicIntent;


    DatabaseReference databaseReference;

    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");


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
                    updateData(v);

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

    private void updateData( View v) {
        //Update data to firebase

        if (isNameChanged() || isEmailChanged() || isPhoneChanged() || isCnicChanged() ){
            //Data is changed
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();

        }
        else {
            //Data is not changed
            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
        }

    }



    private boolean isNameChanged() {
        if (!fullNameIntent.equals(fullName.getText().toString())){


            //Name is changed
            databaseReference.child("users").child("name").setValue(fullName.getText().toString());

            //Update name in firebase

            fullNameIntent = fullName.getText().toString();
            return true;
        }
        else {
            return false;
        }

    }

    private boolean isEmailChanged() {
        if (!emailIntent.equals(email.getText().toString())){
            //Email is changed
            databaseReference.child(emailIntent).child("email").setValue(email.getText().toString());
            //Update email in firebase
            emailIntent = email.getText().toString();
            return true;
        }
        else {
            return false;
        }


    }

    private boolean isPhoneChanged() {
        if (!phoneIntent.equals(phone.getText().toString())){
            //Phone is changed
            databaseReference.child(phoneIntent).child("phone").setValue(phone.getText().toString());

            //Update phone in firebase
            phoneIntent = phone.getText().toString();

            return true;
        }
        else {
            return false;
        }
    }

    private boolean isCnicChanged() {
        if (!cnicIntent.equals(cnic.getText().toString())){
            //change existing cnic in firebase of current user
            databaseReference.child(cnicIntent).child("cnic").setValue(cnic.getText().toString());

            //Update cnic in firebase
            cnicIntent = cnic.getText().toString();
            return true;




        }
        else {
            return false;
        }

    }

}