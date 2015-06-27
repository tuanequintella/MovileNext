package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.callbacks.SeasonEpisodeListCallback;
import com.movile.next.seriestracker.callbacks.ShowListCallback;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.service.FetchRemoteEpisodeListService;
import com.movile.next.seriestracker.remote.service.FetchRemoteShowListService;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FetchRemoteShowListClient {
    private static final String TAG = FetchRemoteEpisodeDetailsClient.class.getSimpleName();
    private RestAdapter mAdapter;
    private ShowListCallback mCallback;

    public FetchRemoteShowListClient(String endpoint, ShowListCallback callback)
    {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getShowList()
    {
        FetchRemoteShowListService service = mAdapter.create(FetchRemoteShowListService.class);
        service.getShowList(new Callback<ArrayList<Show>>() {
            @Override
            public void success(ArrayList<Show> showList, Response response) {
                mCallback.onShowListLoadedCallback(showList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode");
            }
        });
    }
}
