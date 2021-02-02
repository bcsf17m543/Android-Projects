package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    
    EditText TextField;
    String dgt,myData;
    ArrayList<Integer>oprtr=new ArrayList<Integer>();
    ArrayList<Double> Numbrs=new ArrayList<Double>();
    double FirstNum,SecondNum;
    Button addBut,subBut,mulBut,divBut,deci,result;
    boolean isClicked,isAdd,isSub,isMul,isDiv,isEqu=false;
    String TAG="Main activity";
    //1 for +,2 for -,3 for *,4 for /
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextField= (EditText) findViewById(R.id.TextField);
        TextField.setText("0");
        //addition
        addBut = (Button) findViewById(R.id.button10);
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEqu)
                {
                    ClearText(v);
                }
                    if(!isAdd)
                    {
                            RemoveLastOprtr();
                            TextField.append("+");
                            oprtr.add(1);
                            isAdd=true;
                            isClicked=false;
                    }
                }
        });
        //subtraction
        subBut = (Button) findViewById(R.id.button13);
        subBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEqu)
                {
                    ClearText(v);
                }
                if(!isSub)
                {
                    RemoveLastOprtr();
                    TextField.append("-");
                    oprtr.add(2);
                    isSub=true;
                    isClicked=false;
                }
            }
        });
        //Multiplication
        mulBut = (Button) findViewById(R.id.button14);
        mulBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEqu)
                {
                    ClearText(v);
                }
                if(!isMul)
                {
                    RemoveLastOprtr();
                    TextField.append("*");
                    oprtr.add(3);
                    isMul=true;
                    isClicked=false;
                }
            }
        });
        //division
        divBut = (Button) findViewById(R.id.button15);
        divBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEqu)
                {
                    ClearText(v);
                }
                if(!isDiv)
                {

                    RemoveLastOprtr();
                    TextField.append("/");
                    oprtr.add(4);
                    isDiv=true;
                    isClicked=false;
                }
            }
        });

    //for floating point numbers so that each number has one decimal point
        deci = (Button) findViewById(R.id.button12);
        deci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isClicked)
                {
                    dgt=".";
                    isClicked=true;
                    if(TextField.getText()==null)
                    {
                        TextField.setText(dgt);
                    }
                    else
                    {
                        TextField.append(dgt);
                    }
                }
            }
        });
        //ResultCalculation
        result=(Button) findViewById(R.id.button11);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEqu) {
                    isClicked = true;
                    RemoveLastOprtr(); //when second operand is absent
                    IfEmpty(); //when user presses only operators this function handles it.
                    ExtractNumbs(); //separates numbers and operators in two arrays
                    Calculate(); //calculates results
                }
            }
        });


    }
    public void OutputNums() //for debugging
    {
        for(int i=0;i<oprtr.size();i++)
        {
            Log.d(TAG,":::"+oprtr.get(i));
        }
    }
    public void Calculate() //Calculates the result
    {
        int j=0;
        int k=1;
        double FirstNum,SecondNum=0.00;
        boolean IsError=false;
        FirstNum=Numbrs.get(j);
        for(int i=1;i<Numbrs.size();i++)
        {
            SecondNum=Numbrs.get(i);
            int local_oprt=oprtr.get(j);
            j++;
            switch (local_oprt) {
                case 1:
                    FirstNum=FirstNum+SecondNum;
                    break;
                case 2:
                    FirstNum=FirstNum-SecondNum;
                    break;
                case 3:
                    FirstNum=FirstNum*SecondNum;
                    break;
                case 4:
                    if(SecondNum!=0){
                        FirstNum=FirstNum/SecondNum;
                    }
                    else{
                        IsError=true;
                        break;
                    }
                    break;
                default:
                    break;
            }

        }
        if(!IsError)
        {
            myData=Double.toString(FirstNum);
            TextField.setText(myData);
        }
        else
        {
            myData="Error";
            isEqu=true;
            TextField.setText(myData);
        }

        Numbrs.clear();
        oprtr.clear();

    }
    public void IfEmpty() //when user does not enters second operand this function handles it
    {
         myData=TextField.getText().toString();
         char test=myData.charAt(myData.lastIndexOf(myData));
         if(test==43 || test ==45 ||test==47||test==42)
         {
             myData=myData+"0";
         }
    }
    public void ExtractNumbs() //this function extracts numbers and operators into two separate arrays
    {
        char[] dats = new char[myData.length()];
        for (int i = 0; i < myData.length(); i++) {
            dats[i] = myData.charAt(i);
        }
        int index=0;
        int ptr=0;
        for(int i=0;i<myData.length();i++)
        {

            if(dats[i]==43 || dats[i]==45 ||dats[i]==47 ||dats[i]==42)
            {
                String number=myData.substring(ptr,i);
                ptr=i+1;
                index=ptr;
                Double local=Double.parseDouble(number);
                Numbrs.add(local);
            }
        }

        String last=myData.substring(index,myData.length());
        Double local_last=Double.parseDouble(last);
        Numbrs.add(local_last);
    }
    public boolean isOperator()
    {
        String txt=TextField.getText().toString();
        char opt=txt.charAt(txt.length()-1);
        if(opt=='+' || opt=='/' || opt=='*' ||opt=='-')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void RemoveLastOprtr() //If second operand is absent
    {
        if(isOperator())
        {
            String text=TextField.getText().toString();
            String newtxt=text.substring(0,text.length()-1);
            int index = oprtr.size() - 1;
            oprtr.remove(index);
            TextField.setText(newtxt);
        }
    }
    //for saving state data
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    public void ClearText(View view) //When user presses C
    {
        if(TextField.getText()!=null)
        {
            dgt="0";
            TextField.setText(dgt);
            isClicked=false; //for decimal numbers
            Makefalse(); //for all operations to be able to use again
        }
    }
    public void Makefalse() //for every new number, operators are enabled
    {
        isAdd=false;
        isDiv=false;
        isMul=false;
        isSub=false;
    }
    public void clickDigit(View view){
        dgt = ((Button) view).getText().toString();
        if(TextField.getText().toString().equals("0") ||isEqu)
        {
            isEqu=false;
            TextField.setText(dgt);
            Makefalse();
        }
        else
        {
            TextField.append(dgt);
            Makefalse();
        }
    }


}