package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class VoteDatabaseActivity extends AppCompatActivity {

    ArrayList<String> usersList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    DatabaseHelper myDB=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_database);

        myDB.displayVoteCounts(usersList);

        ListView listView=(ListView)findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,usersList);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
    }
}
