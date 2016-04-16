package com.example.taku.hackathontemplateapplication.template;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taku.hackathontemplateapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ListViewのテンプレート
 */
public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_listview);

        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new Data("index: " + i));
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter(this, 0, dataList));
    }

    /**
     * データクラス
     */
    private static class Data {
        private String text;

        public Data(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    /**
     * データリストからリストビューを生み出すためのカスタムアダプター
     */
    private static class ListAdapter extends ArrayAdapter<Data> {

        LayoutInflater layoutInflater;


        public ListAdapter(Context context, int resource, List<Data> objects) {
            super(context, 0, objects);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.template_listview_cell, null);
            }

            Data data = getItem(position);
            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(data.getText());

            return convertView;
        }
    }

}
