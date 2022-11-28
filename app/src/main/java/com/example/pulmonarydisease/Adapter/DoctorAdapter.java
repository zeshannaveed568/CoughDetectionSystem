package com.example.pulmonarydisease.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DoctorAdapter extends FirebaseRecyclerAdapter<DoctorInfoFirebase, DoctorAdapter.DoctorViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DoctorAdapter(@NonNull FirebaseRecyclerOptions<DoctorInfoFirebase> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorAdapter.DoctorViewHolder holder, int position, @NonNull DoctorInfoFirebase model) {

        holder.doctorName.setText(model.getName());
        holder.doctorEmail.setText(model.getEmail());
        holder.doctorPhone.setText(model.getPhone());

    }

    @NonNull
    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.listdoctorpatientstyle,parent,false);

        return new DoctorViewHolder(view);

    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, doctorEmail, doctorPhone;


        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.doctorPatientName);
            doctorEmail = itemView.findViewById(R.id.doctorPatientEmail);
            doctorPhone = itemView.findViewById(R.id.doctorPatientType);


        }
    }
}
