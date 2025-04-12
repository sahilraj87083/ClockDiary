package com.example.clockdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    TextView clockdiary;
    LottieAnimationView lottie1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        clockdiary=findViewById(R.id.clockdiary);
        lottie1 =findViewById(R.id.lottie1);

        clockdiary.animate().translationY(-200).setDuration(2200).setStartDelay(0);
        lottie1.animate().translationX(2000).setDuration(2200).setStartDelay(2200);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        },3450);

    }
}