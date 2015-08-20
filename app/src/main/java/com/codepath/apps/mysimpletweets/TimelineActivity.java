package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweets.fragments.HomeTimeLineFragment;
import com.codepath.apps.mysimpletweets.fragments.MentionsTimeLineFragment;

public class TimelineActivity extends ActionBarActivity {

    private HomeTimeLineFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    public void onProfileView(MenuItem mi){
        Intent i = new Intent(this,ProfileActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.twitter_post) {
            goPost();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goPost() {
        Intent i = new Intent(this, PostActivity.class);
        startActivityForResult(i, 3458);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            //fragment.getaTweets().clear();
            //fragment.populateTimeline(1);
        }
    }

    public class TweetsPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = {"Home","Mentions"};

        public TweetsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new HomeTimeLineFragment();
            }else if (position == 1){
                return new MentionsTimeLineFragment();
            }else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}
