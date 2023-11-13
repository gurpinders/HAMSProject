package com.example.hamsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Patient> {

    private Context mContext;
    private int mResource;

    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        this.mResource = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(mContext).inflate(mResource,parent,false);
        TextView address = view.findViewById(R.id.textView_two);
        TextView email = view.findViewById(R.id.textView_three);
        TextView health = view.findViewById(R.id.textView_fourth);
        TextView name = view.findViewById(R.id.textView_one);
        TextView number = view.findViewById(R.id.textView_fifth);

        address.setText(getItem(position).getAddress());
        email.setText(getItem(position).getEmail());
        health.setText(getItem(position).getHealth());
        name.setText(getItem(position).getName());
        number.setText(getItem(position).getNumber());

        return view;
    }
}
