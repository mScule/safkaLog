package com.ahkera.safkalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.diary.DiaryDate;

import java.util.ArrayList;

/**
 * Custom RecycleView Adapter for DiaryDate objects.
 * @author Vilhelm
 */
public class DiaryDateAdapter extends RecyclerView.Adapter<DiaryDateAdapter.ContactHolder> {

    private OnDiaryDateListener mOnDiaryDateListener;
    private ArrayList<DiaryDate> diaryDates;
    private Context context;

    public DiaryDateAdapter(ArrayList<DiaryDate> diaryDates, Context context, OnDiaryDateListener mOnDiaryDateListener) {
        this.diaryDates = diaryDates;
        this.context = context;
        this.mOnDiaryDateListener = mOnDiaryDateListener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_diary_date, parent, false);
        return new ContactHolder(view, mOnDiaryDateListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryDateAdapter.ContactHolder holder, int position) {
        final DiaryDate diaryDate = diaryDates.get(position);

        holder.setDiaryDateDate(diaryDate.getDate());
        holder.setDiaryDateKcalTotal(Integer.toString(diaryDate.getKcalTotal()));
    }

    @Override
    public int getItemCount() { return diaryDates == null ? 0 : diaryDates.size(); }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView diaryDateDate, diaryDateKcalTotal;
        private OnDiaryDateListener onDiaryDateListener;

        public ContactHolder(@NonNull View itemView, OnDiaryDateListener onDiaryDateListener) {
            super(itemView);

            diaryDateDate = itemView.findViewById(R.id.it_diaryDate_tv_date);
            diaryDateKcalTotal = itemView.findViewById(R.id.it_diaryDate_tv_kcalTotal);
            this.onDiaryDateListener = onDiaryDateListener;

            itemView.setOnClickListener(this);
        }

        public void setDiaryDateDate(String date) { this.diaryDateDate.setText(date); }
        public void setDiaryDateKcalTotal(String date) { this.diaryDateKcalTotal.setText(date); }

        @Override
        public void onClick(View view) {
            onDiaryDateListener.onDiaryDateClick(getAdapterPosition());
        }
    }

    public interface OnDiaryDateListener {
        void onDiaryDateClick(int position);
    }
}
