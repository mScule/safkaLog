package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.global.SaveStateManager;

public class ConsumptionLimitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_limit);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SaveStateManager.saveState(this);
    }
}