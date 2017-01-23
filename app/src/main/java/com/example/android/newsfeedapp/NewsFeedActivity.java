package com.example.android.newsfeedapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Story>>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = NewsFeedActivity.class.getSimpleName();
    private static final String BASE_URL = "https://content.guardianapis.com/search";
    private static final int STORY_LOADER_ID = 1;
    private StoryArrayAdapter mAdapter;
    private SharedPreferences prefs;
    @BindView(R.id.news_feed_view) ListView mNewsFeedView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.no_connection_view) TextView mNoConnectionView;
    @BindView(R.id.no_data_view) TextView mNoDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        ButterKnife.bind(this);

        //Set Empty State View Maybe

        mAdapter = new StoryArrayAdapter(this, new ArrayList<Story>());
        mNewsFeedView.setAdapter(mAdapter);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);


        mProgressBar.setVisibility(View.GONE);

        mNewsFeedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mAdapter.isEmpty()) {
                    return;
                }

                Story currentStory = mAdapter.getItem(i);
                Uri storyUri = Uri.parse(currentStory.getmLink());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, storyUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(STORY_LOADER_ID, null, this);
            loaderManager.restartLoader(STORY_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mAdapter.clear();
            mNoConnectionView.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.segments_key))) {
            mAdapter.clear();

            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                mProgressBar.setVisibility(View.VISIBLE);
                mNoConnectionView.setVisibility(View.GONE);
                getSupportLoaderManager().restartLoader(STORY_LOADER_ID, null, this);
            } else {
                mProgressBar.setVisibility(View.GONE);
                mAdapter.clear();
                mNoConnectionView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public Loader<List<Story>> onCreateLoader(int id, Bundle args) {
        String apiKey = getString(R.string.api_key);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String segment = sharedPrefs.getString(getString(R.string.segments_key), getString(R.string.segments_default_value));

        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", segment);
        uriBuilder.appendQueryParameter("api-key", apiKey);
        return new StoryLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Story>> loader, List<Story> data) {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.clear();

        if (data != null && data.isEmpty()) {
            mNoDataView.setVisibility(View.VISIBLE);
        }

        if (data != null && !data.isEmpty()) {
            mNoDataView.setVisibility(View.GONE);
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Story>> loader) {
        mNoConnectionView.setVisibility(View.GONE);
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
