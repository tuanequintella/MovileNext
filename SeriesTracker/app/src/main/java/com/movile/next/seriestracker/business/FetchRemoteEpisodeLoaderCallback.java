package com.movile.next.seriestracker.business;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.next.seriestracker.activity.OnEpisodeLoadedListener;
import com.movile.next.seriestracker.model.Episode;

public class FetchRemoteEpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode>{

    private static OnEpisodeLoadedListener mListener;
    private Context mContext;

    public FetchRemoteEpisodeLoaderCallback (OnEpisodeLoadedListener listener, Context context) {
        mListener = listener;
        mContext = context;
    }

    public Loader<Episode> onCreateLoader(int id, Bundle bundle) {
        return new FetchRemoteEpisodeDetails(mContext);
    }

    public void onLoadFinished(Loader<Episode> loader, Episode episode) {
        mListener.onEpisodeLoaded(episode);
    }

    public void onLoaderReset(Loader<Episode> loader) {

    }
}
