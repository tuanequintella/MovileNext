package com.movile.next.seriestracker.loaders;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.next.seriestracker.callbacks.FavoriteShowCallback;
import com.movile.next.seriestracker.database.dbflow.dao.FavoriteDAO;
import com.movile.next.seriestracker.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */

public class CheckFavoriteShowAsyncTask extends AsyncTask {
    FavoriteShowCallback mCallback;
    String mShowSlug;
    Context mContext;

    public CheckFavoriteShowAsyncTask(Context context, String showSlug, FavoriteShowCallback callback) {
        mContext = context;
        mShowSlug = showSlug;
        mCallback = callback;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Favorite favorite = new FavoriteDAO().find(mShowSlug);
        return favorite;
    }

    @Override
    protected void onPostExecute(Object result) {
        if(result == null) {
            mCallback.setFavorite(false);
        } else {
            mCallback.setFavorite(true);
        }
    }
}
