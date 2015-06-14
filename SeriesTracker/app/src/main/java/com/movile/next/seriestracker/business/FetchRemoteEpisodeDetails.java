package com.movile.next.seriestracker.business;

import android.content.AsyncTaskLoader;
import com.movile.next.seriestracker.util.ModelConverter;
import com.movile.next.seriestracker.model.Episode;


public class FetchRemoteEpisodeDetails extends AsyncTaskLoader<Episode> {

    public FetchRemoteEpisodeDetails() {

    }

    @Override
    protected Episode loadInBackground() {
        Episode episode = null;
        episode = new ModelConverter().toEpisode("{}");
        //TODO: Fetch episode from HTTP
        return episode;
    }
}
