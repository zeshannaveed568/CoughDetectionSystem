package com.example.pulmonarydisease.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterSpinner extends ArrayAdapter<String> {


    private int hidingItemIndex,hidingItemIndex2,hidingItemIndex3;

    private int mHiddenPosition = -1;

    public CustomAdapterSpinner(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    public void hide(int position) {
        mHiddenPosition = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position == mHiddenPosition) {
            view.setVisibility(View.GONE);
        }
        return view;
    }
}
