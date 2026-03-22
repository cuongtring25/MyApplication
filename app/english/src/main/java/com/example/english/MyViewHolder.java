package com.    example.english;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textview;


    public MyViewHolder(@NonNull View itemView, Context context, ArrayList<Vocab> data) {
        super(itemView);
        textview = itemView.findViewById(R.id.text_view);
    }
}