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
import com.ahkera.safkalog.diary.DiaryDate;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;
import com.ahkera.safkalog.util.Alert;

/**
 * The home screen of the SafkaLog application. The currentDate it changed through this activity.
 * @author Vilhelm
 * @author Konsta
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView    diaryLogsToday;
    private LinearLayout    labelDiaryLogToday;
    private TextView        valueTodayTotalKcal;
    private DiaryLogAdapter diaryLogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hello message check
        if(!SaveStateManager.stateExists(this))
            Alert.show(this, getString(R.string.app_welcomeMsg), "Nice. Let's go");
        else
            SaveStateManager.loadState(this);

        valueTodayTotalKcal = findViewById(R.id.ac_main_tv_todayTotalKcalValue);
        labelDiaryLogToday  = findViewById(R.id.ac_main_ll_diaryLogTodayLabels);
        diaryLogsToday      = findViewById(R.id.ac_main_rv_diaryLogsToday);

        diaryLogAdapter = new DiaryLogAdapter(GlobalInstance.getInstance().diaryDateToday.getLogs(), this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateTodayLog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SaveStateManager.saveState(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkDay();
        updateTodayLog();
    }

    /** Updates the today log recycler view */
    private void updateTodayLog() {

        checkDay();

        // Show or hide the today's diary log widgets
        valueTodayTotalKcal.setText(
            Integer.toString(GlobalInstance.getInstance().diaryDateToday.getKcalTotal())
        );

        if(GlobalInstance.getInstance().diaryDateToday.getLogs().isEmpty()) {
            labelDiaryLogToday.setVisibility(INVISIBLE);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            diaryLogsToday.setLayoutManager(layoutManager);
            diaryLogsToday.setAdapter(diaryLogAdapter);
        }
    }

    /**
     * Checks if the current date in GlobalInstance matches with the current date in the Calendar.
     * If not, new day is created, and the old one is added to the diary
     */
    private void checkDay() {

        // Checking the date. If changed. Set new day.
        if(!GlobalInstance.getInstance().diaryDateToday.isToday()) {

            // Adding the date to diary only, if there's logs made.
            if(!GlobalInstance.getInstance().diaryDateToday.getLogs().isEmpty())

                // Add new date to dates
                GlobalInstance.getInstance().dates.add(GlobalInstance.getInstance().diaryDateToday);

            // Create new current day
            GlobalInstance.getInstance().diaryDateToday = new DiaryDate();

        }
    }

    /** Contains cases for every button in the mainactivity */
    public void buttonEvents(View view) {
        switch(view.getId()) {
            case R.id.ac_main_btn_consumptionLimit:
                Intent intentConsumptionLimit = new Intent(this, ConsumptionLimitActivity.class);
                startActivity(intentConsumptionLimit);
                break;

            case R.id.ac_main_btn_recipeBook:
                Intent intentRecipeBook = new Intent(this, RecipeBookActivity.class);
                startActivity(intentRecipeBook);
                break;

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

            case R.id.ac_main_btn_consumablesEdit:
                Intent intentEditIngredients = new Intent(this, EditActivity.class);
                intentEditIngredients.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_INGREDIENTS);
                startActivity(intentEditIngredients);
                break;

            case R.id.ac_main_btn_diaryEdit:
                Intent intentEditDiary = new Intent(this, EditActivity.class);
                intentEditDiary.putExtra(EditActivity.EDIT_MODE, EditActivity.MODE_DIARY);
                startActivity(intentEditDiary);
                break;
        }
    }
}