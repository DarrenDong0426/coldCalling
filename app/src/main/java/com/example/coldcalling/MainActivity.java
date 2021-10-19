package com.example.coldcalling;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private TextView dateAndTime, name, className;
    private Button calledLog, uncalledLog, random;
    private ImageView image;
    private ArrayList<Icons> Icons = new ArrayList<Icons>();
    private static final String TAG = "MainActivity";
    private Date called;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateAndTime = (TextView) findViewById(R.id.dateAndTime);
        className = (TextView) findViewById(R.id.className);
        className.setText("App Development");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String date = new SimpleDateFormat("MM/dd/yyy").format(new Date());
                String time = new SimpleDateFormat("hh:mm a").format(new Date());
                dateAndTime.setText("Date: " + date + " Time: " + time);
            }
        }, 0, 1000);
        Field[] fields = R.drawable.class.getFields();
        for (Field field : fields) {
            if (!field.getName().startsWith("ic") && !field.getName().startsWith("abc")
                    && !field.getName().startsWith("avd") && !field.getName().startsWith("tool")
                    && !field.getName().startsWith("btn") && !field.getName().startsWith("design")
                    && !field.getName().startsWith("material") && !field.getName().startsWith("mtrl")
                    && !field.getName().startsWith("notif") && !field.getName().startsWith("test")
                    && !field.getName().startsWith("navi")) {
                try {
                    Icons.add(new Icons(field.getInt(null), field.getName(), 0, new Date()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        addListenOnButton();
    }

    private void addListenOnButton() {
        name = (TextView) findViewById(R.id.name);
        calledLog = (Button) findViewById(R.id.calledLog);
        calledLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CalledLog.class);
                i.putExtra("list", Icons);
                startActivity(i);
            }
        });
        uncalledLog = (Button) findViewById(R.id.uncalledLog);
        uncalledLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UncalledLog.class);
                i.putExtra("list", Icons);
                startActivity(i);
            }
        });
        image = (ImageView) findViewById(R.id.image);
        random = (Button) findViewById(R.id.Random);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int index = rand.nextInt(Icons.size());
                if (Icons.get(index).getCalled() < 2) {
                    Icons x = Icons.get(index);
                    image.setImageResource(x.getImageResId());
                    name.setText(x.getName());
                    if (x.getCalled() < 2) {
                        x.setCalled(x.getCalled() + 1);
                    }
                    if (Icons.get(index).getCalled() == 2) {
                        called = new Date();
                        Icons.get(index).setTime(called);
                    }
                } else {
                    Date current = new Date();

                    long diff = current.getTime() - called.getTime();
                    Log.d(TAG, "" + current.getTime());
                    Log.d(TAG, "" + called.getTime());
                    Log.d(TAG, "The time is " + diff);
                    if (diff >= 2400000){
                        Icons.get(index).setCalled(0);
                    }
                    String message = Icons.get(index).getName() + " has already been called twice in the past 40 minutes. Click randomize again to call another person.";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }


        });
    }

}