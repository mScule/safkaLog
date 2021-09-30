package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ahkera.safkalog.R;

public class RecipesActivity extends AppCompatActivity {


    public static final String
            GRAMS = "com.ahkera.safkalog.activities.GramActivity.GRAM_ACTIVITY";

    private int grams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

    }
}