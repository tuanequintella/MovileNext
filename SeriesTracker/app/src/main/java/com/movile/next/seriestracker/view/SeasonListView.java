package com.movile.next.seriestracker.view;

import com.movile.next.seriestracker.model.Season;

import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public interface SeasonListView {

    void onSeasonListLoaded(List<Season> seasonList);
}
