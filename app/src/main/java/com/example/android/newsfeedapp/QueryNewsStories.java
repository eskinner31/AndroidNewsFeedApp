package com.example.android.newsfeedapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Skinner on 1/16/17.
 */

public final class QueryNewsStories {

    private static final String TAG = QueryNewsStories.class.getSimpleName();
    private final String BASE_URL = ;

    private QueryNewsStories() {}

    //Request for List of Stories
    public static List<Story> getNewsStories(String request) {
        String response = null;
        URL url = createUrl(request);

        try {
            response = newsHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "getNewsStories: ", e);
        }

        List<Story> stories = parseJsonFeatures(response);

        return stories;
    }

    private static URL createUrl(String stringUrl) {

    }

    private static String newsHttpRequest(URL url) throws IOException {
        String response = "";

        if (url == null) {
            return response;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {

        } catch (IOException e) {
            Log.e(TAG, "newsHttpRequest: ", e);
        } finally {

        }
    }

    private static String streamReader(InputStream inputStream) throws IOException {

    }

    private static List<Story> parseJsonFeatures(String newsStoryResults) {

    }

    private static Bitmap getStoryImage(String imageUrlSource) throws IOException {
        try {

        } catch (IOException e) {
            Log.e(TAG, "getStoryImage: ", e);
        }
        return null;
    }
}
