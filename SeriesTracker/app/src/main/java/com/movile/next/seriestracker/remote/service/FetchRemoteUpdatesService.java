package com.movile.next.seriestracker.remote.service;

import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.service.UpdatesService;

import retrofit.http.GET;

public interface FetchRemoteUpdatesService {

    @GET("/latestUpdate.json")
    ShowUpdate getUpdates();

}