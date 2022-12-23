package com.example.car_parking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList car_color_id;
    private ArrayList<Integer>plateNum_id;
    SQLiteDatabase sqLiteDatabase;
    public MyAdapter(Context context, ArrayList<Integer> plateNum_id, ArrayList car_color_id) {
        this.context = context;
        this.car_color_id = car_color_id;
        this.plateNum_id = plateNum_id;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.car_entry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        int plate = plateNum_id.get(pos);
        holder.platen.setText(plateNum_id.get(position).toString()); //integer
        holder.colorc.setText(String.valueOf(car_color_id.get(position)));

        holder.button.setOnClickListener(new View.OnClickListener() {
            DBHelper dbHelper = new DBHelper(context);
            public void onClick(View v) {
            sqLiteDatabase = dbHelper.getReadableDatabase();
            long delete = sqLiteDatabase.delete("CarParking","Plate_number="+plate, null);

            if(delete != -1){
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT);
                plateNum_id.remove(pos);
                notifyDataSetChanged();
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return plateNum_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView platen, colorc;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            platen = itemView.findViewById(R.id.txtPN);
            colorc = itemView.findViewById(R.id.txtCC);
            button= (Button) itemView.findViewById(R.id.remove);

        }
    }
}
