package com.movile.next.seriestracker.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.next.seriestracker.callbacks.FavoriteShowCallback;
import com.movile.next.seriestracker.database.dbflow.dao.FavoriteDAO;
import com.movile.next.seriestracker.model.Favorite;
import com.movile.next.seriestracker.model.Show;

/**
 * Created by movile on 04/07/15.
 */

public class ToggleFavoriteShowAsyncTask extends AsyncTask {
    FavoriteShowCallback mCallback;
    Show mShow;
    Context mContext;

    public ToggleFavoriteShowAsyncTask(Context context, Show show, FavoriteShowCallback callback) {
        mContext = context;
        mShow = show;
        mCallback = callback;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        FavoriteDAO dao = new FavoriteDAO();
        Favorite favorite = dao.find(mShow.ids().slug());

        if(favorite == null) {
           favorite = new Favorite(mShow.ids().slug(), mShow.title());
            //insert
            dao.save(favorite);
        } else {
            //delete
            dao.delete(favorite);
            favorite = null;
        }

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
