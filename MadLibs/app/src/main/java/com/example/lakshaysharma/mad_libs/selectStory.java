package com.example.lakshaysharma.mad_libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_story);
    }

    public void setStory(View view) {

        Button button = (Button) view;
        String tag = button.getTag().toString();

        Intent intent = new Intent(selectStory.this, fillTheWords.class);
        intent.putExtra("Tag", tag);
        startActivity(intent);

    }
}
