package com.example.mynotes.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Model.MyNotes;
import com.example.mynotes.R;
import com.example.mynotes.room.DatabaseClient;
import com.example.mynotes.ui.MainActivity;

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
    public void onBindViewHolder(@NonNull MyNotesViewHolder holder, final int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.notes.setText(list.get(position).getNote());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                //positive button
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyNotes myNotes = list.get(position);
                        deleteTask(myNotes);
                    }
                });
                //negetive button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyNotesViewHolder extends RecyclerView.ViewHolder{

        private TextView title,notes;
        private ImageView delete;
        private CardView main_card;

        public MyNotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            notes = itemView.findViewById(R.id.notes);
            main_card = itemView.findViewById(R.id.main_card);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private void deleteTask(final MyNotes myNotes) {

        class DeleteTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context).getAppDatabase()
                        .myNotesDao()
                        .delete(myNotes);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context,MainActivity.class));
                ((Activity)context).finish();
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }
}
