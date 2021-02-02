package com.example.grandquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String  DATABASE_NAME="employee_db";
    private static final String TABLE_NAME="employees";
    private static final String ID ="ID";
    private static final String name="name";
    private static final String design="design";
    private  static final String salary="salary";


    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                name+" TEXT ,"+
                design+" TEXT ,"+
                salary+" TEXT"+
                ")";
        db.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public void AddUser(EmployeeModel userModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(name,userModel.getName());
        contentValues.put(design,userModel.getDesign());
        contentValues.put(salary,userModel.getDesign());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }



    public List<EmployeeModel> getAllEmployees(){
        List<EmployeeModel> userModelList=new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                EmployeeModel userModel=new EmployeeModel(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                userModelList.add(userModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return userModelList;
    }


    public EmployeeModel getUser(String name){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{ID,name,design,salary},name+" = ?",new String[]{String.valueOf(name)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        EmployeeModel userModel=new EmployeeModel(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        return userModel;
    }
    public int updateUser(EmployeeModel userModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,userModel.getName());
        contentValues.put(design,userModel.getDesign());
        return db.update(TABLE_NAME,contentValues,name+"=?",new String[]{String.valueOf(userModel.getName())});

    }

    public void deleteUser(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+"=?",new String[]{id});
        db.close();
    }

    public int getTotalCount(){
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }
}

