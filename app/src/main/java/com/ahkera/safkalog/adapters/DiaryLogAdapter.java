package com.ahkera.safkalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.diary.DiaryLog;

import java.util.ArrayList;

/**
 * Custom RecycleView Adapter for the DiaryLog objects.
 * @author Vilhelm
 */
public class DiaryLogAdapter extends RecyclerView.Adapter<DiaryLogAdapter.ContactHolder> {

    private ArrayList<DiaryLog> diaryLogs;
    private Context context;

    public DiaryLogAdapter(ArrayList<DiaryLog> diaryLogs, Context context) {
        this.diaryLogs = diaryLogs;
        this.context   = context;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_diary_log, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        final DiaryLog diaryLog = diaryLogs.get(position);

        holder.setEatableName(diaryLog.getEatableUnit().getName());
        holder.setEatableKcal(Integer.toString(diaryLog.getEatableUnit().getKcal()));
        holder.setLogTime(diaryLog.getTime());
    }

    @Override
    public int getItemCount() {
        return diaryLogs == null ? 0 : diaryLogs.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        private TextView eatableName, eatableKcal, logTime;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);

            eatableName = itemView.findViewById(R.id.it_diaryLog_tv_eatableName);
            eatableKcal = itemView.findViewById(R.id.it_diaryLog_tv_eatableKcal);
            logTime     = itemView.findViewById(R.id.it_diaryLog_tv_logTime);
        }

        public void setEatableName(String eatableName) {
            this.eatableName.setText(eatableName);
        }
        public void setEatableKcal(String eatableKcal) {
            this.eatableKcal.setText(eatableKcal);
        }

        public void setLogTime(String logTime) {
            this.logTime.setText(logTime);
        }
    }
}
