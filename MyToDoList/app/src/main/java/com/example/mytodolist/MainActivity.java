package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button addNote;
    Button viewNote;
    Button delNote;
    Button updNote;
    private String CHANNEL_ID = "channel_id";
    private int NOTIFICATION_ID = 1;
    private String CHANNEL_NAME = "About Us";
    public int NOTIFICIATION_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote=(Button) findViewById(R.id.addNote);
        viewNote=(Button) findViewById(R.id.viewNote);
        delNote=(Button)findViewById(R.id.delNote);
        updNote=(Button) findViewById(R.id.updNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),addItemScreen.class);
                startActivity(intent);
            }
        });
        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), NotesListView.class);
                startActivity(intent);
            }
        });
        delNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), DeleteItemView.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),addItemScreen.class);
                startActivity(intent);
            }
        });
        updNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), UpdateItem.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add:
            addNote.performClick();
            return(true);
        case R.id.reset:
            Toast.makeText(getApplicationContext(),"Screen Refreshed", Toast.LENGTH_LONG).show();
            return(true);
        case R.id.about:
            createNotificationChannel();
            createNotification();
            return(true);
        case R.id.exit:
            System.exit(0);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void createNotificationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NOTIFICIATION_IMPORTANCE);
            notificationChannel.setDescription("This is a notification channel");

            //register it
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
        public void createNotification(){

            Intent intent = new Intent(this, AboutNotification.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.favorite1)
                    .setContentTitle("About Us")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("This is a Simple Todo List app where you can set the Alarm for daily tasks and write nots about your daily activities."))
                    .setContentIntent(pendingIntent);


            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

        }

    }
