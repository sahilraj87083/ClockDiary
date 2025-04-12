package com.example.clockdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button performance;
    private Button alarmbutton;
    private Button diary;
    private Button dateandtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Clock Diary");
        performance=(Button) findViewById(R.id.performance);

        performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPerformance();
            }
        });



        alarmbutton =(Button) findViewById(R.id.alarmbutton);
        alarmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                Toast.makeText(MainActivity.this, "called", Toast.LENGTH_SHORT).show();
                openAlarm();
            }

        });
        diary=(Button) findViewById(R.id.diary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDiary();
            }
        });
        dateandtime = (Button) findViewById(R.id.dateandtime);
        dateandtime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                opendateandtime();
            }


        });
        }
    public void opendateandtime() {
        Intent intent=new Intent(this,Date_and_time.class);
        startActivity(intent);
    }
    public void openPerformance(){
        Intent intent=new Intent(this,Performance.class);
        startActivity(intent);
    }
    public void openAlarm(){
        Intent intent=new Intent(this,Alarm.class);
        startActivity(intent);
    }
    public void openDiary(){
        Intent intent=new Intent(this,Diary.class);
        startActivity(intent);
    }

}