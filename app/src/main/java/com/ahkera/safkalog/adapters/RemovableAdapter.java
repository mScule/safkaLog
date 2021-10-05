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
 * @author Vilhelm
 */
public class RemovableAdapter extends RecyclerView.Adapter<RemovableAdapter.ContactHolder> {

    private OnRemovableListener mOnRemovableListener;
    private ArrayList<?> removables;
    private Context context;

    public RemovableAdapter(ArrayList<?> removables, Context context, OnRemovableListener onRemovableListener) {
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

            Eatable removableEatable = (Eatable) removable;

            inflater.inflate(R.layout.removable_eatable, holder.removable);

            TextView
                removableType = holder.removable.findViewById(R.id.removable_eatable_tv_type),
                removableName = holder.removable.findViewById(R.id.removable_eatable_tv_name),
                removableKcal = holder.removable.findViewById(R.id.removable_eatable_tv_kcal);

            // Assigning type
            if (removableEatable instanceof Ingredient) {
                removableType.setText(
                    context.getString(R.string.sh_em_food) + '/' +
                    context.getString(R.string.sh_em_drink)
                );
            } else if (removableEatable instanceof Recipe) {
                removableType.setText(context.getString(R.string.sh_em_recipe));
            }

            removableName.setText(removableEatable.getName());
            removableKcal.setText(Integer.toString(removableEatable.getKcal()));

        } else if (removable instanceof EatableUnit) {

            EatableUnit removableEatableUnit = (EatableUnit) removable;
            Log.d("RemovableAdapter", "EatableUnit not yet supported");

        } else if (removable instanceof DiaryLog) {

            DiaryLog removableDiaryLog = (DiaryLog) removable;
            EatableUnit removableEatableUnit = removableDiaryLog.getEatableUnit();

            inflater.inflate(R.layout.removable_diary_log, holder.removable);

            TextView
                removableName  = holder.removable.findViewById(R.id.removable_diaryLog_tv_eatableName),
                removableGrams = holder.removable.findViewById(R.id.removable_diaryLog_tv_eatableKcal),
                removableTime  = holder.removable.findViewById(R.id.removable_diaryLog_tv_logTime);

            removableName.setText(removableEatableUnit.getName());
            removableGrams.setText(Integer.toString(removableEatableUnit.getKcal()));
            removableTime.setText(removableDiaryLog.getTime());

        } else if (removable instanceof DiaryDate) {

            DiaryDate removableDiaryDate = (DiaryDate) removable;
            Log.d("RemovableAdapter", "DiaryDate not yet supported");

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
