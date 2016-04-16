package com.example.taku.hackathontemplateapplication.template;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.taku.hackathontemplateapplication.R;

/**
 * CheckBoxとSwitchとProgressBarのテンプレート
 */
public class CheckBoxSwitchProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_checkbox_switch_progressbar);

        // チェックボックスのチェックON・OFFの検知
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // チェックされた時
                    Toast.makeText(CheckBoxSwitchProgressBarActivity.this, "checked", Toast.LENGTH_SHORT).show();
                } else {
                    // チェックが外された時
                    Toast.makeText(CheckBoxSwitchProgressBarActivity.this, "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // スイッチON・OFFの検知
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // ONになった時
                    Toast.makeText(CheckBoxSwitchProgressBarActivity.this, "on", Toast.LENGTH_SHORT).show();
                } else {
                    // ONになった時
                    Toast.makeText(CheckBoxSwitchProgressBarActivity.this, "off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // プログレスバー（水平）を30%に設定
        ProgressBar horizontalProgressBar = (ProgressBar) findViewById(R.id.horizontalProgressBar);
        horizontalProgressBar.setProgress(horizontalProgressBar.getMax() * 30 / 100);
    }

}
