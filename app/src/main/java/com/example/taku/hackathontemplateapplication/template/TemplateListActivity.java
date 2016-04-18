package com.example.taku.hackathontemplateapplication.template;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taku.hackathontemplateapplication.R;

/**
 * テンプレートを一覧表示するActivity
 */
public class TemplateListActivity extends AppCompatActivity {

    ActivityInfoAdapter activityInfoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_listview);

        ActivityInfo[] activities = new ActivityInfo[0];
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            activities = packageInfo.activities;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        activityInfoAdapter = new ActivityInfoAdapter(this, 0, activities);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(activityInfoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityInfo activityInfo = activityInfoAdapter.getItem(position);
                Intent intent = new Intent();
                intent.setClassName(activityInfo.packageName, activityInfo.name);
                startActivity(intent);
            }
        });
    }

    /**
     * アクティビティ情報からリストビューを生み出すカスタムアダプター
     */
    private static class ActivityInfoAdapter extends ArrayAdapter<ActivityInfo> {

        LayoutInflater layoutInflater;


        public ActivityInfoAdapter(Context context, int resource, ActivityInfo[] objects) {
            super(context, 0, objects);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.template_activity_template_list_cell, null);
            }

            ActivityInfo activityInfo = getItem(position);
            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(activityInfo.name);

            return convertView;
        }
    }

}
