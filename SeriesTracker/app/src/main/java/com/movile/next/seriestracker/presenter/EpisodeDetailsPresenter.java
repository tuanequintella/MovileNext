package com.movile.next.seriestracker.presenter;

import com.movile.next.seriestracker.callbacks.EpisodeCallback;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteEpisodeDetailsClient;
import com.movile.next.seriestracker.view.EpisodeDetailsView;

public class EpisodeDetailsPresenter implements EpisodeCallback{

    private EpisodeDetailsView mView;

    public EpisodeDetailsPresenter(EpisodeDetailsView view) {
        mView = view;
    }

    public void loadEpisode(String show, Long season, Long episode)
    {
        new FetchRemoteEpisodeDetailsClient(ApiConfiguration.API_URL_BASE, this)
                .getEpisodeDetails(show, season, episode);
    }

    @Override
    public void onEpisodeLoadedCallback(Episode episode) {
        mView.onEpisodeLoaded(episode);
    }
}
