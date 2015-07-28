package com.movile.next.seriestracker.callbacks;

import com.movile.next.seriestracker.model.Show;

import java.util.ArrayList;

/**
 * Created by movile on 27/06/15.
 */
public interface ShowListCallback {
    void onShowListLoadedCallback(ArrayList<Show> season);
}
