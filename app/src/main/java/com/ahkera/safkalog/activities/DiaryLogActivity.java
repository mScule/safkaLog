package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.DiaryLogAdapter;
import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;

import java.util.ArrayList;

/**
 * Shows the contents of single diary date. The information inside these logs cannot be modified.
 * @author Vilhelm
 */
public class DiaryLogActivity extends AppCompatActivity {

    public static final String DIARY_DATE_INDEX =
        "com.ahkera.safkalog.activities.DiaryLogActivity.DIARY_DATE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_log);

        RecyclerView logsViewer = findViewById(R.id.ac_diaryLog_rv_diaryLogs);

        Bundle extras = getIntent().getExtras();
        int diaryDate = extras.getInt(DIARY_DATE_INDEX);
        ArrayList<DiaryLog> logs = GlobalInstance.getInstance().dates.get(diaryDate).getLogs();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        logsViewer.setLayoutManager(layoutManager);
        logsViewer.setAdapter(new DiaryLogAdapter(logs, this));
    }
}