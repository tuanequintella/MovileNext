package com.movile.next.seriestracker.presenter;

import com.movile.next.seriestracker.callbacks.SeasonListCallback;
import com.movile.next.seriestracker.callbacks.ShowCallback;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteShowDetailsClient;
import com.movile.next.seriestracker.view.ShowDetailsView;

import java.util.List;


public class ShowDetailsPresenter implements ShowCallback{

    ShowDetailsView mView;

    public ShowDetailsPresenter(ShowDetailsView view) {
        mView = view;
    }

    public void loadShowDetails(String show) {
        new FetchRemoteShowDetailsClient(ApiConfiguration.API_URL_BASE, this, null).getShowDetails(show);
    }

    @Override
    public void showLoadedCallback(Show show) {
        mView.onShowLoaded(show);
    }
}
