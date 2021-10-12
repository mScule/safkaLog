package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.ConsumableUnitAdapter;
import com.ahkera.safkalog.consumable.Recipe;
import com.ahkera.safkalog.global.gson.GsonSingleton;
import com.google.gson.Gson;

/**
 * Shows to the user the selected Recipe from the RecipeBook.
 * @author Vilhelm
 */
public class RecipeBookRecipeActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE =
        "com.ahkera.safkalog.activities.RecipeBookRecipeActivity.RECIPE";

    private TextView recipeName;
    private RecyclerView ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book_recipe);

        Gson gson = GsonSingleton.getInstance().gson;
        Bundle extras = getIntent().getExtras();
        Recipe recipe = gson.fromJson(extras.getString(EXTRA_RECIPE), Recipe.class);

        recipeName = findViewById(R.id.ac_recipeBookRecipe_tv_recipeName);
        recipeName.setText(recipe.getName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ingredientList = findViewById(R.id.ac_recipeBookRecipe_tv_ingredientsList);
        ingredientList.setLayoutManager(layoutManager);
        ingredientList.setAdapter(new ConsumableUnitAdapter(recipe, this));
    }
}