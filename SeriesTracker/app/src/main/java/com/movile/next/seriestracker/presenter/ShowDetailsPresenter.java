package com.movile.next.seriestracker.presenter;

import android.content.Context;

import com.movile.next.seriestracker.callbacks.FavoriteShowCallback;
import com.movile.next.seriestracker.callbacks.ShowCallback;
import com.movile.next.seriestracker.loaders.CheckFavoriteShowAsyncTask;
import com.movile.next.seriestracker.loaders.ToggleFavoriteShowAsyncTask;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteShowDetailsClient;
import com.movile.next.seriestracker.view.ShowDetailsView;


public class ShowDetailsPresenter implements ShowCallback, FavoriteShowCallback {
    Context mContext;
    ShowDetailsView mView;

    public ShowDetailsPresenter(Context context, ShowDetailsView view) {
        mContext = context;
        mView = view;
    }

    public void loadShowDetails(String show) {
        new FetchRemoteShowDetailsClient(ApiConfiguration.API_URL_BASE, this, null).getShowDetails(show);
    }

    @Override
    public void showLoadedCallback(Show show) {
        mView.onShowLoaded(show);
    }

    public void checkFavoriteStatus(String show) {
        new CheckFavoriteShowAsyncTask(mContext, show, this).execute();
    }

    public void toggleFavoriteStatus(Show show) {
        new ToggleFavoriteShowAsyncTask(mContext, show, this).execute();
    }

    @Override
    public void setFavorite(boolean isFav) {
        mView.setFavorite(isFav);
    }
}
