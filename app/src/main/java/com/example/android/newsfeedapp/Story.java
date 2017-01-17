package com.example.android.newsfeedapp;

import android.graphics.Bitmap;

/**
 * Created by Skinner on 1/10/17.
 */

public class Story {
    private static final String TAG = Story.class.getSimpleName();

    private String mTitle;
    private String mDescription;
    private Bitmap mImage;
    private String mAuthor;
    private String mSection;
    private String mDate;

    public Story(String mTitle, String mDescription, Bitmap mImage, String mAuthor, String mSection, String mDate) {

        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImage = mImage;
        this.mAuthor = mAuthor;
        this.mSection = mSection;
        this.mDate = mDate;
    }

    public Story(String mTitle, String mDescription, String mAuthor, String mSection, String mDate) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mAuthor = mAuthor;
        this.mSection = mSection;
        this.mDate = mDate;
    }

    public String getmDescription() { return mDescription; }

    public String getmTitle() { return mTitle; }

    public String getmAuthor() { return mAuthor; }

    public Bitmap getmImage() { return mImage; }

    public String getmDate() { return mDate; }

    public String getmSection() { return mSection; }

}
