package com.movile.next.seriestracker.callbacks;

import android.database.Cursor;

/**
 * Created by movile on 05/07/15.
 */
public interface FavoritesLoadedCallback {
    void favoritesLoadedCallback(Cursor favoritesCursor);
}
