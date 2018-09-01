package com.example.lakshaysharma.dictionarygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    private static final int REQ_CODE_ADD_WORD = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void playTheGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addWord(View view) {
        Intent intent = new Intent(this, AddNewWord.class);
        startActivityForResult(intent, REQ_CODE_ADD_WORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == REQ_CODE_ADD_WORD){
            String newWord = intent.getStringExtra("newWord");
            String newMeaning = intent.getStringExtra("newMeaning");

            Toast.makeText(MainMenu.this, newWord + " Added!", Toast.LENGTH_LONG).show();
        }
    }
}
