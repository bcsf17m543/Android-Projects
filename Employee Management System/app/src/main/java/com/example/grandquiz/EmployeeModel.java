package com.example.grandquiz;

public class EmployeeModel {

    private String Name;
    private String Design;
    private float Salary;

    public EmployeeModel(String a,String b,String c)
    {
        Name=a;
        Design=b;
        Salary= Float.parseFloat(c);
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesign() {
        return Design;
    }

    public void setDesign(String design) {
        Design = design;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }

    public float getSalary() {
        return Salary;
    }
}
