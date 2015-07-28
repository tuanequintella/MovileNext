package com.movile.next.seriestracker.callbacks;

import com.movile.next.seriestracker.model.Season;

import java.util.List;

public interface SeasonListCallback {
    void seasonListLoadedCallback(List<Season> seasonList);
}
