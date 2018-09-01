package com.example.lakshaysharma.friendr;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class DetailsFragment extends Fragment {

    private ImageView image;
    private TextView personName;
    private TextView personDetail;
    private RatingBar ratings;

    private static float RATING;

    Activity ACTIVITY;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ACTIVITY = getActivity();
        Intent intent = ACTIVITY.getIntent();
        String tag = intent.getStringExtra("Tag");
        startActivity(tag);

    }

    public void startActivity(String tag){

        image = ACTIVITY.findViewById(R.id.personImage);
        personName = ACTIVITY.findViewById(R.id.personName);
        personDetail = ACTIVITY.findViewById(R.id.personDetails);
        ratings = ACTIVITY.findViewById(R.id.rating);


        setDetails(tag);
        setRating(tag);

    }

    public void setDetails(String tag){

        if (tag == null){
            tag = "chandler";
        }
        else{
            int ImgID = ACTIVITY.getResources().getIdentifier(tag.toLowerCase(), "drawable", ACTIVITY.getPackageName());
            String[] names = ACTIVITY.getResources().getStringArray(R.array.friend_full_names);
            String[] details = ACTIVITY.getResources().getStringArray(R.array.friend_details);

            for (int i = 0; i<names.length; i++){

                String[] nameSplit = names[i].split(" ");
                if (nameSplit[0].toLowerCase().equals(tag)){
                    personName.setText(names[i]);
                    personDetail.setText(details[i]);
                    image.setImageResource(ImgID);
                    SharedPreferences prefs = ACTIVITY.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    Float rating = prefs.getFloat(tag, 0);
                    ratings.setRating(rating);
                }

            }
        }

    }

    public void setRating(String tag){

        final String tag1 = tag;
        ratings.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                RATING = ratings.getRating();
                SharedPreferences prefs = ACTIVITY.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEdit = prefs.edit();
                prefsEdit.putFloat(tag1, RATING);
                prefsEdit.apply();
            }
        });
    }
}
