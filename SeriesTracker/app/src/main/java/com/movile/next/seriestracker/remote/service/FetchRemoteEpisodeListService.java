
package com.movile.next.seriestracker.remote.service;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.ApiConfiguration;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface FetchRemoteEpisodeListService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/?extended=full,images")
    void getEpisodeList(
            @Path("show") String show,
            @Path("season") Long season,
            Callback<ArrayList<Episode>> callback);

}