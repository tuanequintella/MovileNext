package com.movile.next.seriestracker.presenter;


import com.movile.next.seriestracker.callbacks.SeasonEpisodeListCallback;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteEpisodeListClient;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.util.ArrayList;

public class SeasonDetailsPresenter implements SeasonEpisodeListCallback {

    private SeasonDetailsView mView;

    public SeasonDetailsPresenter(SeasonDetailsView view){
        mView = view;
    }

    public void loadEpisodesFromSeason(String show, Long season) {
        new FetchRemoteEpisodeListClient(ApiConfiguration.API_URL_BASE, this)
                .getEpisodeList(show, season);
    }

    @Override
    public void onSeasonLoadedCallback(ArrayList<Episode> episodeList) {
        mView.onSeasonLoaded(episodeList);
    }
}
