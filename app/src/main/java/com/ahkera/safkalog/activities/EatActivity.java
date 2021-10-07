package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.EatableAdapter;
import com.ahkera.safkalog.consumable.Consumable;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.util.Alert;

/**
 * Asks user the grams of the consumable and creates DiaryLog and adds it to the currentDiaryDate.
 * @author Vilhelm
 */
public class EatActivity extends AppCompatActivity implements EatableAdapter.OnEatableListener {

    private RecyclerView eatables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);

        eatables = findViewById(R.id.ac_eat_rv_eatables);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        eatables.setLayoutManager(layoutManager);
        eatables.setAdapter(new EatableAdapter(GlobalInstance.getInstance().consumables, this, this::onEatableClick));
    }

    private void eat(Consumable consumable, int grams) {

        GlobalInstance.getInstance().diaryDateToday.addLog(
            new DiaryLog(new ConsumableUnit(consumable, grams))
        );

        // Consumable eaten alert
        Alert.show(
            this,
            "You consumed " + grams + "grams of \"" + consumable.getName() + "\"",
            "Ok"
        );
    }

    @Override
    public void onEatableClick(int position) {
        EditText etGrams = findViewById(R.id.ac_eat_et_grams);

        if(etGrams.getText().toString() != null && !etGrams.getText().toString().equals("")) {
            Consumable consumable = GlobalInstance.getInstance().consumables.get(position);
            int     grams = Integer.parseInt(etGrams.getText().toString());

            eat(consumable, grams);
        } else {
            Alert.show(
                this,
                "You have to give the amount of the consumable in grams before you proceed to add one",
                "Ok"
            );
        }
    }
}