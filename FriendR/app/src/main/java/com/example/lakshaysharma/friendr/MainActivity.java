package com.example.lakshaysharma.friendr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    /*private RatingBar[] RatingBars = new RatingBar[6];
    private ImageButton[] Images = new ImageButton[6];
    private Float rating;

    private final int REQ_CODE = 1234;
    private static String tag;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*RatingBars[0] = findViewById(R.id.chandlerRatingBar);
        RatingBars[1] = findViewById(R.id.joeyRatingBar);
        RatingBars[2] = findViewById(R.id.monicaRatingBar);
        RatingBars[3] = findViewById(R.id.phoebeRatingBar);
        RatingBars[4] = findViewById(R.id.rachelRatingBar);
        RatingBars[5] = findViewById(R.id.rossRatingBar);

        Images[0] = findViewById(R.id.chandlerImage);
        Images[1] = findViewById(R.id.joeyImage);
        Images[2] = findViewById(R.id.monicaImage);
        Images[3] = findViewById(R.id.phoebeImage);
        Images[4] = findViewById(R.id.rachelImage);
        Images[5] = findViewById(R.id.rossImage);

        setImageButtonListeners();*/

    }

    /*public void setImageButtonListeners(){

        for (int i=0; i<Images.length; i++){
            final int finalI = i;

            Images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tag = Images[finalI].getTag().toString();

                    Intent intent = new Intent(MainActivity.this, Details.class);
                    intent.putExtra("Tag", tag);
                    startActivityForResult(intent, REQ_CODE);
                }
            });

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

            for (int i=0; i<Images.length; i++ ){
                String getRating = Images[i].getTag().toString();
                rating = prefs.getFloat(getRating, 0);
                RatingBars[i].setRating(rating);
            }

    }*/
}
