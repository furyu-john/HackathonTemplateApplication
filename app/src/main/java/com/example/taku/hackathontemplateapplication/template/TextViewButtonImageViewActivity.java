package com.example.taku.hackathontemplateapplication.template;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taku.hackathontemplateapplication.R;

/**
 * TextViewとButtonとImageViewの使用例
 */
public class TextViewButtonImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_text_button_imageview);

        // 動的にテキストを入れる
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("動的に入れるテキスト");

        // ボタンにクリックイベントを設定する（他のViewにも使える）
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ボタン押下時の処理
            }
        });

        // 画像のリソースを動的に設定する。
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.sample);
    }

}
