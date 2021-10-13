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
public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ContactHolder> {

    private OnConsumableListener mOnConsumableListener;
    private ArrayList<Consumable> consumables;
    private Context context;

    public ConsumableAdapter(ArrayList<Consumable> consumables, Context context, OnConsumableListener mOnConsumableListener) {
        this.consumables = consumables;
        this.context  = context;
        this.mOnConsumableListener = mOnConsumableListener;
    }

    /** The method that inflates the layout from the contactholder */
    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_eatable, parent, false);
        return new ContactHolder(view, mOnConsumableListener);
    }

    /***
     * Binds the given information to the contact holder layout
     * @param holder the contactholder that holds the custom layout
     * @param position the location of the item in the arraylist
     */
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

    /** Gets the item count */
    @Override
    public int getItemCount() {
        return consumables == null ? 0 : consumables.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView eatableType, eatableName;
        private OnConsumableListener onConsumableListener;

        /** binds the click event and views */
        public ContactHolder(@NonNull View itemView, OnConsumableListener onConsumableListener) {
            super(itemView);

            eatableType = itemView.findViewById(R.id.it_eatable_tv_eatableType);
            eatableName = itemView.findViewById(R.id.it_eatable_tv_eatableName);
            this.onConsumableListener = onConsumableListener;

            itemView.setOnClickListener(this);
        }

        /** Setter for the type */
        public void setEatableType(String eatableType) {
            this.eatableType.setText(eatableType);
        }

        /** Setter for the name */
        public void setEatableName(String eatableName) {
            this.eatableName.setText(eatableName);
        }

        @Override
        public void onClick(View view) {
            onConsumableListener.onConsumableClick(getAdapterPosition());
        }
    }

    /** Interface for giving the change of creating custom click events */
    public interface OnConsumableListener {
        void onConsumableClick(int position);
    }
}
