package com.example.votingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.votingapp.R.id.voteBJP;
import static com.example.votingapp.R.id.voteINC;
import static com.example.votingapp.R.id.voteINDEPENDENT;
import static com.example.votingapp.R.id.voteJDS;
import static com.example.votingapp.R.id.voteNOTA;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context,"MyVotingAppDB.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS voters (name VARCHAR(20) ,number INT(10) PRIMARY KEY UNIQUE,password VARCHAR,vote VARCHAR(3))");
        db.execSQL("CREATE TABLE IF NOT EXISTS votecount (name VARCHAR(20) ,countvotes INT(10))");
        db.execSQL("INSERT INTO votecount ('name', 'countvotes') VALUES ('BJP', '0'),('INC', '0'),('JDS', '0'),('INDEPENDENT', '0'),('NOTA','0')");
        db.execSQL("INSERT INTO voters VALUES ('admin','1234567890','pass','No')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert (String username,String password,String number) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
            contentValues.put("name", username);
            contentValues.put("password", password);
            contentValues.put("number", number);
            contentValues.put("vote", "No");
            long ins = db.insert("voters", null, contentValues);
            db.close();
            return ins != -1;
    }

    public void updateDatabase(int radioId,String number) {
        SQLiteDatabase db=this.getReadableDatabase();
        if (radioId == R.id.voteBJP) {
            db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'BJP'");
            db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
            return;
        } else if (radioId == voteINC) {
            db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'INC'");
            db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
            //Toast.makeText(VotingActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
            return;
        } else if (radioId == voteJDS) {
            db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'JDS'");
            db.execSQL("UPDATE voters SET vote='Yes' WHERE number =?", new String[]{number});
            return;
        } else if (radioId == voteINDEPENDENT) {
            db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'INDEPENDENT'");
            db.execSQL("UPDATE voters SET vote = 'Yes' WHERE number =?", new String[]{number});
            return;
        } else if (radioId == voteNOTA) {
            db.execSQL("UPDATE votecount SET countvotes = countvotes + 1 WHERE name = 'NOTA'");
            db.execSQL("UPDATE voters SET vote = 'Yes' WHERE number =?", new String[]{number});
            return;
        } else {
            return;
        }
    }

    public boolean checkNumber (String number) {
        SQLiteDatabase db=this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.rawQuery("SELECT * FROM voters WHERE number=?",new String[]{number});
        if (cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public boolean numberPassword (String number,String password) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM voters WHERE number=? AND password=?",new String[] {number,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void updateListView(ArrayList<String> usersList) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM voters",null);

        int nameIndex=cursor.getColumnIndex("name");
        int numberIndex=cursor.getColumnIndex("number");
        int voteIndex=cursor.getColumnIndex("vote");

        if(cursor.moveToFirst()) {
            usersList.clear();
            cursor.moveToFirst();
            cursor.moveToNext();
            do {
                usersList.add("Name : "+cursor.getString(nameIndex)+"\n"+"Number  : "+cursor.getString(numberIndex)+"\n"+"Vote : "+cursor.getString(voteIndex)+"\n");
            } while (cursor.moveToNext());
        }
    }

    public  void displayVoteCounts (ArrayList<String> voteCountList) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM votecount", null);

            int nameIndex = cursor.getColumnIndex("name");
            int voteCountIndex = cursor.getColumnIndex("countvotes");

           if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                do {
                    voteCountList.add(cursor.getString(nameIndex) + " : " + cursor.getString(voteCountIndex) + "\n");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfVoted(String number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT vote FROM Voters WHERE number=?", new String[]{number});
        int voteIndex=cursor.getColumnIndex("vote");
            cursor.moveToFirst();
            if(cursor.getString(voteIndex).equals("Yes"))
                return true;
            else return false;
    }


}
