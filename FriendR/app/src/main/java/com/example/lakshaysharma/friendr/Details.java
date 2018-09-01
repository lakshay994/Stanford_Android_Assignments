package com.example.lakshaysharma.friendr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    /*private ImageView image;
    private TextView personName;
    private TextView personDetail;
    private RatingBar ratings;

    private static float RATING;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //startActivity();

    }

    /*public void startActivity(){

        image = findViewById(R.id.personImage);
        personName = findViewById(R.id.personName);
        personDetail = findViewById(R.id.personDetails);
        ratings = findViewById(R.id.rating);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("Tag");

        setDetails(tag);
        setRating(tag);

    }

    private void setDetails(String tag){

        int ImgID = getResources().getIdentifier(tag.toLowerCase(), "drawable", getPackageName());
        String[] names = getResources().getStringArray(R.array.friend_full_names);
        String[] details = getResources().getStringArray(R.array.friend_details);

        for (int i = 0; i<names.length; i++){

            String[] nameSplit = names[i].split(" ");
            if (nameSplit[0].toLowerCase().equals(tag)){
                personName.setText(names[i]);
                personDetail.setText(details[i]);
                image.setImageResource(ImgID);
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                Float rating = prefs.getFloat(nameSplit[0].toLowerCase(), 0);
                ratings.setRating(rating);
            }

        }

    }

    private void setRating(String tag){

        final String fTag = tag;
        ratings.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                RATING = ratings.getRating();
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor prefsEdit = prefs.edit();
                prefsEdit.putFloat(fTag, RATING);
                prefsEdit.apply();
            }
        });
    }*/

}
