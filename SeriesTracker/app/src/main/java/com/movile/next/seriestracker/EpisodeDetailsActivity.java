package com.movile.next.seriestracker;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class EpisodeDetailsActivity extends Activity {

    protected String myString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onCreate()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onResume()");
        if(myString != null ) {
            Log.d(EpisodeDetailsActivity.class.getSimpleName(), "myString = " + myString);
        }else {
            Log.d(EpisodeDetailsActivity.class.getSimpleName(), "myString is null");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onRestoreInstanceState()");
        super.onRestoreInstanceState(savedInstanceState);
        myString = savedInstanceState.getString("myString");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onSaveInstanceState()");
        myString = "lalalala";
        outState.putString("myString", myString);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        myString = null;
        Log.d(EpisodeDetailsActivity.class.getSimpleName(), "called onDestroy()");
        super.onDestroy();
    }
}
