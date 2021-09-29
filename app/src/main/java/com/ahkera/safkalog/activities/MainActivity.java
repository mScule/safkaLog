package com.ahkera.safkalog.activities;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.diary.DiaryLog;
import com.ahkera.safkalog.eatable.Ingredient;
import com.ahkera.safkalog.global.Global;

public class MainActivity extends AppCompatActivity {

    private LinearLayout labelDiaryLogToday, containerDiaryLogToday;
    private TextView valueTodayTotalKcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(Ingredient ingredient : Global.getInstance().ingredients)
            Log.d("Global", ingredient.toString());

        valueTodayTotalKcal = findViewById(R.id.activity_main_value_today_total_kcal);

        valueTodayTotalKcal.setText(
            Integer.toString(Global.getInstance().diaryDateToday.getKcalTotal())
        );

        // Show or hide the today's diary log widgets
        labelDiaryLogToday     = findViewById(R.id.activity_main_label_diary_log_today);
        containerDiaryLogToday = findViewById(R.id.activity_main_container_diary_log_today);

        if(Global.getInstance().diaryDateToday.getLogs().isEmpty()) {
            labelDiaryLogToday.setVisibility(INVISIBLE);
            containerDiaryLogToday.setVisibility(INVISIBLE);
        } else {

            // Creating today's diary log rows
            for(DiaryLog log : Global.getInstance().diaryDateToday.getLogs()) {
                getLayoutInflater().inflate(R.layout.item_diary_log, containerDiaryLogToday);

                View logRow = getLayoutInflater().inflate(R.layout.item_diary_log, null);

                TextView
                    name = logRow.findViewById(R.id.it_diaryLog_tv_eatableName),
                    kcal = logRow.findViewById(R.id.it_diaryLog_tv_eatableKcal),
                    time = logRow.findViewById(R.id.it_diaryLog_tv_logTime);

                name.setText(log.getEatableUnit().getName());
                kcal.setText(Integer.toString(log.getEatableUnit().getKcal()));
                time.setText(log.getTime());

                containerDiaryLogToday.addView(logRow);
            }
        }
    }

    public void buttonEvents(View view) {
        switch(view.getId()) {
            case R.id.activity_main_button_eat:
                Intent intentEat = new Intent(this, EatActivity.class);
                startActivity(intentEat);
                break;

            case R.id.activity_main_button_ingredients:
                Intent intentIngredients = new Intent(this, IngredientsActivity.class);
                startActivity(intentIngredients);
                break;

            case R.id.activity_main_button_recipes:
                Intent intentRecipes = new Intent(this, RecipesActivity.class);
                startActivity(intentRecipes);
                break;

            case R.id.activity_main_button_diary:
                Intent intentDiary = new Intent(this, DiaryActivity.class);
                startActivity(intentDiary);
                break;

            case R.id.activity_main_button_eat_edit:
                Intent intentEditEat = new Intent(this, EditActivity.class);
                intentEditEat.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_EAT);
                startActivity(intentEditEat);
                break;

            case R.id.activity_main_button_ingredients_edit:
                Intent intentEditIngredients = new Intent(this, EditActivity.class);
                intentEditIngredients.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_INGREDIENTS);
                startActivity(intentEditIngredients);
                break;

            case R.id.activity_main_button_recipes_edit:
                Intent intentEditRecipes = new Intent(this, EditActivity.class);
                intentEditRecipes.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_RECIPES);
                startActivity(intentEditRecipes);
                break;

            case R.id.activity_main_button_diary_edit:
                Intent intentEditDiary = new Intent(this, EditActivity.class);
                intentEditDiary.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_DIARY);
                startActivity(intentEditDiary);
                break;
        }
    }
}