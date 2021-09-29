package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ahkera.safkalog.R;

public class EditActivity extends AppCompatActivity {

    public static final int
        MODE_EAT         = 0,
        MODE_INGREDIENTS = 1,
        MODE_RECIPES     = 2,
        MODE_DIARY       = 3;

    public static final String EDIT_MODE =
        "com.ahkera.safkalog.activities.EditActivity.EDIT_ACTIVITY";

    private int editMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle extras = getIntent().getExtras();

        editMode = extras.getInt(EDIT_MODE);

        switch(editMode) {
            case MODE_EAT:
                break;
            case MODE_INGREDIENTS:
                break;
            case MODE_RECIPES:
                break;
            case MODE_DIARY:
                break;
        }
    }
}