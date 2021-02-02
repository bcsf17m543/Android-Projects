package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class alarmActivity extends AppCompatActivity {

    EditText hours;
    EditText minutes;
    EditText message;
    Button setAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        hours=(EditText) findViewById(R.id.hours);
        minutes=(EditText) findViewById(R.id.minutes);
        setAlarm=(Button) findViewById(R.id.savetime);
        message=(EditText)findViewById(R.id.message);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=hours.getText().toString();
                String s2=minutes.getText().toString();

                if(isNumeric(s1) && isNumeric(s2))
                {
                    int hour = Integer.parseInt(hours.getText().toString());
                    int minut = Integer.parseInt(minutes.getText().toString());
                    String mess=message.getText().toString();
                    if (hour <= 24 && minut <= 60) {
                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, minut);
                        intent.putExtra(AlarmClock.EXTRA_MESSAGE,mess);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Correct input", Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Correct input", Toast.LENGTH_LONG).show();

                }
            }
        });

    }


    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }



}