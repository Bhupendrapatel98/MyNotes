package com.example.mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynotes.Adapter.Adapter;
import com.example.mynotes.Model.MyNotes;
import com.example.mynotes.R;
import com.example.mynotes.room.DatabaseClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton float_btn;
    RecyclerView show_recycler;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float_btn = findViewById(R.id.float_btn);
        show_recycler = findViewById(R.id.show_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        show_recycler.setLayoutManager(layoutManager);

        float_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,AddActivity.class));

            }
        });

        showNotes();
    }

    public  void  showNotes(){

        class GetTasks extends AsyncTask<Void, Void, List<MyNotes>> {

            @Override
            protected List<MyNotes> doInBackground(Void... voids) {
                List<MyNotes> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .myNotesDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<MyNotes> tasks) {
                super.onPostExecute(tasks);
                 adapter = new Adapter(MainActivity.this, tasks);
                show_recycler.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
