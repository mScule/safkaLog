package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.consumable.Ingredient;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;
import com.ahkera.safkalog.util.Alert;
import com.ahkera.safkalog.util.InputValidator;

/**
 * Asks the user to give the name and kilocalories per 100g from to user.
 *
 * @author Vilhelm
 */

public class IngredientsActivity extends AppCompatActivity {

    private EditText inputName, inputKcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        inputName = findViewById(R.id.ac_ingredients_et_name);
        inputKcal = findViewById(R.id.ac_ingredients_et_kcal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveStateManager.saveState(this);
    }

    public void addIngredient(View view) {

        String
            name = inputName.getText().toString(),
            kcalString = inputKcal.getText().toString();

        if (
            InputValidator.isContentful(this, "ingredient name", name) &&
            InputValidator.isUnsignedInteger(this, "ingredient kcal", kcalString)) {

            int kcal = Integer.parseInt(inputKcal.getText().toString());

            GlobalInstance.getInstance().consumables.add(new Ingredient(name, kcal));

            // New food added alert
            Alert.show(
                    this,
                    "New consumable \"" + name + "\" added with " + kcal + " kcal",
                    "Ok"
            );

        }
    }
}