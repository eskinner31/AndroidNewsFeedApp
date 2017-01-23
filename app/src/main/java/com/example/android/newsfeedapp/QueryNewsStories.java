package com.example.android.newsfeedapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Skinner on 1/16/17.
 */

public final class QueryNewsStories {

    private static final String TAG = QueryNewsStories.class.getSimpleName();

    private QueryNewsStories() {}

    //Request for List of Stories
    public static List<Story> getNewsStories(String requestUrl) {
        String response = null;
        URL url = createUrl(requestUrl);

        try {
            response = newsHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "getNewsStories: ", e);
        }

        List<Story> stories = parseJsonFeatures(response);

        return stories;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "createUrl: Problem with URL", e);
        }
        return url;
    }

    private static String newsHttpRequest(URL url) throws IOException {
        String response = "";

        if (url == null) {
            return response;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            //CASTING THE URL as a HttpURLConnection. This is how we know where the call is directed
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                response = streamReader(inputStream);
            }
        } catch (IOException e) {
            Log.e(TAG, "newsHttpRequest: ", e);
        } finally {

            //CLOSE ALL CONNECTIONS AND STREAMS
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        //RETURN RESULT
        return response;
    }

    private static String streamReader(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Story> parseJsonFeatures(String newsStoryResults) {
        if (TextUtils.isEmpty(newsStoryResults)) {
            return Collections.EMPTY_LIST;
        }

        List<Story> stories = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(newsStoryResults);
            JSONObject topLevelResponseObj = responseObject.getJSONObject("response");
            JSONArray storiesArray = topLevelResponseObj.getJSONArray("results");

            for (int i = 0; i < storiesArray.length(); i++) {
                JSONObject currentStory = storiesArray.getJSONObject(i);

                String storyTitle = currentStory.getString("webTitle");
                String storySectionName = currentStory.getString("sectionName");
                String storyStringUrl = currentStory.getString("webUrl");

                Story story = new Story(storyTitle, storyStringUrl, storySectionName);

                stories.add(story);
            }
        } catch (JSONException e) {
            Log.e(TAG, "parseJsonFeatures: Issues with the parsing", e);
        }

        return stories;
    }
}
