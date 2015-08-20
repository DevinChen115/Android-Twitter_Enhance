package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.mysimpletweets.EndlessScrollListener;
import com.codepath.apps.mysimpletweets.TwitterApp;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by daiyan on 8/20/15.
 */
public class UserTimeLineFragment extends TweetsListFragment{
    private TwitterClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        client = TwitterApp.getRestClient(); // singleton client
        getaTweets().clear();
        populateTimeline(1);

        getLvTweets().setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                populateTimeline(page);
            }
        });
        return v;
    }

    // Creates a new fragment given an int and title
    // DemoFragment.newInstance(5, "Hello");
    public static UserTimeLineFragment newInstance(String screenName) {
        UserTimeLineFragment userTimeLineFragment = new UserTimeLineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userTimeLineFragment.setArguments(args);
        return userTimeLineFragment;
    }

    // Send an API request to get the timeline json
    // Fill the listview by creating the tweet objects from the json
    protected void populateTimeline(int page) {
        String screenName = getArguments().getString("screen_name");
        client.getUserTimeLine(page, screenName, new JsonHttpResponseHandler() {
            // Success


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                addAll(Tweet.fromJSONArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());

            }


        });
    }}
