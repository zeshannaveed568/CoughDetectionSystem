package com.example.pulmonarydisease;

import android.app.Activity;
import android.app.AlertDialog;

public class LoadingDialogActivity {

    private Activity activity;
    private AlertDialog dialog;

    LoadingDialogActivity(Activity myActivity){
        activity = myActivity;
    }


    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setView(R.layout.custom_dialog);
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }

}
