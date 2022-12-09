package com.example.pulmonarydisease;

import android.app.Activity;
import android.app.AlertDialog;

public class LoadingDialogActivity {

    private Activity activity;
    private AlertDialog dialog;

    public LoadingDialogActivity(Activity myActivity){
        activity = myActivity;
    }


    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setView(R.layout.custom_dialog);
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }

}
