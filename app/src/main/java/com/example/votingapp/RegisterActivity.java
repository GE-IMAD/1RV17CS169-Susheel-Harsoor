package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText phoneNumber;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.Username);
        password=findViewById(R.id.Password);
        phoneNumber=findViewById(R.id.phoneNumber);

        myDB =new DatabaseHelper(this);
    }

    public void register(View view) {
        try {
            myDB =new DatabaseHelper(this);
            if (myDB.checkNumber(phoneNumber.getText().toString())) {
                if (myDB.insert(username.getText().toString(), password.getText().toString(), phoneNumber.getText().toString())) {
                    Toast.makeText(this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(this, "Invalid Registration", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "User Already exists", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"User Already Exists",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
