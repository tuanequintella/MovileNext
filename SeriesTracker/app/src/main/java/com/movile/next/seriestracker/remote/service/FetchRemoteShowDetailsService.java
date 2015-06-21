package com.movile.next.seriestracker.remote.service;

import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.ApiConfiguration;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface FetchRemoteShowDetailsService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            Callback<Show> callback);
}
