
package com.movile.next.seriestracker.remote.service;

import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.ApiConfiguration;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface FetchRemoteShowListService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/popular?limit=50&extended=full,images")
    void getShowList(Callback<ArrayList<Show>> callback);

}