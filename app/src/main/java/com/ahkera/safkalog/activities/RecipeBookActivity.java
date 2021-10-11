package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.ConsumableAdapter;
import com.ahkera.safkalog.consumable.Consumable;
import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.consumable.Ingredient;
import com.ahkera.safkalog.consumable.Recipe;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.gson.GsonSingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecipeBookActivity extends AppCompatActivity implements ConsumableAdapter.OnConsumableListener {

    private RecyclerView recyclerView;
    private ArrayList<Consumable> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);

        recipes = new ArrayList();

        // Add only recipes to the list
        for(Consumable consumable : GlobalInstance.getInstance().consumables)
            if(consumable instanceof Recipe)
                recipes.add(consumable);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.ac_recipeBook_rv_recipes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ConsumableAdapter(recipes, this, this::onConsumableClick));
    }

    @Override
    public void onConsumableClick(int position) {
        Gson gson = GsonSingleton.getInstance().gson;

        Recipe recipe = (Recipe)recipes.get(position);

        for(ConsumableUnit unit : recipe.getIngredients())
            Log.d("Recipe", unit.toString());

        String jsonRecipe = gson.toJson(recipe);

        Intent intentRecipeBookRecipe = new Intent(this, RecipeBookRecipeActivity.class);
        intentRecipeBookRecipe.putExtra(RecipeBookRecipeActivity.EXTRA_RECIPE, jsonRecipe);

        startActivity(intentRecipeBookRecipe);
    }
}