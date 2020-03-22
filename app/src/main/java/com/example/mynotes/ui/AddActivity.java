package com.example.mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.Model.MyNotes;
import com.example.mynotes.R;
import com.example.mynotes.room.DatabaseClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    EditText title, note;
    FloatingActionButton done_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.title);
        note = findViewById(R.id.note);
        done_btn = findViewById(R.id.done_btn);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotes();
            }
        });
    }

    public void addNotes() {

        final String ntitle = title.getText().toString();
        final String nnote = note.getText().toString();

        class SaveTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                MyNotes myNotes = new MyNotes();
                myNotes.setTitle(ntitle);
                myNotes.setNote(nnote);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .myNotesDao()
                        .insert(myNotes);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this,MainActivity.class));
               finish();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }
}
