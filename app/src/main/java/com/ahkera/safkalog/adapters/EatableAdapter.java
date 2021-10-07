package com.ahkera.safkalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.consumable.Consumable;
import com.ahkera.safkalog.consumable.Ingredient;
import com.ahkera.safkalog.consumable.Recipe;

import java.util.ArrayList;

/**
 * Custom RecycleView Adapter for Consumable objects.
 * @author Vilhelm
 */
public class EatableAdapter extends RecyclerView.Adapter<EatableAdapter.ContactHolder> {

    private OnEatableListener mOnEatableListener;
    private ArrayList<Consumable> consumables;
    private Context context;

    public EatableAdapter(ArrayList<Consumable> consumables, Context context, OnEatableListener mOnEatableListener) {
        this.consumables = consumables;
        this.context  = context;
        this.mOnEatableListener = mOnEatableListener;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_eatable, parent, false);
        return new ContactHolder(view, mOnEatableListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        final Consumable consumable = consumables.get(position);

        // The emojis, that are being shown under the type label
        // Ingredient: Donut '/' Coffee
        // Recipe:     Scroll
        final String
            typeIngredient =
                context.getString(R.string.sh_em_food) + '/' +
                context.getString(R.string.sh_em_drink),

            typeRecipe =
                context.getString(R.string.sh_em_recipe);

        String type = "";

        // Check the consumable type
        if (consumable instanceof Ingredient)
            type = typeIngredient;

        else if (consumable instanceof Recipe)
            type = typeRecipe;

        holder.setEatableType(type);
        holder.setEatableName(consumable.getName());
    }

    @Override
    public int getItemCount() {
        return consumables == null ? 0 : consumables.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView eatableType, eatableName;
        private OnEatableListener onEatableListener;

        public ContactHolder(@NonNull View itemView, OnEatableListener onEatableListener) {
            super(itemView);

            eatableType = itemView.findViewById(R.id.it_eatable_tv_eatableType);
            eatableName = itemView.findViewById(R.id.it_eatable_tv_eatableName);
            this.onEatableListener = onEatableListener;

            itemView.setOnClickListener(this);
        }

        public void setEatableType(String eatableType) {
            this.eatableType.setText(eatableType);
        }
        public void setEatableName(String eatableName) {
            this.eatableName.setText(eatableName);
        }

        @Override
        public void onClick(View view) {
            onEatableListener.onEatableClick(getAdapterPosition());
        }
    }

    public interface OnEatableListener {
        void onEatableClick(int position);
    }
}
