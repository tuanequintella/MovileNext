package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.callbacks.SeasonEpisodeListCallback;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.service.FetchRemoteEpisodeListService;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchRemoteEpisodeListClient {
    private static final String TAG = FetchRemoteEpisodeDetailsClient.class.getSimpleName();
    private RestAdapter mAdapter;
    private SeasonEpisodeListCallback mCallback;

    public FetchRemoteEpisodeListClient(String endpoint, SeasonEpisodeListCallback callback)
    {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getEpisodeList(String show, Long season)
    {
        FetchRemoteEpisodeListService service = mAdapter.create(FetchRemoteEpisodeListService.class);
        service.getEpisodeList(show, season, new Callback<ArrayList<Episode>>() {
            @Override
            public void success(ArrayList<Episode> episodeList, Response response) {
                mCallback.onSeasonLoadedCallback(episodeList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode");
            }
        });
    }

    public void mockGetEpisodeList()
    {
        ArrayList<Episode> dataSource = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Episode ep = new Episode();
            ep.setNumber((long)i);
            ep.setTitle("Title " + i);
            dataSource.add(ep);
        }

        mCallback.onSeasonLoadedCallback(dataSource);
    }
}
