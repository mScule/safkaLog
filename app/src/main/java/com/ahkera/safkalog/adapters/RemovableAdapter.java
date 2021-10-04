package com.ahkera.safkalog.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
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

        // Checking the type of the removable
        if (removable instanceof Eatable) {
            Eatable removableEatable = (Eatable) removable;

            TextView
                removableType = new TextView(context),
                removableName = new TextView(context),
                removableKcal = new TextView(context);

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

            holder.removable.addView(removableType);
            LayoutInflater.from(context).inflate(R.layout.item_space, holder.removable);

            holder.removable.addView(removableName);
            LayoutInflater.from(context).inflate(R.layout.item_space, holder.removable);

            holder.removable.addView(removableKcal);

        } else if (removable instanceof EatableUnit) {
            EatableUnit removableEatableUnit = (EatableUnit) removable;

            TextView
                removableName  = new TextView(context),
                removableGrams = new TextView(context);

            removableName.setText(removableEatableUnit.getName());
            removableGrams.setText(Integer.toString(removableEatableUnit.getGrams()));

            holder.removable.addView(removableName);
            //holder.removable.addView(space);
            holder.removable.addView(removableGrams);

        } else if (removable instanceof DiaryLog) {
            DiaryLog removableDiaryLog = (DiaryLog) removable;

            TextView
                removableName = new TextView(context),
                removableKcal = new TextView(context),
                removableTime = new TextView(context);

            removableName.setText(removableDiaryLog.getEatableUnit().getName());
            removableKcal.setText(Integer.toString(removableDiaryLog.getEatableUnit().getKcal()));
            removableTime.setText(removableDiaryLog.getTime());

            holder.removable.addView(removableName);
            //holder.removable.addView(space);
            holder.removable.addView(removableKcal);
            //holder.removable.addView(space);
            holder.removable.addView(removableTime);

        } else if (removable instanceof DiaryDate) {
            DiaryDate removableDiaryDate = (DiaryDate) removable;
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
