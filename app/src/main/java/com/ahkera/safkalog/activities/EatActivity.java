package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.ConsumableAdapter;
import com.ahkera.safkalog.consumable.Consumable;
import com.ahkera.safkalog.consumable.ConsumptionLimit;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.consumable.ConsumableUnit;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;
import com.ahkera.safkalog.util.Alert;
import com.ahkera.safkalog.util.InputValidator;

/**
 * Asks user the grams of the consumable and creates DiaryLog and adds it to the currentDiaryDate.
 * @author Vilhelm
 */
public class EatActivity extends AppCompatActivity implements ConsumableAdapter.OnConsumableListener {

    private RecyclerView eatables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);

        eatables = findViewById(R.id.ac_eat_rv_eatables);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        eatables.setLayoutManager(layoutManager);
        eatables.setAdapter(new ConsumableAdapter(GlobalInstance.getInstance().consumables, this, this::onConsumableClick));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveStateManager.saveState(this);
    }

    private void eat(Consumable consumable, int grams) {

        GlobalInstance.getInstance().diaryDateToday.addLog(
            new DiaryLog(new ConsumableUnit(consumable, grams))
        );

        ConsumptionLimit limit = GlobalInstance.getInstance().consumptionLimit;

        if(limit.isToggled()) {
            int kcalLeft = limit.getKcal() - GlobalInstance.getInstance().diaryDateToday.getKcalTotal();

            if (kcalLeft > 0) {
                Alert.show(
                    this,
                    "You have " + kcalLeft + " kcal left for today.",
                    "Ok"
                );
            } else if (kcalLeft == 0) {
                Alert.show(
                        this,
                        "You have consumed all your kcals for today!",
                        "Ok"
                );
            } else {
                Alert.show(
                        this,
                        "You have exceeded you kcal limit by " + -kcalLeft + " today",
                        "Ok"
                );
            }
        } else {
            // Consumable eaten alert
            Alert.show(
                this,
                "You consumed " + grams + " grams of \"" + consumable.getName() + "\"",
                "Ok"
            );
        }
    }

    @Override
    public void onConsumableClick(int position) {
        EditText etGrams = findViewById(R.id.ac_eat_et_grams);

        if(InputValidator.isUnsignedInteger(etGrams.getText().toString())) {

            int     grams = Integer.parseInt(etGrams.getText().toString());

            if(grams > 0) {
                Consumable consumable = GlobalInstance.getInstance().consumables.get(position);
                eat(consumable, grams);
            } else {
                Alert.show(
                    this,
                    "Grams can't be zero or smaller value",
                    "Ok"
                );
            }
        } else {
            Alert.show(
                this,
                "You have to give the amount of the consumable in grams before you proceed to add one",
                "Ok"
            );
        }
    }
}