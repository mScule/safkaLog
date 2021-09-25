package com.ahkera.safkalog.global;

import android.util.Log;

import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.eatable.Ingredient;

import java.util.ArrayList;

public class Global {
    public DiaryDate diaryDateToday;

    private static Global global = new Global();

    public static Global getInstance() { return global; }

    private Global() {
        diaryDateToday = new DiaryDate();
        testDiaryLogToday();
    }

    private void testDiaryLogToday() {
        diaryDateToday.addLog(new DiaryLog(new Ingredient("food1", 123)));
        diaryDateToday.addLog(new DiaryLog(new Ingredient("food2", 234)));
        diaryDateToday.addLog(new DiaryLog(new Ingredient("food3", 345)));
        diaryDateToday.addLog(new DiaryLog(new Ingredient("food5", 565)));
        diaryDateToday.addLog(new DiaryLog(new Ingredient("food8", 233)));

        for(DiaryLog log : diaryDateToday.getLogs())
            Log.d("DiaryLog", log.toString());
    }
}
