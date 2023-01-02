package com.example.pulmonarydisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pulmonarydisease.Adapter.CustomAdapterSpinner;
import com.example.pulmonarydisease.Firebase.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreateAppointmentActivity extends AppCompatActivity {

    EditText etPatientName, etPatientAge, etPatientPhone, etPatientEmail, etDoctorEmail;
    CalendarView etDate;

    TextView tvDateSet, tvTimeSet;

    TimePicker timePicker;

    Spinner spnDoctorName;

    Button btnCreateAppointment;

    ValueEventListener listener;

    ArrayList<String> list;
    CustomAdapterSpinner adapter;

    String userEmail;

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
        tvTimeSet = findViewById(R.id.timeInputSet);
        etDoctorEmail = findViewById(R.id.doctorEmailInput);
        timePicker = findViewById(R.id.timePicker);



        //get current user id
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        //get current user email
        userEmail = user.getEmail();

        //set current user email to patient email
        etPatientEmail.setText(userEmail);


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //set time should be from 8Am to 5Pm
                if (hourOfDay < 8 || hourOfDay > 17) {
                    Toast.makeText(CreateAppointmentActivity.this, "Please choose time between 8Am to 5Pm", Toast.LENGTH_SHORT).show();
                } else {
                    tvTimeSet.setText(hourOfDay + ":" + minute);
                }
            }
        });

        etDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                int currentyear = calendar.get(Calendar.YEAR);
                int currentmonth = calendar.get(Calendar.MONTH);
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                //get mobile date
                if(year < currentyear || (year == currentyear && month < currentmonth) || (year == currentyear && month == currentmonth && dayOfMonth < currentDay)) {
                    Toast.makeText(CreateAppointmentActivity.this, "Please select a future date", Toast.LENGTH_SHORT).show();
                } else {
                //set selected date as appointment date
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    tvDateSet.setText(date);
                }
            }
        });





        //Hooks for Spinner

        spnDoctorName = findViewById(R.id.spinnerDoctor);


        //fetch data from firebase in spinner
        list = new ArrayList<>();
        adapter = new CustomAdapterSpinner(this, android.R.layout.simple_spinner_item, list){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = null;

                // If this is the initial dummy entry, make it hidden
                if (position == 2 || position == 4 || position == 6 || position == 8 || position == 10 || position == 12 ) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }

                else {
                    // Pass convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDoctorName.setAdapter(adapter);



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

        list.clear();
        list.add("Select Doctor");
        listener = reference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {

                    //Check for type doctor
                    String type = item.child("type").getValue(String.class);
                    if (type.equals("doctor")) {
                        String name = item.child("name").getValue(String.class);
                        String email = item.child("email").getValue(String.class);

                        list.add(name);
                        list.add(email);



                        //set doctor email to etDoctorEmail when doctor name is selected
                        spnDoctorName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                etDoctorEmail.setText(list.get(position+1));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


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

        String date = tvDateSet.getText().toString();
        String time = tvTimeSet.getText().toString();

        String patientEmail = userEmail.toLowerCase(Locale.ROOT);
        String doctorEmail = etDoctorEmail.getText().toString().toLowerCase(Locale.ROOT);




        String doctorName = spnDoctorName.getSelectedItem().toString();

        if (patientName.isEmpty() || patientAge.isEmpty() || patientPhone.isEmpty() || patientEmail.isEmpty() || date.isEmpty() || time.isEmpty() || doctorName.isEmpty() || doctorEmail.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {

            //Create new firebase user
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String Uid = user.getUid();

//            //Create new appointment
            Appointment appointment = new Appointment(patientName, patientAge, patientPhone, patientEmail, date, time, doctorName, doctorEmail);


            //Save appointment to firebase
            FirebaseDatabase.getInstance().getReference("Appointments").child(Uid).setValue(appointment);

            //Show success message
            Toast.makeText(CreateAppointmentActivity.this, "Appointment Set Sucessfully", Toast.LENGTH_SHORT).show();

            //Go back to home page

            //Go back to home activity
            finish();

        }




    }
}