package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.callbacks.ShowCallback;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.service.FetchRemoteShowDetailsService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FetchRemoteShowDetailsClient {

    private static final String TAG = FetchRemoteShowDetailsClient.class.getSimpleName();
    private RestAdapter mAdapter;
    private ShowCallback mCallback;

    public FetchRemoteShowDetailsClient(String endpoint, ShowCallback callback)
    {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getShowDetails(String show)
    {
        FetchRemoteShowDetailsService service = mAdapter.create(FetchRemoteShowDetailsService.class);
        service.getEpisodeDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mCallback.onShowLoadedCallback(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode");
            }
        });
    }
}
