package com.example.coldcalling;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class CalledLog extends AppCompatActivity {
    private Button mNextButton;
    private Button mBackButton;
    private ImageView image;
    private int mIndex = 0;
    private TextView dateAndTime, name, numberCalledOn;
    private ArrayList<Icons> CalledIcons = new ArrayList<Icons>();
    private ArrayList<Icons> Icons = new ArrayList<Icons>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Icons> Icons = (ArrayList<Icons>) getIntent().getSerializableExtra("list");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_log);
        for (Icons icons : Icons){
            if (icons.getCalled() != 0) {
                CalledIcons.add(new Icons(icons.getImageResId(), icons.getName(), icons.getCalled(), icons.getTime()));
            }}
        dateAndTime = (TextView) findViewById(R.id.dateAndTime);
        name = (TextView) findViewById(R.id.name);
        numberCalledOn = (TextView) findViewById(R.id.numCalled);

// name.setText("Name: " + CalledIcons.get(mIndex).getName());
// dateAndTime.setText("Date: " + CalledIcons.get(mIndex).getTime());
// numberCalledOn.setText("Times called on: " + CalledIcons.get(0).getCalled());

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(v -> {
            if (mIndex<CalledIcons.size()){
                mIndex = (mIndex + 1) % CalledIcons.size();
                updateView();
            }
            else{
                mIndex = 0;
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(v -> {
            if (mIndex>0){
                mIndex = (mIndex -1) % CalledIcons.size();
                updateView();
            }
            else{
                mIndex = CalledIcons.size();
            }
        });
       // updateView();
    }

    private void updateView(){
        int num = CalledIcons.get(mIndex).getCalled();
        String indexName = CalledIcons.get(mIndex).getName();
        name.setText("Name: " + indexName);
        numberCalledOn.setText("Times called on: " + indexName);
    }
}
