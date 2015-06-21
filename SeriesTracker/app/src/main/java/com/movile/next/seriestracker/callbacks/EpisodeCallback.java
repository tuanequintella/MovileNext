package com.movile.next.seriestracker.callbacks;

import com.movile.next.seriestracker.model.Episode;

public interface EpisodeCallback {

    void onEpisodeLoadedCallback(Episode episode);
}
