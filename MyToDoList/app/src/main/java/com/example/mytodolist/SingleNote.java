package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SingleNote extends AppCompatActivity {

    TextView title_v;
    TextView description_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_note);
        title_v=(TextView)findViewById(R.id.title_v);
        description_v=(TextView)findViewById(R.id.description_v);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                title_v.setText(extras.getString("title"));
                description_v.setText(extras.getString("desc"));
            }
        }
    }
}