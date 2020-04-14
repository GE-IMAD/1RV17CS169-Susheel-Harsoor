package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserDatabaseActivity extends AppCompatActivity {

    ArrayList<String> usersList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    DatabaseHelper myDb=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_database);

        //SQLiteDatabase db=openOrCreateDatabase("MyVotingAppDB",MODE_PRIVATE,null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS voters (name VARCHAR(20) ,number INT(10) PRIMARY KEY UNIQUE,password VARCHAR,vote VARCHAR(3))");

       try {
           myDb.updateListView(usersList);
           ListView listView = (ListView) findViewById(R.id.listView);
           arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usersList);
           arrayAdapter.notifyDataSetChanged();
           listView.setAdapter(arrayAdapter);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    /*public void updateListView() {
        Cursor cursor=db.rawQuery("SELECT * FROM users",null);

        int nameIndex=cursor.getColumnIndex("name");
        int numberIndex=cursor.getColumnIndex("number");
        int voteIndex=cursor.getColumnIndex("vote");

        if(cursor.moveToFirst()) {
            usersList.clear();

            do {
                usersList.add("Name :"+cursor.getString(nameIndex)+"\n"+"Number  :"+cursor.getString(numberIndex)+"\n"+"Vote :"+cursor.getString(voteIndex)+"\n");
            } while (cursor.moveToFirst());
            arrayAdapter.notifyDataSetChanged();
        }
    }*/

}
