package com.movile.next.seriestracker.database.dbflow.dao;

import android.database.Cursor;

import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity;
import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity$Table;
import com.movile.next.seriestracker.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDAO {

    public FavoriteDAO() {
        //Empty constructor
    }

    public Cursor all() {
       return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
    }

    public Favorite find(String slug) {
        FavoriteEntity entity = new Select()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .querySingle();

        Favorite favorite = null;
        if(entity != null) {
            favorite = new Favorite(entity.slug(), entity.title());
        }
        return favorite;
    }

    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(),favorite.title());
        entity.save();
    }

    public void delete(Favorite favorite) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(favorite.slug()))
                .queryClose();
    }
}

