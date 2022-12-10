package com.example.pulmonarydisease.Adapter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.Fragments.ReportFragment;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientAdapter extends FirebaseRecyclerAdapter<PatientInfoFirebase, PatientAdapter.PatientViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PatientAdapter(@NonNull FirebaseRecyclerOptions<PatientInfoFirebase> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientViewHolder holder, int position, @NonNull PatientInfoFirebase model) {

        holder.patientName.setText(model.getName());
        holder.patientEmail.setText(model.getEmail());
        holder.patientType.setText(model.getType());

        holder.whatsappPatient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PackageManager pm = v.getContext().getPackageManager();
                try {

                    String url = "https://api.whatsapp.com/send?phone=" + model.getPhone() + "&text=" + "Hello " + model.getName();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                }


            }

        });



        holder.btnPatientReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_patient, new ReportFragment(model.getName(),model.getEmail(),model.type, model.getPhone())).addToBackStack(null).commit();


            }
        });



    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdoctorpatientstyle,parent,false);
        return new PatientViewHolder(view);

    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {

        TextView patientName, patientEmail, patientType;

        Button btnPatientReport;

        ImageView whatsappPatient;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);


            patientName = itemView.findViewById(R.id.doctorPatientName);
            patientEmail = itemView.findViewById(R.id.doctorPatientEmail);
            patientType= itemView.findViewById(R.id.doctorPatientType);

            whatsappPatient =itemView.findViewById(R.id.whatsappUser);

            btnPatientReport = itemView.findViewById(R.id.btnReport);

        }
    }



}
