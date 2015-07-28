package com.movile.next.seriestracker.remote.client;

import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.remote.service.FetchRemoteUpdatesService;

import retrofit.RestAdapter;

/**
 * Created by movile on 28/06/15.
 */
public class FetchRemoteUpdatesClient {
    private RestAdapter mAdapter;

    public FetchRemoteUpdatesClient(String endpoint)
    {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    public ShowUpdate getUpdates()
    {
        FetchRemoteUpdatesService service = mAdapter.create(FetchRemoteUpdatesService.class);
        return service.getUpdates();
    }
}
