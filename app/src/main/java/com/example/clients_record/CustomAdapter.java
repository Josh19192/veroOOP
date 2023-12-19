package com.example.clients_record;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaDrm;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHlder> {
    private Context context;
    private ArrayList emp_id, emp_name,emp_position, status,salary,date;


    CustomAdapter(Context context, ArrayList emp_id, ArrayList emp_name, ArrayList emp_position,ArrayList status,ArrayList salary, ArrayList date){
        this.context = context;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_position = emp_position;
        this.status = status;
        this.salary = salary;
        this.date = date;
    }

    @NonNull
    @Override
    public MyViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHlder holder, @SuppressLint("RecyclerView") int position) {

        holder.emp_id.setText(String.valueOf(emp_id.get(position)));
        holder.emp_name.setText(String.valueOf(emp_name.get(position)));
        holder.emp_position.setText(String.valueOf(emp_position.get(position)));
        holder.status.setText(String.valueOf(status.get(position)));
        holder.salary.setText(String.valueOf("Salary PHP"+ salary.get(position)));
        holder.date.setText(String.valueOf("Date Hired" + date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update_activity.class );
                intent.putExtra("id", String.valueOf(emp_id.get(position)));
                intent.putExtra("name", String.valueOf(emp_name.get(position)));
                intent.putExtra("brand", String.valueOf(emp_position.get(position)));
                intent.putExtra("year", String.valueOf(status.get(position)));
                intent.putExtra("price", String.valueOf(salary.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return emp_id.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder{
        TextView emp_id, emp_name, emp_position,status,salary,date;
        LinearLayout mainLayout;
        public MyViewHlder(@NonNull View itemView) {
            super(itemView);

            emp_id = itemView.findViewById(R.id.car_id_txt);
            emp_name = itemView.findViewById(R.id.name_txt);
            emp_position = itemView.findViewById(R.id.position_txt);
            status = itemView.findViewById(R.id.status_txt);
            salary = itemView.findViewById(R.id.salary_txt);
            date = itemView.findViewById(R.id.date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
