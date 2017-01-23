package com.example.android.newsfeedapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Skinner on 1/10/17.
 */

public class StoryArrayAdapter extends ArrayAdapter<Story> {
    public StoryArrayAdapter(Activity context, ArrayList<Story> story) { super(context, 0, story); }

    //Overriding this method to decide what view we would like to populate listview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //Annotated as nullable in  docs, this is why we check.
        if(convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.story_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Story currentStory = getItem(position);

        holder.storySection.setText(currentStory.getmSection());
        holder.storyTitle.setText(currentStory.getmTitle());

        return convertView;
    }

    /**
     * What is the visibility of using a nested static class?
     * What is the general rule of thumb on deciding when it's best to use a static class?
     */
    static class ViewHolder {
        @BindView(R.id.story_section) TextView storySection;
        @BindView(R.id.story_title) TextView storyTitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
