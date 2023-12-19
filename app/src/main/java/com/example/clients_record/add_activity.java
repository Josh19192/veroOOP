package com.example.clients_record;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class add_activity extends AppCompatActivity {

    EditText txtName, txtPosition,txtStatus,txtSalary;
    Button addButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        String currentDate = getCurrentDate();


        txtName = findViewById(R.id.txt_name);
        txtPosition = findViewById(R.id.txt_position);
        txtStatus = findViewById(R.id.txt_status);
        txtSalary = findViewById(R.id.txt_salary);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper myDB = new DBHelper(add_activity.this);
                myDB.addCar(txtName.getText().toString().trim(),
                        txtPosition.getText().toString().trim(),
                        txtStatus.getText().toString().trim(),
                        Integer.valueOf(txtSalary.getText().toString().trim()),
                        currentDate);

                Intent intent = new Intent(add_activity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }










    private String getCurrentDate() {
        // Get the current date in the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());

    }
}