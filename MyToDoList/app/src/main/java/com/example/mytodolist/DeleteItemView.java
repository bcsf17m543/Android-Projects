package com.example.mytodolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;
import androidx.appcompat.app.AlertDialog;


public class DeleteItemView extends AppCompatActivity {

    public List<MyItem> allNotes;
    ListView simpleList;
    DatabaseHelper databaseHelper;
     CustomArrayAdapter customArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item_view);
        databaseHelper=new DatabaseHelper(this);
        allNotes=databaseHelper.getAllNotes();
        simpleList = (ListView)findViewById(R.id.del_list);
         customArrayAdapter=new CustomArrayAdapter(this,allNotes);
        simpleList.setAdapter(customArrayAdapter);
        simpleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.d("Delete","::: this is long click");

                Toast.makeText(getApplicationContext(),"Long Clicked Successfully", Toast.LENGTH_LONG).show();

                 createDeleteDialog(position);
                customArrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }
    private void createDeleteDialog(final int position)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete Note");
        builder.setMessage("Do you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyItem del_item= allNotes.get(position);
                databaseHelper.deleteUser(del_item.getTitle());
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}