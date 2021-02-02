package com.example.grandquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add_btn;
    Button view_btn;
    Button edit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_btn=(Button) findViewById(R.id.add_emp);
        view_btn=(Button) findViewById(R.id.view_emp);
        edit_btn=(Button) findViewById(R.id.edit_emp);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),addEmployee.class);
                startActivity(intent);
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),addEmployee.class);
                startActivity(intent);
            }
        });
    }
}