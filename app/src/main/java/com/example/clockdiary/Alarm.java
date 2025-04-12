package com.example.clockdiary;
import java.util.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clockdiary.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class Alarm extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MaterialTimePicker picker;
    private  Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private EditText editText;
    TextToSpeech textToSpeech;
    public static String reason;

    String reasonfromtyping="";
    String reasonfromspeaking="";
    String speakingtext;
    String time="New World Started";
    TextView textViewreason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding=ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_alarm);
        createNotificationChannel();


//        View view1=binding.getRoot();
//        setContentView(view1);

//        binding.SelectedTime.setOnClickListener(new View.OnClickListener(){
//            @Override
//                    public void onClick(View v){
//
//            }
//        });
//        binding.setAlarmBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//            }
//        });
//        binding.CancelAlarmBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//            }
//        });   bin

        findViewById(R.id.selectTimebtn).setOnClickListener(view -> {
            speaking("Please Select Time in 12 hour Format"+"                                                              "+" Also Enter Why do you want to set Alarm?");
//            Toast.makeText(this, "set alarm", Toast.LENGTH_SHORT).show();
//            @Override
//                    public void onClick(View v
            time=showTimePicker();
//            speaking("Why do you want to set the Alarm?");


//            textToSpeech.speak("Why do you want to set Alarm?",TextToSpeech.QUEUE_FLUSH,null);

//            }


        });
        textViewreason=findViewById(R.id.reasonofalarm);
        findViewById(R.id.setAlarmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setAlarm();
            }
        });

        findViewById(R.id.imagebtnforreason).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speaknow(view);
            }
        });

        findViewById(R.id.setreason).setOnClickListener(view -> {
            setreasontostring();
            if(reasonfromspeaking.length()!=0){
                reason=reasonfromspeaking;
                speakingtext="The Reason you spoke for Alarm is "+reasonfromspeaking;
            } else if (reasonfromtyping.length()!=0) {
                reason=reasonfromtyping;
                speakingtext="The Reason you entered for Alarm is "+reason;
            }
            if(reason.length()!=0){
                textViewreason.setText("The reason you entered for Alarm is "+reason+".");
                speaking(speakingtext);
            }else{
                speaking("Please either enter or speak why do you want to set Alarm?");
            }
//            textToSpeech.speak(reason , TextToSpeech.QUEUE_FLUSH,null);
            Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
            reason="";
            reasonfromspeaking="";
            reasonfromtyping="";
        });

        findViewById(R.id.CancelAlarmBtn).setOnClickListener(view -> {
//            @Override
//                    public void onClick (View view){
                cancelAlarm();
//            }

        });


    }

    private void setreasontostring() {
//        Toast.makeText(this, "hlw", Toast.LENGTH_SHORT).show();
        EditText text=(EditText) findViewById(R.id.reasontyped);


         reasonfromtyping=text.getText().toString().toLowerCase();

    }

    private void cancelAlarm() {
//        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
        if(alarmManager==null){
            alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
        speaking("Alarm Cancelled!");
    }

    private void setAlarm() {

//        Toast.makeText(this, "In set alarm function", Toast.LENGTH_SHORT).show();
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent=new Intent(this, AlarmReceiver.class);


//        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_MUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
        speaking("Alarm set Successfully");

    }

    private String showTimePicker() {
        String selectedTime="";
            picker=new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Select Alarm Time")
                    .build();
            picker.show(getSupportFragmentManager(),"Alarm_Ringing");

            picker.addOnPositiveButtonClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int hr = picker.getHour();
                    int mint = picker.getMinute();
                    String ampm = "AM";
                    String mins=String.valueOf(mint);
                    if(hr >= 12){
                            ampm="PM";
                        if(hr>12){
                            hr-=12;
                        }
                    }else{
                        if(hr==0){
                            hr=12;
                        }
                        ampm="AM";

                    }
                    String hrs=String.valueOf(hr);
                    if(hr<10){
                        hrs="0"+hr;
                    }
                    if(mint<10){
                         mins="0"+mint;
                    }
                    String selectedTime = hrs+":"+mins+" "+ampm;


                    TextView selectedTimeView = findViewById(R.id.selectedTime);
                    selectedTimeView.setText(selectedTime);


                    calendar =Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                    calendar.set(Calendar.MINUTE,picker.getMinute());
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);

                }

            });
        return selectedTime;
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name="REMAINDERCHANNEL";
            String description="Channel for Alarm Manager";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel("Alarm_Ringing",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void speaking(String text){
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i !=TextToSpeech.ERROR)
                    textToSpeech.setLanguage(Locale.ENGLISH);
                textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//
            }

            });


    }


    private void speaknow(View view){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Start speaking...");
        startActivityForResult(intent,111);

    }
//    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==111 && resultCode==RESULT_OK){
            String tempreason=(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
            reasonfromspeaking=tempreason.toLowerCase();
        }
    }
}