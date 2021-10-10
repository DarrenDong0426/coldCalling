package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private TextView dateAndTime, name;
    private Button calledLog, uncalledLog, random;
    private ImageView image;
    private ArrayList<Icons> Icons= new ArrayList<Icons>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateAndTime = (TextView) findViewById(R.id.dateAndTime);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String date = new SimpleDateFormat("MM/dd/yyy").format(new Date());
                String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
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
                    && !field.getName().startsWith("navi")){
                try {
                    Icons.add(new Icons(field.getInt(null), field.getName()));
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
        uncalledLog = (Button) findViewById(R.id.uncalledLog);
        image = (ImageView) findViewById(R.id.image);
        random = (Button) findViewById(R.id.Random);
        random.setOnClickListener(new View.OnClickListener(){
            @Override
            public void OnClick(View v){

            }
        };


    }
}