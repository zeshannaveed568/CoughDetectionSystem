package com.example.pulmonarydisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DoctorInfoActivity extends AppCompatActivity {

    Button btnContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        btnContact = findViewById(R.id.btnContactAdmin);


        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                try {

                    String url = "https://api.whatsapp.com/send?phone=" + "923335424738" + "&text=" + "Hello Admin " ;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Error/n" + e, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}