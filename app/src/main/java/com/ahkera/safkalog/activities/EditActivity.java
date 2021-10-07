package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.adapters.RemovableAdapter;
import com.ahkera.safkalog.global.GlobalInstance;

import java.util.ArrayList;

/**
 * User can edit (delete) information from different ArrayLists by accessing edit activity with
 * different modes. These modes determines which ArrayList will be modified.
 * @author Vilhelm
 */
public class EditActivity extends AppCompatActivity implements RemovableAdapter.OnRemovableListener {

    public static final int
        MODE_EAT         = 0,
        MODE_INGREDIENTS = 1,
        MODE_RECIPES     = 2,
        MODE_DIARY       = 3;

    public static final String EDIT_MODE =
        "com.ahkera.safkalog.activities.EditActivity.EDIT_ACTIVITY";

    private RecyclerView editables;

    private int editMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle extras = getIntent().getExtras();

        editables = findViewById(R.id.ac_edit_rv_editables);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        editables.setLayoutManager(layoutManager);

        editMode = extras.getInt(EDIT_MODE);

        updateRemovables(editMode);
    }

    private void updateRemovables(int mode) {

        ArrayList<?> editablesList = new ArrayList();

        switch(mode) {
            case MODE_EAT:
                editablesList = GlobalInstance.getInstance().diaryDateToday.getLogs();
                break;
            case MODE_INGREDIENTS:
            case MODE_RECIPES:
                editablesList = GlobalInstance.getInstance().consumables;
                break;
            case MODE_DIARY:
                break;
        }

        editables.setAdapter(new RemovableAdapter(
                editablesList,
                this,
                this::onRemovableClick
        ));
    }

    @Override
    public void onRemovableClick(int position) {
        switch(editMode) {
            case MODE_EAT:
                GlobalInstance.getInstance().diaryDateToday.removeLog(position);
                break;
            case MODE_INGREDIENTS:
            case MODE_RECIPES:
                GlobalInstance.getInstance().consumables.remove(position);
                break;
            case MODE_DIARY:
                break;
        }
        updateRemovables(editMode);
    }
}