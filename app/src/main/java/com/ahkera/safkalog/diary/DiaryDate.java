package com.ahkera.safkalog.diary;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.ArrayList;

public class DiaryDate {
    private ArrayList<DiaryLog> logs;
    private Date date;
    private int kcalSum;

    public DiaryDate() {
        logs = new ArrayList();
        date = java.util.Calendar.getInstance().getTime();
        kcalSum = 0;
    }

    private void refreshKcalTotal() {
        kcalSum = 0;

        for(DiaryLog log : logs) {
            kcalSum += log.getIngredient().getKcal();
        }
    }

    public void addLog(DiaryLog log) {
        logs.add(log);
        refreshKcalTotal();
    }

    public void removeLog(int i) {
        logs.remove(i);
        refreshKcalTotal();
    }

    public ArrayList<DiaryLog> getLogs() { return logs; }

    public Date getDate() {
        return date;
    }

    public int getKcalTotal() {
        return kcalSum;
    }

    @NonNull
    @Override
    public String toString() {
        return date.toString() + " | " + getKcalTotal() + "kcal";
    }
}
