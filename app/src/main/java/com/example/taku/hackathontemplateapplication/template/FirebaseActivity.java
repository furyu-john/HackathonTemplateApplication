package com.example.taku.hackathontemplateapplication.template;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taku.hackathontemplateapplication.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

/**
 * Firebaseのテンプレート
 */
public class FirebaseActivity extends AppCompatActivity {

    private static final String PATH_CHAT = "chat";

    EditText messageEditText;
    ChatAdapter chatAdapter;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_firebase);

        Firebase.setAndroidContext(getApplicationContext());
        final Firebase chatFirebase = new Firebase("https://<MY_APP>.firebaseio.com/").child(PATH_CHAT);

        // チャットのメッセージリスト
        listView = (ListView) findViewById(R.id.listView);
        chatAdapter = new ChatAdapter(getApplicationContext(), chatFirebase.limitToLast(10));
        chatAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.smoothScrollToPosition(chatAdapter.getCount());
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        });
        listView.setAdapter(chatAdapter);

        // メッセージ入力領域
        messageEditText = (EditText) findViewById(R.id.editText);

        // Sendボタン
        findViewById(R.id.button_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                chatFirebase.push().setValue(message);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private static class ChatAdapter extends ArrayAdapter<String> implements ChildEventListener {

        LayoutInflater inflater;

        public ChatAdapter(Context context, Query query) {
            super(context, 0);
            inflater = LayoutInflater.from(context);
            query.addChildEventListener(this);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.template_firebase_list_cell, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(getItem(position));

            return convertView;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String message = dataSnapshot.getValue(String.class);
            add(message);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
        }
    }
}
