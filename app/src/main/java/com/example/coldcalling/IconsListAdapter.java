package com.example.coldcalling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class IconsListAdapter extends ArrayAdapter<Icons> {
    public static final String TAG = "IconsListAdapter";
    private Context mContext;
    int mResource;

    public IconsListAdapter(Context context, int resource, ArrayList<Icons> objects){
        super(context, resource, objects);
        mContext=context;
        mResource = resource;
    }
    @Override
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent){
        int imageID = getItem(position).getImageResId();
        String name = getItem(position).getName();
        int numCalled = getItem(position).getCalled();
        Date time = getItem(position).getTime();

        Icons icon = new Icons(imageID, name, numCalled, time);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView iconName = (TextView) convertView.findViewById(R.id.name);
        TextView iconNumCalled = (TextView) convertView.findViewById(R.id.numCalled);
        TextView iconDate = (TextView) convertView.findViewById(R.id.dateAndTime);
        ImageView iconImage =(ImageView) convertView.findViewById(R.id.image);

        iconName.setText(String.valueOf(name));
        iconNumCalled.setText(String.valueOf(numCalled));
        iconDate.setText(String.valueOf(time));
        iconImage.setImageResource(imageID);
        return convertView;
    }

}
