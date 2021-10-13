package com.ahkera.safkalog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.consumable.Recipe;

import java.util.ArrayList;

/**
 * Custom RecyclerView Adapter for ConsumableUnits
 * @author Vilhelm
 */
public class ConsumableUnitAdapter extends RecyclerView.Adapter<ConsumableUnitAdapter.ContactHolder> {

    private ArrayList<ConsumableUnit> consumableUnits;
    private Context context;

    public ConsumableUnitAdapter(Recipe recipe, Context context) {
        this.consumableUnits = recipe.getIngredients();
        this.context = context;
    }

    /** The method that inflates the layout from the contactholder */
    @NonNull
    @Override
    public ConsumableUnitAdapter.ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_consumable_unit, parent, false);
        return new ContactHolder(view);
    }


    /***
     * Binds the given information to the contact holder layout
     * @param holder the contactholder that holds the custom layout
     * @param position the location of the item in the arraylist
     */
    @Override
    public void onBindViewHolder(@NonNull ConsumableUnitAdapter.ContactHolder holder, int position) {
        final ConsumableUnit consumable = consumableUnits.get(position);

        holder.setIngredientName(consumable.getName());
        holder.setIngredientGrams(Integer.toString(consumable.getGrams()));
    }

    /** Gets the item count */
    @Override
    public int getItemCount() {
        return consumableUnits == null ? 0 : consumableUnits.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        private TextView ingredientName, ingredientGrams;

        /** binds the views */
        public ContactHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.it_recipeBookRecipe_tv_name);
            ingredientGrams = itemView.findViewById(R.id.it_recipeBookRecipe_tv_grams);
        }

        /** Setter for the name */
        public void setIngredientName(String ingredientName) {
            this.ingredientName.setText(ingredientName);
        }

        /** Setter for the grams */
        public void setIngredientGrams(String ingredientGrams) {
            this.ingredientGrams.setText(ingredientGrams);
        }
    }
}
