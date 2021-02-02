package com.example.grandquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editEmployee extends AppCompatActivity {

    Button edit_edit;
    EmployeeModel emp;
    EditText nam;
    EditText sal;
    EditText des;
    DataBaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);
        edit_edit=(Button) findViewById(R.id.add_add);
        nam=(EditText)findViewById(R.id.emp_nam1);
        sal=(EditText)findViewById(R.id.emp_sal1);
        des=(EditText)findViewById(R.id.emp_dep1);
       EmployeeModel populateEmp= databaseHelper.getUser(nam.getText().toString());

        databaseHelper=new DataBaseHelper(editEmployee.this);
        edit_edit.setOnClickListener(new View.OnClickListener() {
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
    public void Populate(String name)
    {
        EmployeeModel populateEmp= databaseHelper.getUser(name);
        nam.setText(populateEmp.getName());
        des.setText(populateEmp.getDesign());
        sal.setText((int) populateEmp.getSalary());

    }
}