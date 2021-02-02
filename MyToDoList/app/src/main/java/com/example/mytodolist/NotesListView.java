package com.example.mytodolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NotesListView extends AppCompatActivity {

    public List<MyItem> MyNotes;
    ListView simpleList;
    DatabaseHelper databaseHelper;
    private ArrayList<String> NotesTitles;
    ArrayAdapter arrayAdapter;
    CustomArrayAdapter customArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        databaseHelper=new DatabaseHelper(NotesListView.this);
        MyNotes=databaseHelper.getAllNotes();
        simpleList = (ListView)findViewById(R.id.note_list);
        NotesTitles=new ArrayList<String>();
        PopulateList();
        customArrayAdapter=new CustomArrayAdapter(this,MyNotes);
        simpleList.setAdapter(customArrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext()," Clicked Successfully", Toast.LENGTH_LONG).show();
                MyItem view_item=MyNotes.get(position);
                databaseHelper.deleteUser(view_item.getTitle());
                Intent intent=new Intent(getBaseContext(),SingleNote.class);
                intent.putExtra("title",view_item.getTitle());
                intent.putExtra("desc",view_item.getDescription());
                startActivity(intent);
            }
        });
    }

    private void PopulateList()
    {
        for(int i=0;i<MyNotes.size();i++)
        {
            MyItem temp= MyNotes.get(i);
            NotesTitles.add(temp.getTitle());
        }
    }

}