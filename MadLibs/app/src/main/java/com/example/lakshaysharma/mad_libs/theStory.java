package com.example.lakshaysharma.mad_libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class theStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_story);

        Intent intent = getIntent();
        String story = intent.getStringExtra("Story");

        TextView textView = findViewById(R.id.story);
        textView.setText(story);

    }
}
