package com.jasonko.newyoutubeapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ArrayList<YoutubeVideo> myVideos = new ArrayList<YoutubeVideo>();
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);
        new DownloadVideosTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DownloadVideosTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            myVideos = VideoAPI.getYoutubeVideos("truemovie1",1);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            ListVideoAdapter videoAdapter = new ListVideoAdapter(MainActivity.this, myVideos);
            myListView.setAdapter(videoAdapter);
        }
    }


}
