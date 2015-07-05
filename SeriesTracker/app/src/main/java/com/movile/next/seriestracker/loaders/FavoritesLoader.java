package com.movile.next.seriestracker.loaders;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.movile.next.seriestracker.database.dbflow.dao.FavoriteDAO;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesLoader extends CursorLoader {
    Context mContext;

    public FavoritesLoader(Context context) {
        super(context);
        mContext = context;
    }

    public Cursor loadInBackground() {
        Cursor favoritesCursor = new FavoriteDAO().all();
        return favoritesCursor;
    }
}
