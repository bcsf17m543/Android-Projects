package com.example.mytodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String  DATABASE_NAME="notes_db";
    private static final String TABLE_NAME="notes";
    private static final String ID="id";
    private static final String title="title";
    private static final String description="description";
    private static final String created_at="created_at";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                title+" TEXT ,"+
                description+" TEXT ,"+
                created_at+ " TEXT "+
                ")";
        db.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public void AddUser(MyItem userModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(title,userModel.getTitle());
        contentValues.put(description,userModel.getDescription());
        contentValues.put(created_at,userModel.getCreated_at());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }



    public List<MyItem> getAllNotes(){
        List<MyItem> userModelList=new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                MyItem userModel=new MyItem(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                userModelList.add(userModel);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return userModelList;
    }


    public MyItem getUser(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{ID,title,description,created_at},ID+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        MyItem userModel=new MyItem(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        return userModel;
    }
    public int updateUser(MyItem userModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(title,userModel.getTitle());
        contentValues.put(description,userModel.getDescription());
        return db.update(TABLE_NAME,contentValues,title+"=?",new String[]{String.valueOf(userModel.getTitle())});

    }

    public void deleteUser(String titl){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,title+"=?",new String[]{titl});
        db.close();
    }

    public int getTotalCount(){
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }
}
