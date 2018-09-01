package com.example.lakshaysharma.dictionarygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintStream;

public class AddNewWord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
    }

    public void addNewWord(View view) {

        TextView textView = findViewById(R.id.newWord);
        String newWord = textView.getText().toString();
        TextView textView1 = findViewById(R.id.newMeaning);
        String newMeaning = textView1.getText().toString();

        //open new file
        try {
            PrintStream file = new PrintStream(openFileOutput("new_words.txt", MODE_PRIVATE | MODE_APPEND));
            file.println(newWord + "\t" + newMeaning);
            file.close();
        } catch (IOException e){
            //do nothing
        }

        Intent goBack = new Intent();
        goBack.putExtra("newWord", newWord);
        goBack.putExtra("newMeaning", newMeaning);
        setResult(RESULT_OK, goBack);
        finish();

    }
}
