package com.example.lakshaysharma.dictionaryaddedfunctionality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.PrintStream;

public class AddNewWord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

    }

    public void addWord(View view) {

        EditText edit1 = findViewById(R.id.newWord);
        EditText edit2 = findViewById(R.id.newDefn);

        String newWord = edit1.getText().toString();
        String newDefn = edit2.getText().toString();

        try {
            PrintStream out = new PrintStream(openFileOutput("new_words.txt", MODE_APPEND));
            out.println(newWord + "\t" + newDefn);
            out.close();
        } catch (IOException ex){
            //oh well!!
        }

        Intent goBack = new Intent();
        goBack.putExtra("newWord", newWord);
        setResult(RESULT_OK, goBack);
        finish();

    }
}
