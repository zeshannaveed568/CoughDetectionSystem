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

import org.w3c.dom.Text;

public class AppointmentDoctorAdapter extends FirebaseRecyclerAdapter<Appointment, AppointmentDoctorAdapter.AppointmentViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AppointmentDoctorAdapter(@NonNull FirebaseRecyclerOptions<Appointment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AppointmentDoctorAdapter.AppointmentViewHolder holder, int position, @NonNull Appointment model) {
        holder.patientName.setText(model.getPatientName());
        holder.appointmentDate.setText(model.getEtDate());
        holder.appointmentTime.setText(model.getEtTime());


        holder.DeleteAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRef(position).removeValue();
            }
        });
    }

    @NonNull
    @Override
    public AppointmentDoctorAdapter.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_doctorside_holder,parent,false);

            return new AppointmentViewHolder(view);

    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView patientName,patientPhone, appointmentDate, appointmentTime;

        ImageView DeleteAppointment;


        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            patientName= itemView.findViewById(R.id.patientAppointmentName);
            patientPhone= itemView.findViewById(R.id.patientAppointmentPhone);
            appointmentDate= itemView.findViewById(R.id.patientAppointmentDate);
            appointmentTime= itemView.findViewById(R.id.patientAppointmentTime);


            DeleteAppointment= itemView.findViewById(R.id.deleteAppointmentDoctor);




        }
    }
    }
