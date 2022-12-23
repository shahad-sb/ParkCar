package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Car_list extends AppCompatActivity {
    ArrayList<Integer> plate_num;
    ArrayList<String> car_color;
    DBHelper DB;
    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        DB = new DBHelper(this);
        plate_num = new ArrayList<>();
        car_color = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, plate_num, car_color);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata()
    {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(Car_list.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                plate_num.add(cursor.getInt(0));
                car_color.add(cursor.getString(1));

            }
        }
    }

    }
