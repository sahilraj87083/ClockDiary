package com.example.clockdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.resources.CancelableFontCallback;

import java.util.ArrayList;

public class AllAlarms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alarms);

        ArrayList<String> list = new ArrayList<>();
        list.add("asjda");
        list.add("fefef");
        list.add("3434");
        list.add("htth");
        list.add("lmm");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, list.get(i), Toast.LENGTH_SHORT).show();
        });

//        findViewById(R.id.allalarmbackbtn).setOnClickListener(view -> {
////            Toast.makeText(this, "hiiiii", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(this,Alarm.class);
//            startActivity(intent);
//
//        });


    }
}