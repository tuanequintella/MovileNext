package com.movile.next.seriestracker.view;

import com.movile.next.seriestracker.model.Episode;

import java.util.ArrayList;

public interface SeasonDetailsView {
    void onSeasonLoaded(ArrayList<Episode> episodeList);
}
