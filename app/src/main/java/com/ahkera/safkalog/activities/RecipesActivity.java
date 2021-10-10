package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.EatableAdapter;
import com.ahkera.safkalog.adapters.RemovableAdapter;
import com.ahkera.safkalog.consumable.Consumable;
import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.consumable.Recipe;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;
import com.ahkera.safkalog.util.Alert;

/** @author Konsta
 *
 * You can add ingredients to recipe by giving how many grams u want to add and pressing the ingredient.
 * after that the ingredient you picked shows up in current ingredients
 * You can name the recipe and click finish recipe and you have a recipe.
 */

public class RecipesActivity extends AppCompatActivity implements EatableAdapter.OnEatableListener, RemovableAdapter.OnRemovableListener {

    private RecyclerView eatables;
    private RecyclerView currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        updateRecycleViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveStateManager.saveState(this);
    }

    public void updateRecycleViews() {
        //Upper RecyclerView
        eatables = findViewById(R.id.ac_ingredientsCatalog_rv);

        LinearLayoutManager layoutManagerUpper = new LinearLayoutManager(this);
        eatables.setLayoutManager(layoutManagerUpper);
        eatables.setAdapter(new EatableAdapter(GlobalInstance.getInstance().consumables, this, this::onEatableClick));

        //Lower RecyclerView
        currentRecipe = findViewById(R.id.ac_currentIngredients_rv);

        LinearLayoutManager layoutManagerLower = new LinearLayoutManager(this);
        currentRecipe.setLayoutManager(layoutManagerLower);
        currentRecipe.setAdapter(new RemovableAdapter(GlobalInstance.getInstance().currentRecipe, this, this::onRemovableClick));
    }

    private void add(Consumable consumable, int grams) {

        GlobalInstance.getInstance().currentRecipe.add(new ConsumableUnit(consumable, grams));
        // Adding ingredients alert
        Alert.show(
                this,
                grams + " grams of \"" + consumable.getName() + "\"" + " added to current ingredients.",
                "Ok"
        );
    }

    @Override
    public void onEatableClick(int position) {
        EditText etGrams = findViewById(R.id.ac_recipes_et_inputGrams);

        if (etGrams.getText().toString() != null && !etGrams.getText().toString().equals("")) {
            Consumable consumable = GlobalInstance.getInstance().consumables.get(position);
            int grams = Integer.parseInt(etGrams.getText().toString());

            add(consumable, grams);
            updateRecycleViews();

        } else {
            Alert.show(
                    this,
                    "You have to give the amount of the ingredient in grams before you proceed to add one.",
                    "Ok"
            );
        }
    }

    @Override
    public void onRemovableClick(int position) {
        GlobalInstance.getInstance().currentRecipe.remove(position);
        updateRecycleViews();
    }

    public void onFinishClick(View view) {
        EditText recipeName = findViewById(R.id.ac_recipes_et_inputName);

        if (recipeName.getText().toString() != null && !recipeName.getText().toString().equals("")) {
            String name = recipeName.getText().toString();
            GlobalInstance.getInstance().consumables.add(new Recipe(name, GlobalInstance.getInstance().currentRecipe));
            updateRecycleViews();

        } else {
            Alert.show(
                    this,
                    "You have to give name for the new recipe.",
                    "Ok"
            );
        }

    }
}