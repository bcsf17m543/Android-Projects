package com.example.grandquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addEmployee extends AppCompatActivity {

    Button add_add;
    EmployeeModel emp;
    EditText nam;
    EditText sal;
    EditText des;
DataBaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add_add=(Button) findViewById(R.id.add_add);
        nam=(EditText)findViewById(R.id.emp_nam);
        sal=(EditText)findViewById(R.id.emp_sal);
        des=(EditText)findViewById(R.id.emp_dep);

        databaseHelper=new DataBaseHelper(addEmployee.this);
        add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = nam.getText().toString();
                String bc = sal.getText().toString();
                String cd=des.getText().toString();
                if(!ab.isEmpty() && !bc.isEmpty()  && !cd.isEmpty() )
                {
                    emp=new EmployeeModel(ab,bc,cd);
                    databaseHelper.AddUser(emp);
                    Toast.makeText(getApplicationContext(),"Employee Successfully", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}