package com.ahkera.safkalog.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryLogAdapter extends RecyclerView.Adapter<DiaryLogAdapter.ContactHolder>{

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
