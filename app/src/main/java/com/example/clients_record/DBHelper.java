package com.example.clients_record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final String DB_NAME = "employee_info.db";
    private static  final int DB_VERSION =1;
    private static final String table_name = "employee_record";
    private static final String column_id = "id";
    private static final String column_name = "name";
    private static final String column_position = "position";
    private static final String column_civil_status = "civil_status";
    private static final String column_salary = "salary";
    private static final String column_date = "date_hired";

    DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name +
                " (" + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                column_name + " TEXT NOT NULL, " +
                column_position + " TEXT NOT NULL, " +
                column_civil_status + " TEXT NOT NULL, " +
                column_salary + " INTEGER NOT NULL, " +
                column_date + " DATE);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);

    }
    void addCar(String name,String position, String civil_status,int salary, String date_hired){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_name,name);
        cv.put(column_position,position);
        cv.put(column_civil_status,civil_status);
        cv.put(column_salary,salary);
        cv.put(column_date, date_hired);

        long result = db.insert(table_name,null,cv);
        if(result == -1){
            Toast.makeText(context,"Add Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Succesfully added!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id, String name2, String brand2, String year2, String price2,String date2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_name, name2);
        cv.put(column_position, brand2);
        cv.put(column_civil_status, year2);
        cv.put(column_salary, price2);
        cv.put(column_date, date2);

        long result = db.update(table_name, cv, "id=?", new String[] {row_id});
        if(result == -1){
            Toast.makeText(context, "Update Failed!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Update!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

}
