package com.example.pulmonarydisease.PatientDash;


import static com.airbnb.lottie.network.FileExtension.JSON;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pulmonarydisease.SymptomsActivity;
import com.example.pulmonarydisease.LoginActivity;
import com.example.pulmonarydisease.PatientInfoActivity;
import com.example.pulmonarydisease.R;
import com.example.pulmonarydisease.ml.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import org.json.JSONException;
import org.json.JSONObject;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.security.auth.callback.Callback;


public class PatientDashFragment extends Fragment {

    Button btnLogout, patientInfo, btnSymptoms;

    Button floatRecord;
    TextView recordingLabel, coughResult, coughReport;

    private MediaRecorder recorder;
    private String fileName = null;

    StorageReference storageReference;


    private static final String LOG_TAG = "AudioRecordTest";

//    private static final LOG_TAG = "AudioRecordTest";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientDashFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PatientDashFragment newInstance(String param1, String param2) {
        PatientDashFragment fragment = new PatientDashFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_dash, container, false);

        // Initializing the Sign Out Button
        btnLogout = view.findViewById(R.id.btnPatientSignOut);


        // Initializing the Info Button
        patientInfo = view.findViewById(R.id.btnInfoPatient);

        // Initializing the Symptoms Button
        btnSymptoms = view.findViewById(R.id.btnAddSymptoms);

        //Storage Reference to store audio
        storageReference = FirebaseStorage.getInstance().getReference();

        //coughResult txtView Hook
        coughResult = view.findViewById(R.id.txtResults);

        //coughReport txtView Hook
        coughReport = view.findViewById(R.id.txtCoughReport);


        // Initializing the Record Button
        recordingLabel = view.findViewById(R.id.recordingLabel);
        floatRecord = view.findViewById(R.id.floatingRecordCough);
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/coughSample1.3gp";


        // defining the sign out button

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        });


        // Initializing the Info Button

        patientInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PatientInfoActivity.class);
                startActivity(intent);
            }
        });


//         Initializing the Symptoms Button

        btnSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SymptomsActivity.class);
                startActivity(intent);

            }
        });



        floatRecord.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            startRecording();
                            recordingLabel.setText("Recording...");
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            stopRecording();
                            recordingLabel.setText("Press and Hold to Analyze Cough");
                            Toast.makeText(getActivity(), "Recorded Sucessfully... Uploading & saved to Storage", Toast.LENGTH_SHORT).show();
                        }
                        return false;




                    }
                }
        );


        return view;

    }



    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //length of recording



        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;

        uploadAudio();

    }

    private void uploadAudio() {
        //upload audio to firebase
        StorageReference filepath = FirebaseStorage.getInstance().getReference().child("Audio");
        Uri uri = Uri.fromFile(new File(fileName));

        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //convert audio to json
                convertAudio();

            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Audio Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void convertAudio() {
        //convert audio to json
        //get the audio file from firebase
        StorageReference filepath = FirebaseStorage.getInstance().getReference().child("Audio").child("coughSample1.3gp");
        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //convert audio to json
                String url = uri.toString();
                String json = "{\"config\": {\"encoding\":\"LINEAR16\",\"sampleRateHertz\": 16000,\"languageCode\": \"en-US\"},\"audio\": {\"uri\": \"" + url + "\"}}";
                //send json to api
                sendForAnalysis(json);
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Audio Download Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void sendForAnalysis(String json) {


        String url = "https://coughAnalysis-api.herokuapp.com/coughAnalysis";


        RequestQueue queue = Volley.newRequestQueue(getActivity());

        Toast.makeText(getActivity(), "Sending for Analysis", Toast.LENGTH_SHORT).show();
        coughResult.setText("Healthy");

        // Request a string response from the provided URL.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                JSONObject jsonObject = null;

                //send json to api
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //get the result from the api
                            String result = response.getString("result");

                            //display the result
                            coughResult.setText(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(jsonObjectRequest);
            }
        }, 5000);




    }
}
