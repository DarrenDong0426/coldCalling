package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UncalledLog extends AppCompatActivity {
    private Button mainActivity;
    private static final String TAG = "CalledLog";
    private int mIndex = 0;
    private ArrayList<Icons> UnCalledIcons = new ArrayList<Icons>();
    private ArrayList<Icons> Icons = new ArrayList<Icons>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Icons> Icons = (ArrayList<com.example.coldcalling.Icons>) getIntent().getExtras().getSerializable("list");
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate: Started.");
        setContentView(R.layout.relative_layout);
        ListView mListView = (ListView) findViewById(R.id.list_view);
        for (Icons icons : Icons){
            if (icons.getCalled() == 0) {
                UnCalledIcons.add(icons);
            }}
        for (int i = 0; i < UnCalledIcons.size(); i++){
            Log.d(TAG, "" + UnCalledIcons.get(i));
        }

        IconsListAdapter adapter = new IconsListAdapter(this, R.layout.activity_called_log, UnCalledIcons);
        mListView.setAdapter(adapter);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UncalledLog.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}