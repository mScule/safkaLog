package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.eatable.Ingredient;
import com.ahkera.safkalog.global.Global;


/** @author Vilhelm
 *
 * This activity is finished.
 *
 * It works by asking the user to give the name and kcals per 100g from to user.
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

    public void addIngredient(View view) {
        String name = inputName.getText().toString();
        int    kcal = Integer.parseInt(inputKcal.getText().toString());
        Global.getInstance().ingredients.add(new Ingredient(name, kcal));

        AlertDialog.Builder creationMsg = new AlertDialog.Builder(this);
        creationMsg.setMessage("New food \"" + name + "\" added with " + kcal + " kcal");
        creationMsg.setCancelable(true);

        creationMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = creationMsg.create();
        dialog.show();
    }
}