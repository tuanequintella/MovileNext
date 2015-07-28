package com.movile.next.seriestracker.model;

import com.google.gson.annotations.SerializedName;

public class Episode {

    private Long season;
    private Long number;
    private String title;
    private MediaIds ids;
    private String overview;
    private Double rating;
    private Long votes;
    @SerializedName("first_aired")
    private String firstAired;
    @SerializedName("available_translations")
    private String[] translations;
    private Images images;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long season() {
        return season;
    }

    public Long number() {
        return number;
    }

    public String title() {
        return title;
    }

    public MediaIds ids() {
        return ids;
    }

    public String overview() {
        return overview;
    }

    public Double rating() {
        return rating;
    }

    public Long votes() {
        return votes;
    }

    public String firstAired() {
        return firstAired;
    }

    public String[] translations() {
        return translations;
    }

    public Images images() {
        return images;
    }

}