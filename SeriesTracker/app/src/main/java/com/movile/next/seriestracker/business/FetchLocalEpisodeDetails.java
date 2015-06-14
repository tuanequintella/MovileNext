package com.movile.next.seriestracker.business;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.movile.next.seriestracker.activity.OnEpisodeLoadedListener;
import com.movile.next.seriestracker.util.ModelConverter;
import com.movile.next.seriestracker.model.Episode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FetchLocalEpisodeDetails extends AsyncTask<Context, Void, Episode> {

    private static OnEpisodeLoadedListener mListener;
    private static final String TAG = FetchLocalEpisodeDetails.class.getSimpleName();
    private static final String ASSET_NAME = "episode.json";

    public FetchLocalEpisodeDetails (OnEpisodeLoadedListener listener) {
        mListener = listener;
    }

    @Override
    protected Episode doInBackground(Context... context) {
        Episode episode = null;
        InputStreamReader reader = null;

        try {
            InputStream stream = context[0].getResources().getAssets().open(ASSET_NAME);
            reader = new InputStreamReader(stream);
            episode = new ModelConverter().toEpisode(reader);
        } catch (IOException e) {
            Log.e(TAG, "Error loading local content from file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error releasing resource", e);
                }
            }
        }

        return episode;
    }

    @Override
    protected void onPostExecute(Episode episode) {
        mListener.onEpisodeLoaded(episode);
    }

}
