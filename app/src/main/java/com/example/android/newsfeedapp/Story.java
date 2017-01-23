package com.example.android.newsfeedapp;

/**
 * Created by Skinner on 1/10/17.
 */

public class Story {
    private static final String TAG = Story.class.getSimpleName();

    private String mTitle;
    private String mLink;
    private String mSection;

    public Story(String mTitle, String mLink, String mSection) {
        this.mTitle = mTitle;
        this.mLink = mLink;
        this.mSection = mSection;
    }

    public String getmTitle() { return mTitle; }

    public String getmLink() { return mLink; }

    public String getmSection() { return mSection; }

}
