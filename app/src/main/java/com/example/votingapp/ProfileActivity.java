package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button castVoteButton;
    TextView textView;
    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        castVoteButton = (Button) findViewById(R.id.castVoteButton);
        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        final String number = intent.getStringExtra("number");
        System.out.println(number);
        if (myDB.checkIfVoted(number)) {
            castVoteButton.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }


        castVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VotingActivity.class);
                intent.putExtra("number", number);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }
}
