package com.example.english;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>    {


    private Context context;
    private ArrayList<Vocab>  data;


    // Constructor để khởi tạo Adapter với dữ liệu và Context
    public MyAdapter(Context context, ArrayList<Vocab> data) {
        this.context = context;
        this.data = data;
    }


    // Tạo ViewHolder cho mỗi mục trong danh sách
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view,context,data);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Vocab item = data.get(position);
        String term = item.term;
        holder.textview.setText(term);
        holder.textview.setOnClickListener(v -> {
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                // PORTRAIT: Open Activity
                Intent intent = new Intent(context, VocabDetailActivity.class);
                intent.putExtra("vocab", item);
                context.startActivity(intent);
            } else {
                // LANDSCAPE: Update Fragment
                VocabFragment vocabFragment = new VocabFragment(item);
                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, vocabFragment)
                        .commit();
            }
        });
    }


    // Trả về số lượng mục trong danh sách
    @Override
    public int getItemCount() {
        return data.size();
    }
}
class Vocab implements Serializable {
    String term;
    String def;
    String ipa;


    public Vocab(String term, String def, String ipa) {
        this.term = term;
        this.def = def;
        this.ipa = ipa;
    }
}
