package com.movile.next.seriestracker.database.dbflow.entity;

import android.provider.BaseColumns;

import com.movile.next.seriestracker.database.dbflow.SeriesTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntity extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public FavoriteEntity() {

    }

    public FavoriteEntity(String s, String t) {
        slug = s;
        title = t;
    }

    public String slug() { return slug; }
    public String title() { return title; }
}
