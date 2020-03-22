package com.example.mynotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Model.MyNotes;
import com.example.mynotes.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyNotesViewHolder> {

    Context context;
    List<MyNotes> list;

    public Adapter(Context context, List<MyNotes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflaterc = LayoutInflater.from(context);
        View view = layoutInflaterc.inflate(R.layout.showdata,parent,false);
        return new MyNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNotesViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.notes.setText(list.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyNotesViewHolder extends RecyclerView.ViewHolder{

        private TextView title,notes;

        public MyNotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            notes = itemView.findViewById(R.id.notes);
        }
    }
}
