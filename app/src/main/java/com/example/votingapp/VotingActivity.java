package com.example.votingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import static com.example.votingapp.R.id.voteBJP;
import static com.example.votingapp.R.id.voteINC;
import static com.example.votingapp.R.id.voteINDEPENDENT;
import static com.example.votingapp.R.id.voteJDS;
import static com.example.votingapp.R.id.voteNOTA;

public class VotingActivity extends AppCompatActivity {

    DatabaseHelper myDB=new DatabaseHelper(this);
    RadioGroup radioGroup;
    String number = "";
    Button voteButton;
    int radioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        voteButton = (Button) findViewById(R.id.buttonVote);

        Intent intent = getIntent();
        number = intent.getStringExtra("number");

        voteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                radioId = radioGroup.getCheckedRadioButtonId();
                SQLiteDatabase db = myDB.getReadableDatabase();

                switch (radioId) {
                    case voteBJP:db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'BJP'");
                                 db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
                                 finish();
                                 break;
                    case voteINC:db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'INC'");
                                 db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
                                 finish();
                                 break;
                    case voteJDS:db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'JDS'");
                                 db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
                                 finish();
                                 break;
                    case voteINDEPENDENT:db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'INDEPENDENT'");
                                         db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
                                         finish();
                                         break;
                    case voteNOTA:db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'NOTA'");
                                  db.execSQL("UPDATE voters SET vote = 'Yes' WHERE number =?", new String[]{number});
                                  finish();
                                  break;
                }
            }
        });
    }
}
