package com.ahkera.safkalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.ahkera.safkalog.R;
import com.ahkera.safkalog.global.GlobalInstance;
import com.ahkera.safkalog.global.SaveStateManager;
import com.ahkera.safkalog.util.Alert;
import com.ahkera.safkalog.util.InputValidator;

public class ConsumptionLimitActivity extends AppCompatActivity {

    private EditText editText;
    private ToggleButton toggleButton;

    private void showAlertActivated() {
        Alert.show(
            this,
            getString(R.string.ac_consumptionLimit_msg_activated),
            "Ok"
        );
    }

    private void showAlertDeactivated() {
        Alert.show(
            this,
            getString(R.string.ac_consumptionLimit_msg_deactivated),
            "Ok"
        );
    }

    public void setLimitSize(View view) {
        String input = editText.getText().toString();

        if(InputValidator.isUnsignedInteger(this, "consumption limit", input)) {
            GlobalInstance.getInstance().consumptionLimit.setKcal(Integer.parseInt(input));
            Alert.show(this, "New consumption limit " + input + " kcal per day set!", "Ok");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_limit);

        editText = findViewById(R.id.ac_consumptionLimit_et_setLimit);
        toggleButton = findViewById(R.id.ac_consumptionLimit_tb_setLimit);

        editText.setText(Integer.toString(GlobalInstance.getInstance().consumptionLimit.getKcal()));
        toggleButton.setChecked(GlobalInstance.getInstance().consumptionLimit.isToggled());

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                GlobalInstance.getInstance().consumptionLimit.setToggled(b);

                if(b)
                    showAlertActivated();
                else
                    showAlertDeactivated();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SaveStateManager.saveState(this);
    }
}