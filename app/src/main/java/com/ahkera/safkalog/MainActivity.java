package com.ahkera.safkalog;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.global.Global;

public class MainActivity extends AppCompatActivity {

    private LinearLayout labelDiaryLogToday, containerDiaryLogToday;
    private TextView valueTodayTotalKcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueTodayTotalKcal = findViewById(R.id.value_today_total_kcal);

        valueTodayTotalKcal.setText(
                Integer.toString(Global.getInstance().diaryDateToday.getKcalTotal())
        );

        // Show or hide the today's diary log widgets
        labelDiaryLogToday     = findViewById(R.id.label_diary_log_today);
        containerDiaryLogToday = findViewById(R.id.container_diary_log_today);

        if(Global.getInstance().diaryDateToday.getLogs().isEmpty()) {
            labelDiaryLogToday.setVisibility(INVISIBLE);
            containerDiaryLogToday.setVisibility(INVISIBLE);
        } else {

            // Creating today's diary log rows
            for(DiaryLog log : Global.getInstance().diaryDateToday.getLogs()) {
                getLayoutInflater().inflate(R.layout.row_diary_log, containerDiaryLogToday);

                View logRow = getLayoutInflater().inflate(R.layout.row_diary_log, null);

                TextView
                    name = logRow.findViewById(R.id.row_diary_log_name),
                    kcal = logRow.findViewById(R.id.row_diary_log_kcal),
                    time = logRow.findViewById(R.id.row_diary_log_time);

                name.setText(log.getIngredient().getName());
                kcal.setText(Integer.toString(log.getIngredient().getKcal()));
                time.setText(log.getTime());

                containerDiaryLogToday.addView(logRow);
            }
        }
    }


}