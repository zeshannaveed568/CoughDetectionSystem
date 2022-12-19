package com.example.pulmonarydisease.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.DetailedReport;
import com.example.pulmonarydisease.Firebase.PatientInfoFirebase;
import com.example.pulmonarydisease.R;
import com.example.pulmonarydisease.ViewReportActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ViewReportAdapter extends FirebaseRecyclerAdapter<PatientInfoFirebase, ViewReportAdapter.ReportViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewReportAdapter(@NonNull FirebaseRecyclerOptions<PatientInfoFirebase> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ReportViewHolder holder, int position, @NonNull PatientInfoFirebase model) {
        holder.viewReport.setText(model.getName());

        holder.viewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open Detailed Report Activity
                Intent intent = new Intent(v.getContext(), DetailedReport.class);
                v.getContext().startActivity(intent);


            }
        });

    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.viewreportlayout, parent, false);

        return new ReportViewHolder(view);
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView viewReport;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);

            viewReport  = itemView.findViewById(R.id.viewReportName);




        }
    }
}
