package com.example.pulmonarydisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pulmonarydisease.Firebase.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateAppointmentActivity extends AppCompatActivity {

    EditText etPatientName, etPatientAge, etPatientPhone, etPatientEmail,  etTime;
    CalendarView etDate;

    TextView tvDateSet;

    Spinner spnDoctorName;

    Button btnCreateAppointment;

    ValueEventListener listener;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;


    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        //Hooks for Edit Text
        etPatientName = findViewById(R.id.patientNameInput);
        etPatientAge = findViewById(R.id.patientAgeInput);
        etPatientPhone = findViewById(R.id.patientPhoneInput);
        etPatientEmail = findViewById(R.id.patientEmailInput);
        etDate = findViewById(R.id.dateInput);
        tvDateSet = findViewById(R.id.dateInputSet);

        etDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(CreateAppointmentActivity.this, date, Toast.LENGTH_SHORT).show();
                tvDateSet.setText(date);
            }
        });


        etTime = findViewById(R.id.timeInput);

        //Hooks for Spinner

        spnDoctorName = findViewById(R.id.spinnerDoctor);

        //fetch data from firebase in spinner


        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spnDoctorName.setAdapter(adapter);



//        list.clear();
        fetchDataSpinner();



        //Hooks for Button

        btnCreateAppointment = findViewById(R.id.btnBookAppointment);

        btnCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAppointment();

            }
        });






    }

    private void fetchDataSpinner() {

        listener = reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    //Check for type doctor
//                  list.clear();
                    String type = item.child("type").getValue(String.class);
                    if (type.equals("doctor")) {
                        String name = item.child("name").getValue(String.class);
                        list.add(name);
                    }

                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void CreateAppointment() {
        //create appointment with doctor
        //send data to firebase
        //set all values

        String patientName = etPatientName.getText().toString();
        String patientAge = etPatientAge.getText().toString();
        String patientPhone = etPatientPhone.getText().toString();
        String patientEmail = etPatientEmail.getText().toString();

        String date = tvDateSet.getText().toString();
        String time = etTime.getText().toString();

        String doctorName = spnDoctorName.getSelectedItem().toString();

        if (patientName.isEmpty() || patientAge.isEmpty() || patientPhone.isEmpty() || patientEmail.isEmpty() || date.isEmpty() || time.isEmpty() || doctorName.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {

            //Create new firebase user
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String Uid = user.getUid();

            //Create new appointment
            Appointment appointment = new Appointment(patientName, patientAge, patientPhone, patientEmail, date, time, doctorName);


            //Save appointment to firebase
            FirebaseDatabase.getInstance().getReference("Appointments").child(Uid).setValue(appointment);

            //Show success message
            Toast.makeText(CreateAppointmentActivity.this, "Appointment Set Sucessfully", Toast.LENGTH_SHORT).show();

            //Go back to home activity
            finish();
//           String currentUserId = FirebaseAuth.getInstance().getUid();
//           reference.child(currentUserId).child("appointments").push().setValue(new Appointment(patientName, patientAge, patientPhone, patientEmail, date, time, doctorName));
//            Toast.makeText(this, "Appointment created successfully", Toast.LENGTH_SHORT).show();
//            finish();

        }




    }
}