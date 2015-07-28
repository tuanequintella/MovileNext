package com.movile.next.seriestracker.callbacks;

import com.movile.next.seriestracker.model.Episode;

import java.util.ArrayList;

public interface SeasonEpisodeListCallback {
    void onSeasonLoadedCallback(ArrayList<Episode> season);
}
