package com.example.car_parking;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    EditText plate_num, color;
    Button insert, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plate_num = findViewById(R.id.Plate_numbertxt);
        color= findViewById(R.id.CarColortxt);

        DB = new DBHelper(this);

        //Start to Display Patients_reports, this Button is for Doctor page
        view = findViewById(R.id.view_btn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Car_list.class));
            }
        });
        //End of Displaying Patient_reports

        //Start of inserting new values in the database, this button is for Patients page
        insert = findViewById(R.id.Add_btn);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Plate_numtxt = Integer.parseInt(plate_num.getText().toString());
                String carColortxt = color.getText().toString();

                Boolean checkinsertdata  = DB.insertuserdata(Plate_numtxt, carColortxt);
                if(checkinsertdata==true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}