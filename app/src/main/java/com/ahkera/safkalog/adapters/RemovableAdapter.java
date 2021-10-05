package com.ahkera.safkalog.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.eatable.Eatable;
import com.ahkera.safkalog.eatable.EatableUnit;
import com.ahkera.safkalog.eatable.Ingredient;
import com.ahkera.safkalog.eatable.Recipe;

import java.util.ArrayList;

/**
 * Custom adapter for item removers. It's used by edit activity, and recipes activity
 *
 * ArrayList can contain Eatable, EatableUnit, DiaryLog, and DiaryDate
 *
 * @author Vilhelm
 */
public class RemovableAdapter extends RecyclerView.Adapter<RemovableAdapter.ContactHolder> {

    private OnRemovableListener mOnRemovableListener;
    private ArrayList<?> removables;
    private Context context;

    public RemovableAdapter(
        ArrayList<?> removables,
        Context context,
        OnRemovableListener onRemovableListener
    ) {
        this.removables = removables;
        this.context    = context;
        this.mOnRemovableListener = onRemovableListener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_removable, parent, false);
        return new ContactHolder(view, mOnRemovableListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        final Object removable = removables.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);

        // Checking the type of the removable
        if (removable instanceof Eatable) {

            Eatable eatable = (Eatable) removable;

            inflater.inflate(R.layout.removable_eatable, holder.removable);

            TextView
                type = holder.removable.findViewById(R.id.removable_eatable_tv_type),
                name = holder.removable.findViewById(R.id.removable_eatable_tv_name),
                kcal = holder.removable.findViewById(R.id.removable_eatable_tv_kcal);

            // Assigning type
            if (eatable instanceof Ingredient) {
                type.setText(
                    context.getString(R.string.sh_em_food) + '/' +
                    context.getString(R.string.sh_em_drink)
                );
            } else if (eatable instanceof Recipe) {
                type.setText(context.getString(R.string.sh_em_recipe));
            }

            name.setText(eatable.getName());
            kcal.setText(Integer.toString(eatable.getKcal()));

        } else if (removable instanceof EatableUnit) {

            EatableUnit eatableUnit = (EatableUnit) removable;

            inflater.inflate(R.layout.removable_eatable_unit, holder.removable);

            TextView
                name  = holder.removable.findViewById(R.id.removable_eatableUnit_tv_name),
                grams = holder.removable.findViewById(R.id.removable_eatableUnit_tv_grams);

            name.setText(eatableUnit.getName());
            grams.setText(eatableUnit.getGrams());

        } else if (removable instanceof DiaryLog) {

            DiaryLog diaryLog = (DiaryLog) removable;
            EatableUnit removableEatableUnit = diaryLog.getEatableUnit();

            inflater.inflate(R.layout.removable_diary_log, holder.removable);

            TextView
                name  = holder.removable.findViewById(R.id.removable_diaryLog_tv_eatableName),
                grams = holder.removable.findViewById(R.id.removable_diaryLog_tv_eatableKcal),
                time  = holder.removable.findViewById(R.id.removable_diaryLog_tv_logTime);

            name.setText(removableEatableUnit.getName());
            grams.setText(Integer.toString(removableEatableUnit.getKcal()));
            time.setText(diaryLog.getTime());

        } else if (removable instanceof DiaryDate) {

            DiaryDate diaryDate = (DiaryDate) removable;
            Log.d("RemovableAdapter", "DiaryDate not yet supported");

            inflater.inflate(R.layout.removable_diary_date, holder.removable);

            TextView
                date = holder.removable.findViewById(R.id.removable_diaryDate_tv_date),
                kcal = holder.removable.findViewById(R.id.removable_diaryDate_tv_kcalTotal);

            date.setText(diaryDate.getDate());
            kcal.setText(Integer.toString(diaryDate.getKcalTotal()));

        } else {
            Log.e("RemovableAdapter", "Unsupported Element in the given ArrayList");
        }
    }

    @Override
    public int getItemCount() { return removables == null ? 0 : removables.size(); }

    public class ContactHolder extends RecyclerView.ViewHolder {
        public LinearLayout removable;
        private Button   removeButton;

        public ContactHolder(@NonNull View itemView, OnRemovableListener onRemovableListener) {
            super(itemView);

            removable    = itemView.findViewById(R.id.it_removable_ll_removable);
            removeButton = itemView.findViewById(R.id.it_removable_btn_removeButton);

            this.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRemovableListener.onRemovableClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnRemovableListener {
        void onRemovableClick(int position);
    }
}
