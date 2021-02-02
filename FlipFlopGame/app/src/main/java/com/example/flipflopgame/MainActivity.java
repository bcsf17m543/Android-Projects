package com.example.flipflopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int points=0;
    Button MyReset;
    TextView myScore;
    TextView myTimer;
    ImageView im_01,im_02,im_03,im_04,im_05,im_06,im_07,im_08,im_09,im_10,im_11,im_12;
    Bitmap myCatch;
    int img_01,img_02,img_03,img_04,img_05,img_06,img_07,img_08,img_09,img_10,img_11,img_12;
    List<Integer> shuffledPictures;
    int questionMark;
    int myClicks=0;
    int firstTag,secondTag=0;
    boolean IsTimeOver=false;
    CountDownTimer myTime;
    Bitmap firstCard;
    Bitmap secondCard;
    Boolean[] CardClicks = new Boolean[12];
    Boolean[] IamDone=new Boolean[12];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //To avoid exception at restart due to first bitmap
        myCatch=BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        MyReset=(Button)findViewById(R.id.Reset_btn);
        //fetching my score view
        myScore=(TextView) findViewById(R.id.my_score);
        myTimer=(TextView)findViewById(R.id.timer_txt);
        //fetching all question marks
        im_01=(ImageView)findViewById(R.id.question_mark1);
        im_02=(ImageView)findViewById(R.id.question_mark2);
        im_03=(ImageView)findViewById(R.id.question_mark3);
        im_04=(ImageView)findViewById(R.id.question_mark4);
        im_05=(ImageView)findViewById(R.id.question_mark5);
        im_06=(ImageView)findViewById(R.id.question_mark6);
        im_07=(ImageView)findViewById(R.id.question_mark7);
        im_08=(ImageView)findViewById(R.id.question_mark8);
        im_09=(ImageView)findViewById(R.id.question_mark9);
        im_10=(ImageView)findViewById(R.id.question_mark10);
        im_11=(ImageView)findViewById(R.id.question_mark11);
        im_12=(ImageView)findViewById(R.id.question_mark12);



        //load images and shuffle them
        LoadImagesAndRandomize();
        MakeFalse(CardClicks);
        MakeFalse(IamDone);
        StartTheTimer();

        //Reset the Game
        MyReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points=0;
                EnableAll();
                LoadImagesAndRandomize();
                MakeFalseAll();
                TurnCardsOff();
                myScore.setText("Score: 0");
                StartTheTimer();
            }
        });

        im_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_01.setImageResource(shuffledPictures.get(0));
                    firstCard = ((BitmapDrawable)im_01.getDrawable()).getBitmap();
                    firstTag=1;
                    myClicks++;
                    CardClicks[0]=true;

                }
                else if(myClicks==1)
                {
                    im_01.setImageResource(shuffledPictures.get(0));
                    secondCard = ((BitmapDrawable)im_01.getDrawable()).getBitmap();
                    secondTag=1;
                    myClicks++;
                    if(!CardClicks[0])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }

                            myScore.setText("Score: "+points);
                        }
                    }

                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_02.setImageResource(shuffledPictures.get(1));
                    firstCard = ((BitmapDrawable)im_02.getDrawable()).getBitmap();
                    firstTag=2;
                    myClicks++;
                    CardClicks[1]=true;


                }
                else if(myClicks==1)
                {
                    im_02.setImageResource(shuffledPictures.get(1));
                    secondCard = ((BitmapDrawable)im_02.getDrawable()).getBitmap();
                    secondTag=2;
                    myClicks++;
                    if(!CardClicks[1])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });

        im_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_03.setImageResource(shuffledPictures.get(2));
                    firstCard = ((BitmapDrawable)im_03.getDrawable()).getBitmap();
                    firstTag=3;
                    myClicks++;
                    CardClicks[2]=true;


                }
                else if(myClicks==1)
                {
                    im_03.setImageResource(shuffledPictures.get(2));
                    secondCard = ((BitmapDrawable)im_03.getDrawable()).getBitmap();
                    secondTag=3;
                    myClicks++;
                    if(!CardClicks[2])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_04.setImageResource(shuffledPictures.get(3));
                    firstCard = ((BitmapDrawable)im_04.getDrawable()).getBitmap();
                    firstTag=4;
                    myClicks++;
                    CardClicks[3]=true;


                }
                else if(myClicks==1)
                {
                    im_04.setImageResource(shuffledPictures.get(3));
                    secondCard = ((BitmapDrawable)im_04.getDrawable()).getBitmap();
                    secondTag=4;
                    myClicks++;
                    if(!CardClicks[3])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_05.setImageResource(shuffledPictures.get(4));
                    firstCard = ((BitmapDrawable)im_05.getDrawable()).getBitmap();
                    firstTag=5;
                    myClicks++;
                    CardClicks[4]=true;


                }
                else if(myClicks==1)
                {
                    im_05.setImageResource(shuffledPictures.get(4));
                    secondCard = ((BitmapDrawable)im_05.getDrawable()).getBitmap();
                    secondTag=5;
                    myClicks++;
                    if(!CardClicks[4])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_06.setImageResource(shuffledPictures.get(5));
                    firstCard = ((BitmapDrawable)im_06.getDrawable()).getBitmap();
                    firstTag=6;
                    myClicks++;
                    CardClicks[5]=true;


                }
                else if(myClicks==1)
                {
                    im_06.setImageResource(shuffledPictures.get(5));
                    secondCard = ((BitmapDrawable)im_06.getDrawable()).getBitmap();
                    secondTag=6;
                    myClicks++;
                    if(!CardClicks[5])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_07.setImageResource(shuffledPictures.get(6));
                    firstCard = ((BitmapDrawable)im_07.getDrawable()).getBitmap();
                    firstTag=7;
                    myClicks++;
                    CardClicks[6]=true;


                }
                else if(myClicks==1)
                {
                    im_07.setImageResource(shuffledPictures.get(6));
                    secondCard = ((BitmapDrawable)im_07.getDrawable()).getBitmap();
                    secondTag=7;
                    myClicks++;
                    if(!CardClicks[6])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_08.setImageResource(shuffledPictures.get(7));
                    firstCard = ((BitmapDrawable)im_08.getDrawable()).getBitmap();
                    firstTag=8;
                    myClicks++;
                    CardClicks[7]=true;


                }
                else if(myClicks==1)
                {
                    im_08.setImageResource(shuffledPictures.get(7));
                    secondCard = ((BitmapDrawable)im_08.getDrawable()).getBitmap();
                    secondTag=8;
                    myClicks++;
                    if(!CardClicks[7])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_09.setImageResource(shuffledPictures.get(8));
                    firstCard = ((BitmapDrawable)im_09.getDrawable()).getBitmap();
                    firstTag=9;
                    myClicks++;
                    CardClicks[8]=true;


                }
                else if(myClicks==1)
                {
                    im_09.setImageResource(shuffledPictures.get(8));
                    secondCard = ((BitmapDrawable)im_09.getDrawable()).getBitmap();
                    secondTag=9;
                    myClicks++;
                    if(!CardClicks[8])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_10.setImageResource(shuffledPictures.get(9));
                    firstCard = ((BitmapDrawable)im_10.getDrawable()).getBitmap();
                    firstTag=10;
                    myClicks++;
                    CardClicks[9]=true;


                }
                else if(myClicks==1)
                {
                    im_10.setImageResource(shuffledPictures.get(9));
                    secondCard = ((BitmapDrawable)im_10.getDrawable()).getBitmap();
                    secondTag=10;
                    myClicks++;
                    if(!CardClicks[9])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_11.setImageResource(shuffledPictures.get(10));
                    firstCard = ((BitmapDrawable)im_11.getDrawable()).getBitmap();
                    firstTag=11;
                    myClicks++;
                    CardClicks[10]=true;


                }
                else if(myClicks==1)
                {
                    im_11.setImageResource(shuffledPictures.get(10));
                    secondCard = ((BitmapDrawable)im_11.getDrawable()).getBitmap();
                    secondTag=11;
                    myClicks++;
                    if(!CardClicks[10])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });
        im_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myClicks==0)
                {
                    im_12.setImageResource(shuffledPictures.get(11));
                    firstCard = ((BitmapDrawable)im_12.getDrawable()).getBitmap();
                    firstTag=12;
                    myClicks++;
                    CardClicks[11]=true;


                }
                else if(myClicks==1)
                {
                    im_12.setImageResource(shuffledPictures.get(11));
                    secondCard = ((BitmapDrawable)im_12.getDrawable()).getBitmap();
                    secondTag=12;
                    myClicks++;
                    if(!CardClicks[11])
                    {
                        if(CompareCards())
                        {
                            points++;
                            if(firstTag!=secondTag)
                            {
                                setEmpty(firstTag);
                                setEmpty(secondTag);
                            }
                            myScore.setText("Score: "+points);
                        }
                    }
                }
                else if(myClicks==2)
                {
                    //Turn all the cards
                    TurnCardsOff();
                    myClicks=0;
                }

            }
        });



    }
    private void StartTheTimer()
    {
        int counter=0;
       myTime= new CountDownTimer(40000, 1000) {

            public void onTick(long millisUntilFinished) {
                myTimer.setText(millisUntilFinished / 1000+"s");
            }

            public void onFinish() {
                DialogActivity myDialog=new DialogActivity();
                myDialog.show(getSupportFragmentManager(),"Flip flop game");
                myTimer.setText("0s");
               // Toast.makeText(getApplicationContext(),"Time over, Starting again", Toast.LENGTH_SHORT).show();
                points=0;
                EnableAll();
                LoadImagesAndRandomize();
                MakeFalseAll();
                TurnCardsOff();
                myScore.setText("Score: 0");
                StartTheTimer();
            }
        }.start();



    }
    private void MakeFalseAll()
    {
        for(int i=0;i<12;i++)
        {
            IamDone[i]=false;
        }
    }
    private void MakeFalse(Boolean[] arr)
    {
        for(int i=0;i<12;i++)
        {
            arr[i]=false;
        }
    }
    private boolean CompareCards()
    {
        if(firstCard.sameAs(secondCard))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private void setEmpty(int tag)
    {

                switch(tag)
                {
                    case 1:
                        im_01.setImageResource(R.drawable.empty);
                        im_01.setEnabled(false);
                        IamDone[0]=true;
                        break;
                    case 2:
                        im_02.setImageResource(R.drawable.empty);
                        im_02.setEnabled(false);
                        IamDone[1]=true;
                        break;
                    case 3:
                        im_03.setImageResource(R.drawable.empty);
                        im_03.setEnabled(false);
                        IamDone[2]=true;
                        break;
                    case 4:
                        im_04.setImageResource(R.drawable.empty);
                        im_04.setEnabled(false);
                        IamDone[3]=true;
                        break;
                    case 5:
                        im_05.setImageResource(R.drawable.empty);
                        im_05.setEnabled(false);
                        IamDone[4]=true;
                        break;
                    case 6:
                        im_06.setImageResource(R.drawable.empty);
                        im_06.setEnabled(false);
                        IamDone[5]=true;
                        break;
                    case 7:
                        im_07.setImageResource(R.drawable.empty);
                        im_07.setEnabled(false);
                        IamDone[6]=true;
                        break;
                    case 8:
                        im_08.setImageResource(R.drawable.empty);
                        im_08.setEnabled(false);
                        IamDone[7]=true;
                        break;
                    case 9:
                        im_09.setImageResource(R.drawable.empty);
                        im_09.setEnabled(false);
                        IamDone[8]=true;
                        break;
                    case 10:
                        im_10.setImageResource(R.drawable.empty);
                        im_10.setEnabled(false);
                        IamDone[9]=true;
                        break;
                    case 11:
                        im_11.setImageResource(R.drawable.empty);
                        im_11.setEnabled(false);
                        IamDone[10]=true;
                        break;
                    case 12:
                        im_12.setImageResource(R.drawable.empty);
                        im_12.setEnabled(false);
                        IamDone[11]=true;
                        break;

                }

    }
    private void EnableAll()
    {
        im_01.setEnabled(true);
        im_02.setEnabled(true);
        im_03.setEnabled(true);
        im_04.setEnabled(true);
        im_05.setEnabled(true);
        im_06.setEnabled(true);
        im_07.setEnabled(true);
        im_08.setEnabled(true);
        im_09.setEnabled(true);
        im_10.setEnabled(true);
        im_11.setEnabled(true);
        im_12.setEnabled(true);


    }
    private void TurnCardsOff()
    {
        if(!IamDone[0])
        {
            im_01.setImageResource(questionMark);
        }
        if(!IamDone[1])
        {
            im_02.setImageResource(questionMark);

        }
        if(!IamDone[2])
        {
            im_03.setImageResource(questionMark);

        }
        if(!IamDone[3])
        {
            im_04.setImageResource(questionMark);

        }
        if(!IamDone[4])
        {
            im_05.setImageResource(questionMark);
        }
        if(!IamDone[5])
        {
            im_06.setImageResource(questionMark);
        }
        if(!IamDone[6])
        {
            im_07.setImageResource(questionMark);
        }
        if(!IamDone[7])
        {
            im_08.setImageResource(questionMark);
        }
        if(!IamDone[8])
        {
            im_09.setImageResource(questionMark);
        }
        if(!IamDone[9])
       {
           im_10.setImageResource(questionMark);
       }
        if(!IamDone[10])
       {
           im_11.setImageResource(questionMark);
       }
        if(!IamDone[11])
       {
           im_12.setImageResource(questionMark);
       }

        MakeFalse(CardClicks);
        firstCard=myCatch;
        secondCard= myCatch;

    }
    private void LoadImagesAndRandomize()
    {
        Random random = new Random();
        boolean res=   random.nextBoolean();

        img_01=R.drawable.favorite1;
        img_02=R.drawable.favorite;
        img_03=R.drawable.camera;
        img_04=R.drawable.camera1;
        img_05=R.drawable.history;
        img_06=R.drawable.history1;
        img_07=R.drawable.loading;
        img_08=R.drawable.loading1;
        img_09=R.drawable.royal;
        img_10=R.drawable.royal1;
        questionMark=R.drawable.question;
        if(res)
        {
            img_11=R.drawable.search1;
            img_12=R.drawable.weather1;
        }
        else
        {
            img_11=R.drawable.search1;
            img_12=R.drawable.search;
        }
       //Shuffle all the loaded images
        Integer[] myPictures={img_01,img_02,img_03,img_04,img_05,img_06,img_07,img_08,img_09,img_10,img_11,img_12};
         shuffledPictures=ShuffleMyImages(myPictures);
        //myimage.setImageResource(pictures.get(position)); to get view of image

    }
    private List<Integer> ShuffleMyImages(Integer[] OriginalArray)
    {
        List<Integer> pictures = new ArrayList<Integer>();
        for (int index = 0; index < OriginalArray.length; index++)
        {
            pictures.add(OriginalArray[index]);
        }
        Collections.shuffle(pictures);
        return pictures;

    }
}