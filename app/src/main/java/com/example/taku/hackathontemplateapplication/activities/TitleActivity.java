package com.example.taku.hackathontemplateapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.taku.hackathontemplateapplication.R;
import com.example.taku.hackathontemplateapplication.template.TemplateListActivity;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ステータスバーを非表示にしておく
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_title);

        // スタートボタン
        findViewById(R.id.activity_title_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleActivity.this, TemplateListActivity.class));
            }
        });
    }
}
