package com.ahkera.safkalog.activities;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.DiaryLogAdapter;
import com.ahkera.safkalog.global.Global;

public class MainActivity extends AppCompatActivity {

    private RecyclerView diaryLogsToday;
    private LinearLayout labelDiaryLogToday;
    private TextView     valueTodayTotalKcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueTodayTotalKcal = findViewById(R.id.ac_main_tv_todayTotalKcalValue);

        valueTodayTotalKcal.setText(
            Integer.toString(Global.getInstance().diaryDateToday.getKcalTotal())
        );

        // Show or hide the today's diary log widgets
        labelDiaryLogToday = findViewById(R.id.ac_main_ll_diaryLogTodayLabels);
        diaryLogsToday     = findViewById(R.id.ac_main_rv_diaryLogsToday);

        if(Global.getInstance().diaryDateToday.getLogs().isEmpty()) {
            labelDiaryLogToday.setVisibility(INVISIBLE);
            diaryLogsToday.setVisibility(INVISIBLE);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            diaryLogsToday.setLayoutManager(layoutManager);
            diaryLogsToday.setAdapter(
                new DiaryLogAdapter(Global.getInstance().diaryDateToday.getLogs(),this)
            );
        }
    }

    public void buttonEvents(View view) {
        switch(view.getId()) {
            case R.id.ac_main_btn_eat:
                Intent intentEat = new Intent(this, EatActivity.class);
                startActivity(intentEat);
                break;

            case R.id.ac_main_btn_ingredients:
                Intent intentIngredients = new Intent(this, IngredientsActivity.class);
                startActivity(intentIngredients);
                break;

            case R.id.ac_main_btn_recipes:
                Intent intentRecipes = new Intent(this, RecipesActivity.class);
                startActivity(intentRecipes);
                break;

            case R.id.ac_main_btn_diary:
                Intent intentDiary = new Intent(this, DiaryActivity.class);
                startActivity(intentDiary);
                break;

            case R.id.ac_main_btn_eatEdit:
                Intent intentEditEat = new Intent(this, EditActivity.class);
                intentEditEat.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_EAT);
                startActivity(intentEditEat);
                break;

            case R.id.ac_main_btn_ingredientsEdit:
                Intent intentEditIngredients = new Intent(this, EditActivity.class);
                intentEditIngredients.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_INGREDIENTS);
                startActivity(intentEditIngredients);
                break;

            case R.id.ac_main_btn_recipesEdit:
                Intent intentEditRecipes = new Intent(this, EditActivity.class);
                intentEditRecipes.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_RECIPES);
                startActivity(intentEditRecipes);
                break;

            case R.id.ac_main_btn_diaryEdit:
                Intent intentEditDiary = new Intent(this, EditActivity.class);
                intentEditDiary.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_DIARY);
                startActivity(intentEditDiary);
                break;
        }
    }
}