package com.example.grandquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class EmployeeListView extends AppCompatActivity {

    List<EmployeeModel> MyEmployees;
    DataBaseHelper databaseHelper;
    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list_view);
        databaseHelper=new DataBaseHelper(EmployeeListView.this);
        MyEmployees=databaseHelper.getAllEmployees();

        simpleList = (ListView) findViewById(R.id.emp_list);
        ArrayAdapter<EmployeeModel> arrayAdapter = new ArrayAdapter<EmployeeModel>(this, R.layout.activity_list, R.id.empText, MyEmployees);
        simpleList.setAdapter(arrayAdapter);



    }
}