package com.example.clients_record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button add_button;

    DBHelper myDB;
    ArrayList<String > emp_id, emp_name, emp_position, status, salary, date;
    CustomAdapter customAdapter;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_activity.class);
                startActivity(intent);

            }
        });

        myDB = new DBHelper(MainActivity.this);
        emp_id = new ArrayList<>();
        emp_name = new ArrayList<>();
        emp_position = new ArrayList<>();
        status = new ArrayList<>();
        salary = new ArrayList<>();
        date = new ArrayList<>();

        displayAllData();

        customAdapter = new CustomAdapter(MainActivity.this, emp_id,emp_name,emp_position,status,salary,date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    void displayAllData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                emp_id.add(cursor.getString(0));
                emp_name.add(cursor.getString(1));
                emp_position.add(cursor.getString(2));
                status.add(cursor.getString(3));
                salary.add(cursor.getString(4));
                date.add(cursor.getString(5));


            }
        }
    }
}