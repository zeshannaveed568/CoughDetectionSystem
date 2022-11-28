package com.example.pulmonarydisease.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
        holder.patientPhone.setText(model.getPhone());


    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdoctorpatientstyle,parent,false);
        return new PatientViewHolder(view);

    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {

        TextView patientName, patientEmail, patientPhone;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);


            patientName = itemView.findViewById(R.id.doctorPatientName);
            patientEmail = itemView.findViewById(R.id.doctorPatientEmail);
            patientPhone= itemView.findViewById(R.id.doctorPatientType);

        }
    }
}
