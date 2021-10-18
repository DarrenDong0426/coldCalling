package com.example.coldcalling;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CalledLog extends AppCompatActivity {
    private static final String TAG = "CalledLog";
    private int mIndex = 0;
    private Button mainActivity;
    private ArrayList<Icons> CalledIcons = new ArrayList<Icons>();
    private ArrayList<Icons> Icons = new ArrayList<Icons>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Icons> Icons = (ArrayList<com.example.coldcalling.Icons>) getIntent().getExtras().getSerializable("list");
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate: Started.");
        setContentView(R.layout.relative_layout);
        ListView mListView = (ListView) findViewById(R.id.list_view);
        for (Icons icons : Icons){
            if (icons.getCalled() != 0) {
                CalledIcons.add(icons);
            }}
        for (int i = 0; i < CalledIcons.size(); i++){
            Log.d(TAG, "" + CalledIcons.get(i));
        }

        IconsListAdapter adapter = new IconsListAdapter(this, R.layout.activity_called_log, CalledIcons);
        mListView.setAdapter(adapter);

        mainActivity = (Button) findViewById(R.id.activity_main);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalledLog.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}