package com.example.pulmonarydisease.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Firebase.Appointment;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AppointmentPatientAdapter extends FirebaseRecyclerAdapter<Appointment, AppointmentPatientAdapter.AppointmentPatientViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AppointmentPatientAdapter(@NonNull FirebaseRecyclerOptions<Appointment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AppointmentPatientAdapter.AppointmentPatientViewHolder holder, int position, @NonNull Appointment model) {

        holder.doctorName.setText(model.getDoctorName());
        holder.appointmentDate.setText(model.getEtDate());
        holder.appointmentTime.setText(model.getEtTime());


        holder.deleteAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove from entry from firebase
                getRef(position).removeValue();
            }
        });

    }

    @NonNull
    @Override
    public AppointmentPatientAdapter.AppointmentPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=
                LayoutInflater.from(parent.getContext()).inflate(R.layout.appointments_patientside_holder,parent,false);
        return new AppointmentPatientViewHolder(view);

    }

    public class AppointmentPatientViewHolder extends RecyclerView.ViewHolder {

        TextView doctorName, appointmentDate, appointmentTime;

        ImageView deleteAppointment;

        public AppointmentPatientViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.doctorAppointmentName);
            appointmentDate = itemView.findViewById(R.id.doctorAppointmentDate);
            appointmentTime = itemView.findViewById(R.id.doctorAppointmenTime);


            deleteAppointment = itemView.findViewById(R.id.deleteAppointment);




        }
    }
    }

