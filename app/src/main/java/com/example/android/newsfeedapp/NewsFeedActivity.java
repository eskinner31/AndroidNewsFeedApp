package com.example.android.newsfeedapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Story>>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = NewsFeedActivity.class.getSimpleName();
    private static final String BASE_URL = "https://content.guardianapis.com/search";
    private Context mContext;
    @BindView(R.id.news_feed_view) ListView mNewsFeedView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        mContext = NewsFeedActivity.this;
        ButterKnife.bind(this);

        mProgressBar.setVisibility(View.GONE);

        //TODO Check For Connection
        //TODO Build out the other methods
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

    @Override
    public Loader<List<Story>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Story>> loader, List<Story> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Story>> loader) {

    }
}
