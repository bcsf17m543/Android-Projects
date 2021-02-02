package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addItemScreen extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    MyItem newNote;
    Button onSave;
    EditText title;
    EditText description;
    Button setAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_screen);
        onSave=(Button) findViewById(R.id.Saveitem);
        title=(EditText)findViewById(R.id.title);
        description=(EditText)findViewById(R.id.description);
        setAlarm=(Button) findViewById(R.id.SetRemainder);
        databaseHelper=new DatabaseHelper(addItemScreen.this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                title.setText(extras.getString("title"));
                description.setText(extras.getString("desc"));
            }
        }

        onSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = title.getText().toString();
                String bc = description.getText().toString();
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                if(!ab.isEmpty() && !bc.isEmpty() )
                {
                    newNote=new MyItem(ab,bc,strDate);
                    databaseHelper.AddUser(newNote);
                    Toast.makeText(getApplicationContext(),"Note added Successfully", Toast.LENGTH_LONG).show();
                }

            }
        });
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),alarmActivity.class);
                startActivity(intent);
            }
        });


    }

}