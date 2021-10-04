package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.EatableAdapter;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.eatable.Eatable;
import com.ahkera.safkalog.eatable.EatableUnit;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.util.Alert;

public class EatActivity extends AppCompatActivity implements EatableAdapter.OnEatableListener {

    private RecyclerView eatables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);

        eatables = findViewById(R.id.ac_eat_rv_eatables);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        eatables.setLayoutManager(layoutManager);
        eatables.setAdapter(new EatableAdapter(GlobalInstance.getInstance().eatables, this, this::onEatableClick));
    }

    private void eat(Eatable eatable, int grams) {

        GlobalInstance.getInstance().diaryDateToday.addLog(
            new DiaryLog(new EatableUnit(eatable, grams))
        );

        // Eatable eaten alert
        Alert.show(
            this,
            "You consumed " + grams + "grams of \"" + eatable.getName() + "\"",
            "Ok"
        );
    }

    @Override
    public void onEatableClick(int position) {
        EditText etGrams = findViewById(R.id.ac_eat_et_grams);

        if(etGrams.getText().toString() != null && !etGrams.getText().toString().equals("")) {
            Eatable eatable = GlobalInstance.getInstance().eatables.get(position);
            int     grams = Integer.parseInt(etGrams.getText().toString());

            eat(eatable, grams);
        } else {
            Alert.show(
                this,
                "You have to give the amount of the consumable in grams before you proceed to add one",
                "Ok"
            );
        }
    }
}