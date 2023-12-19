package com.example.clients_record;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_activity extends AppCompatActivity {

    EditText name;
    EditText position;
    EditText status;
    EditText salary;
    EditText date;
    Button update_button, delete_button;
    String id1,name1,position1,status1,salary1,date1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.txt_name2);
        position = findViewById(R.id.txt_position2);
        status = findViewById(R.id.txt_status2);
        salary = findViewById(R.id.txt_salary2);
        date = findViewById(R.id.txt_date2);
        update_button = findViewById(R.id.update_button);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper myDB = new DBHelper(update_activity.this);
                myDB.updateData(id1, name.getText().toString(), position.getText().toString(), status.getText().toString(), salary.getText().toString(), date.getText().toString());

                Intent intent = new Intent(update_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete_button = findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("brand") && getIntent().hasExtra("year") &&
                getIntent().hasExtra("price") && getIntent().hasExtra("date") ){
            //getting
            id1 = getIntent().getStringExtra("id");
            name1 = getIntent().getStringExtra("name");
            position1 = getIntent().getStringExtra("brand");
            status1 = getIntent().getStringExtra("year");
            salary1 = getIntent().getStringExtra("price");
            date1 = getIntent().getStringExtra("date");

            //setting
            name.setText(name1);
            position.setText(position1);
            status.setText(status1);
            salary.setText(salary1);
            date.setText(date1);
        }else{
            Toast.makeText(this,"No Data!",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Delete " + name1 + " ?");
        build.setMessage("Are you sure want to Delete " + name1 + " ?");
        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(update_activity.this);
                myDB.deleteData(id1);

                Intent intent = new Intent(update_activity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        build.create().show();

    }
}