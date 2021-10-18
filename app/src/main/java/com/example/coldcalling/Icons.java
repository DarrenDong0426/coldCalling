package com.example.coldcalling;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Icons implements Serializable{

    private int mImageResId;

    private String mName;

    private int mCalled;

    private Date mTime;

    public Icons(int imageResId, String name, int called , Date time) {
        mImageResId = imageResId;
        name.replaceAll(" ", "");
        String[]names = name.split("_");
        String fName = "";
        String lName = "";
        if(names.length == 2) {
            fName = names[0].substring(0,1).toUpperCase() + names[0].substring(1);
            lName = names[1].substring(0,1).toUpperCase() + names[1].substring(1);
        }

        mName = fName + " " + lName;

        mCalled = called;

        mTime = time;

    }

    public int getImageResId() { return mImageResId; }

    public void setImageResId(int imageResId) { mImageResId = imageResId; }

    public String getName() {
        return mName;
    }

    public void setName(String name) { mName = name; }

    public void setCalled(int called) {
        mCalled = called;
    }

    public int getCalled(){ return mCalled; }

    public Date getTime(){
        return mTime;
    }

    public void setTime(Date time){
        mTime = time;
    }

}