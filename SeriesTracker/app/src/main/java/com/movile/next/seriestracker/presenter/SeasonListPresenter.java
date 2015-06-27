package com.movile.next.seriestracker.presenter;

import com.movile.next.seriestracker.callbacks.SeasonListCallback;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteShowDetailsClient;
import com.movile.next.seriestracker.view.SeasonListView;

import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class SeasonListPresenter implements SeasonListCallback {

    SeasonListView mView;

    public SeasonListPresenter(SeasonListView view) {
        mView = view;
    }

    public void loadShowSeasonList(String show) {
        new FetchRemoteShowDetailsClient(ApiConfiguration.API_URL_BASE, null, this).getSeasonList(show);
    }

    @Override
    public void seasonListLoadedCallback(List<Season> seasonList) {
        mView.onSeasonListLoaded(seasonList);
    }
}
