package com.example.pulmonarydisease.Adapter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Firebase.DoctorInfoFirebase;
import com.example.pulmonarydisease.R;
import com.example.pulmonarydisease.Fragments.ReportFragment;
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
        holder.doctorType.setText(model.getType());


        holder.whatsappDoc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PackageManager pm = v.getContext().getPackageManager();
                try {

                    String url = "https://api.whatsapp.com/send?phone=" + model.getPhone() + "&text=" + "Hello Doctor";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                }


            }

        });


        holder.btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_doctor, new ReportFragment(model.getName(),model.getEmail(),model.type, model.getPhone())).addToBackStack(null).commit();


            }
        });

    }






    @NonNull
    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.listdoctorpatientstyle,parent,false);

        return new DoctorViewHolder(view);

    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName, doctorEmail, doctorType, doctorPhone;

        Button btnReport;

        ImageView whatsappDoc;


        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.doctorPatientName);
            doctorEmail = itemView.findViewById(R.id.doctorPatientEmail);
            doctorType = itemView.findViewById(R.id.doctorPatientType);

            whatsappDoc =itemView.findViewById(R.id.whatsappUser);


            btnReport = itemView.findViewById(R.id.btnReport);


        }
    }



}
