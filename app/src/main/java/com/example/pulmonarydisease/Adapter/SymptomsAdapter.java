package com.example.pulmonarydisease.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulmonarydisease.Firebase.ModelSymptoms;
import com.example.pulmonarydisease.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SymptomsAdapter extends FirebaseRecyclerAdapter<ModelSymptoms, SymptomsAdapter.SymptomViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SymptomsAdapter(@NonNull FirebaseRecyclerOptions<ModelSymptoms> options) {
        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull SymptomsAdapter.SymptomViewHolder holder, int position, @NonNull ModelSymptoms model) {


    }

    @NonNull
    @Override
    public SymptomsAdapter.SymptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_detailed_report, parent, false);

        return new SymptomsAdapter.SymptomViewHolder(view);
    }

    public class SymptomViewHolder extends RecyclerView.ViewHolder{

        public SymptomViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
