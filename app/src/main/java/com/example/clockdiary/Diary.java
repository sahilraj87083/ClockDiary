package com.example.clockdiary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class Diary extends AppCompatActivity {
    FloatingActionButton mcreatenotesfab;
    private FirebaseAuth firebaseAuth;
    private Firebase firebase;

    RecyclerView mrecyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

//    FirestoreRecyclerAdapter<firebasemodel,NoteViewHolder> noteAdapter;



//    implementation 'org.apache.poi:poi-ooxml:4.1.2'
//    implementation 'com.firebaseui:firebase-ui-firestore:4.1.2'
//    implementation 'javax.xml.stream:stax-api:1.0'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);


        mcreatenotesfab=(FloatingActionButton) findViewById(R.id.createnotefab);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();

//        getSupportActionBar().setTitle("All Notes");
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//
//            // Set the title of the ActionBar
//            actionBar.setTitle("All Notes");
//
//            // Set the subtitle of the ActionBar
////            actionBar.setSubtitle("My App Subtitle");
//
//            // Set the display options for the ActionBar
////            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        mcreatenotesfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencreatenote();}
        });

    }
    public void opencreatenote() {
        Intent intent=new Intent(this,createnote.class);
        startActivity(intent);
    }
}