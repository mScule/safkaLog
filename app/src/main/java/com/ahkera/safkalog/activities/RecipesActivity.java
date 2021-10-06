package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.DiaryLogAdapter;
import com.ahkera.safkalog.adapters.EatableAdapter;
import com.ahkera.safkalog.adapters.RemovableAdapter;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.eatable.Eatable;
import com.ahkera.safkalog.eatable.EatableUnit;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.util.Alert;

public class RecipesActivity extends AppCompatActivity implements EatableAdapter.OnEatableListener, RemovableAdapter.OnRemovableListener {

    private RecyclerView eatables;
    private RecyclerView currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        //Upper RecyclerView
        eatables = findViewById(R.id.ac_ingredientsCatalog_rv);

        LinearLayoutManager layoutManagerUpper = new LinearLayoutManager(this);
        eatables.setLayoutManager(layoutManagerUpper);
        eatables.setAdapter(new EatableAdapter(GlobalInstance.getInstance().eatables, this, this::onEatableClick));

        //Lower RecyclerView
        currentRecipe = findViewById(R.id.ac_currentIngredients_rv);

        LinearLayoutManager layoutManagerLower = new LinearLayoutManager(this);
        currentRecipe.setLayoutManager(layoutManagerLower);
        currentRecipe.setAdapter(new RemovableAdapter(GlobalInstance.getInstance().currentRecipe, this, this::onRemovableClick));
    }

    private void add(Eatable eatable, int grams) {

        GlobalInstance.getInstance().diaryDateToday.addLog(
                new DiaryLog(new EatableUnit(eatable, grams))
        );

        // Adding ingredients alert
        Alert.show(
                this,
                grams + " grams of \"" + eatable.getName() + "\"" + " added to current ingredients.",
                "Ok"
        );
    }

    @Override
    public void onEatableClick(int position) {
        EditText etGrams = findViewById(R.id.ac_recipes_et_inputGrams);

        if (etGrams.getText().toString() != null && !etGrams.getText().toString().equals("")) {
            Eatable eatable = GlobalInstance.getInstance().eatables.get(position);
            int grams = Integer.parseInt(etGrams.getText().toString());

            add(eatable, grams);
        } else {
            Alert.show(
                    this,
                    "You have to give the amount of the consumable in grams before you proceed to add one",
                    "Ok"
            );
        }
    }

    @Override
    public void onRemovableClick(int position){

    }
}