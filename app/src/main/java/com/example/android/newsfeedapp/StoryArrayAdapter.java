package com.example.android.newsfeedapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Skinner on 1/10/17.
 */

public class StoryArrayAdapter extends ArrayAdapter<Story> {
    public StoryArrayAdapter(Activity context, ArrayList<Story> story) { super(context, 0, story); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //TODO Inflate the Layout From the Context

        //TODO Create ViewHolder

        //TODO ASsign Views

        return convertView;
    }
}
