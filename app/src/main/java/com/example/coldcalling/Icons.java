package com.example.coldcalling;

public class Icons {

    private int mImageResId;

    private String mName;

    public Icons(int imageResId, String name) {
        mImageResId = imageResId;
        mName = name;
    }

    public int getImageResId() { return mImageResId; }

    public void setImageResId(int imageResId) { mImageResId = imageResId; }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }
}

