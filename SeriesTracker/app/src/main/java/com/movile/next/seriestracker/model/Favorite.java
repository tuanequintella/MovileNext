package com.movile.next.seriestracker.model;

import java.io.Serializable;

/**
 * Created by movile on 04/07/15.
 */
public class Favorite implements Serializable {
    private String mSlug;
    private String mTitle;

    public Favorite() {
    }

    public Favorite(String slug, String title) {
        mSlug = slug;
        mTitle = title;
    }

    public String slug() {
        return mSlug;
    }

    public String title() {
        return mTitle;
    }
}
