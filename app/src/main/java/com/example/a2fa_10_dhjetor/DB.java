package com.example.a2fa_10_dhjetor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context) {
        super(context, "loginlogin.db",null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT,emri TEXT,email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean insertUser(String name,String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emri",name);
        contentValues.put("email",email);
        contentValues.put("password",password);

       long result= db.insert("user",null,contentValues);
       return result !=-1;
    }

    public boolean loginUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user where email=? and password=?", new String[]{email,password});

        if (cursor.getCount()>0){
            return true;
        }
        return false;

    }


}
