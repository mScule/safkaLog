package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.DiaryDateAdapter;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;

/**
 * Access menu that the user uses for accessing the DiaryDates from the diary, for inspection.
 * @author Vilhelm
 */
public class DiaryActivity extends AppCompatActivity implements DiaryDateAdapter.OnDiaryDateListener {

    private RecyclerView diaryDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        diaryDates = findViewById(R.id.ac_diary_rv_diaryDates);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        diaryDates.setLayoutManager(layoutManager);
        diaryDates.setAdapter(
            new DiaryDateAdapter(GlobalInstance.getInstance().dates, this, this::onDiaryDateClick)
        );
    }

    @Override
    public void onDiaryDateClick(int position) {
        Intent diaryDateIntent = new Intent(this, DiaryLogActivity.class);
        diaryDateIntent.putExtra(DiaryLogActivity.DIARY_DATE_INDEX, position);
        startActivity(diaryDateIntent);
    }
}