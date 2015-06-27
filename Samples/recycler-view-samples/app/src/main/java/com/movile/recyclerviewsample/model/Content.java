package com.movile.recyclerviewsample.model;

public class Content {

    private String mTitle;
    private String mDescription;

    public Content(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String title() {
        return mTitle;
    }

    public String description() {
        return mDescription;
    }

}
