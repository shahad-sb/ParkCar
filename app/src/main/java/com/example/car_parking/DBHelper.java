package com.example.car_parking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    public DBHelper(Context context) {
        super(context, "CarParking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table CarParking(Plate_number Integer primary key, Color TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists CarParking");
    }
    //Method for Inserting values inside Patients_Reports table
    public Boolean insertuserdata(int Plate_number, String Color)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Plate_number", Plate_number);
        contentValues.put("Color", Color);
        long result = DB.insert("CarParking", null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    //Display Data of Patients_report
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from CarParking", null);
        return cursor;
    }

    }

