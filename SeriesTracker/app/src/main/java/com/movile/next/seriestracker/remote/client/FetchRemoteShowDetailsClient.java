package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.callbacks.SeasonListCallback;
import com.movile.next.seriestracker.callbacks.ShowCallback;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.service.FetchRemoteShowDetailsService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FetchRemoteShowDetailsClient {

    private static final String TAG = FetchRemoteShowDetailsClient.class.getSimpleName();
    private RestAdapter mAdapter;
    private ShowCallback mShowCallback;
    private SeasonListCallback mSeasonListCallback;

    public FetchRemoteShowDetailsClient(String endpoint, ShowCallback showCallback, SeasonListCallback seasonListCallback)
    {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mShowCallback = showCallback;
        mSeasonListCallback = seasonListCallback;
    }

    public void getShowDetails(String show)
    {
        FetchRemoteShowDetailsService service = mAdapter.create(FetchRemoteShowDetailsService.class);
        service.getShowDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mShowCallback.showLoadedCallback(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode");
            }
        });
    }

    public void getSeasonList(String show)
    {
        FetchRemoteShowDetailsService service = mAdapter.create(FetchRemoteShowDetailsService.class);
        service.getSeasonList(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mSeasonListCallback.seasonListLoadedCallback(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode");
            }
        });
    }
}
