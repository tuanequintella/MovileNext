package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.callbacks.EpisodeCallback;
import com.movile.next.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.service.FetchRemoteEpisodeDetailsService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchRemoteEpisodeDetailsClient {

    private static final String TAG = FetchRemoteEpisodeDetailsClient.class.getSimpleName();
    private RestAdapter mAdapter;
    private EpisodeCallback mCallback;

    public FetchRemoteEpisodeDetailsClient(String endpoint, EpisodeCallback callback)
    {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getEpisodeDetails(String show, Long season, Long episode)
    {
        FetchRemoteEpisodeDetailsService service = mAdapter.create(FetchRemoteEpisodeDetailsService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mCallback.onEpisodeLoadedCallback(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode");
            }
        });
    }
}
