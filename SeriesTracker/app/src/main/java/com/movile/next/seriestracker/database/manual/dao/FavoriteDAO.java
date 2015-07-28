package com.movile.next.seriestracker.database.manual.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.movile.next.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.next.seriestracker.database.manual.helper.ProviderUriHelper;
import com.movile.next.seriestracker.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDAO {
    private Context mContext;

    public FavoriteDAO(Context context) {
        mContext = context;
    }

    public Cursor all() {
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
        Cursor result = mContext.getContentResolver().query(uri, null, null, null, null, null);
        return result;
    }

    public Favorite find(String slug) {
        Cursor cursor = null;
        Favorite result = null;

        try {
            Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
            cursor = mContext.getContentResolver().query(uri, null, FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{slug}, null, null);

            if (cursor.moveToFirst()) {
                FavoriteEntity entity = new FavoriteEntity().fromCursor(cursor);
                result = new Favorite(entity.slug(), entity.title());
            }
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void save(Favorite favorite) {
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);

        ContentValues values = new FavoriteEntity(favorite.slug(),favorite.title()).toContentValues();
        mContext.getContentResolver().insert(uri, values);
    }

    public void delete(Favorite favorite) {
        Uri uri = new ProviderUriHelper(mContext).mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME);
        mContext.getContentResolver().delete(uri, FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{favorite.slug()});
    }
}
