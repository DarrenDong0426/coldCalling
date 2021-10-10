package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
            if (!field.getName().startsWith("ic") || !field.getName().startsWith("abc")) {
                try {
                    Icons.add(new Icons(field.getInt(null), field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < Icons.size(); i++){
            System.out.print(Icons.get(i).getName() + " + " + Icons.get(i).getImageResId());
        }

        addListenOnButton();
    }

    private void addListenOnButton() {
        name = (TextView) findViewById(R.id.name);
        calledLog = (Button) findViewById(R.id.calledLog);
        uncalledLog = (Button) findViewById(R.id.uncalledLog);
        random = (Button) findViewById(R.id.Random);
        image = (ImageView) findViewById(R.id.image);

    }
}