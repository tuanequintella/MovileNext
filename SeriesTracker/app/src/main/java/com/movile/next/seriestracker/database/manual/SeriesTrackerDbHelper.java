package com.movile.next.seriestracker.database.manual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.movile.next.seriestracker.database.manual.entity.FavoriteEntity;

public class SeriesTrackerDbHelper extends SQLiteOpenHelper {

    public SeriesTrackerDbHelper(Context context) {
        super(context, DatabaseConfiguration.NAME, null, DatabaseConfiguration.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.createSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.dropSql());
        onCreate(database);
    }

}
