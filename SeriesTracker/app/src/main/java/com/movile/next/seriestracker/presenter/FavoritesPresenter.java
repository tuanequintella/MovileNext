package com.movile.next.seriestracker.presenter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;

import com.movile.next.seriestracker.callbacks.FavoritesLoadedCallback;
import com.movile.next.seriestracker.loaders.FavoritesLoaderCallback;
import com.movile.next.seriestracker.view.FavoritesView;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesPresenter implements FavoritesLoadedCallback {

    Context mContext;
    FavoritesView mView;

    public FavoritesPresenter(Context context, FavoritesView view){
        mContext = context;
        mView = view;
    }

    public void loadFavorites(LoaderManager loaderManager) {
       loaderManager.initLoader(0, null, new FavoritesLoaderCallback(mContext, this)).forceLoad();
    }

    @Override
    public void favoritesLoadedCallback(Cursor favoritesCursor) {
        mView.onFavoritesLoaded(favoritesCursor);
    }
}
