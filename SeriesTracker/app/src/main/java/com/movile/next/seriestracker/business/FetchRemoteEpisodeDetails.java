package com.movile.next.seriestracker.business;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.util.ModelConverter;
import com.movile.next.seriestracker.model.Episode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;


public class FetchRemoteEpisodeDetails extends AsyncTaskLoader<Episode> {
    private Context mContext;

    public FetchRemoteEpisodeDetails(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Episode loadInBackground() {
        Episode episode = null;
        InputStreamReader reader = null;
        try {
            String show = "the-big-bang-theory";
            String season = "1";
            String epi = "3";
            String episode_url = MessageFormat.format(mContext.getString(R.string.api_url_episode), show, season, epi);
            HttpURLConnection connection = configureConnection(mContext, mContext.getString(R.string.api_url_base) + episode_url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e("TAG", "Error loading remote content", e);
        } finally {
            // Release InputStreamReader if used
        }

        return episode;
    }

    private HttpURLConnection configureConnection(Context context, String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setReadTimeout(mContext.getResources().getInteger(R.integer.api_timeout_read));
        connection.setConnectTimeout(mContext.getResources().getInteger(R.integer.api_timeout_connect));
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("trakt-api-version", mContext.getString(R.string.api_version));
        connection.setRequestProperty("trakt-api-key", mContext.getString(R.string.api_key));

        return connection;
    }
}
