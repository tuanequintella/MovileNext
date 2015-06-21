package com.movile.next.seriestracker.presenter;

import com.movile.next.seriestracker.callbacks.ShowCallback;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteShowDetailsClient;
import com.movile.next.seriestracker.view.ShowDetailsView;


public class ShowDetailsPresenter implements ShowCallback{

    ShowDetailsView mView;

    public ShowDetailsPresenter(ShowDetailsView view) {
        mView = view;
    }

    public void loadShowDetails(Show show) {
        new FetchRemoteShowDetailsClient(ApiConfiguration.API_URL_BASE, this);
    }

    @Override
    public void onShowLoadedCallback(Show show) {
        mView.onShowLoaded(show);
    }
}
