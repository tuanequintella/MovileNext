package com.movile.next.seriestracker.loaders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.movile.next.seriestracker.callbacks.FavoritesLoadedCallback;

public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    Context mContext;
    FavoritesLoadedCallback mCallback;

    public FavoritesLoaderCallback(Context context, FavoritesLoadedCallback callback) {
        mContext = context;
        mCallback = callback;
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new FavoritesLoader(mContext);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCallback.favoritesLoadedCallback(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) { };

}
