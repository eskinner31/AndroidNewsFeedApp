package com.example.android.newsfeedapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Skinner on 1/16/17.
 */

public class StoryLoader extends AsyncTaskLoader<List<Story>> {

    private static final String TAG = StoryLoader.class.getSimpleName();

    private String mUrl;

    public StoryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() { forceLoad(); }

    @Override
    public List<Story> loadInBackground() {
        if(mUrl == null) {
            return null;
        }

        List<Story> stories = QueryNewsStories.getNewsStories(mUrl);
        return stories;
    }
}
