package com.movile.next.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.EpisodeListAdapter;
import com.movile.next.seriestracker.listener.OnEpisodeClickListener;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.text.MessageFormat;
import java.util.ArrayList;

public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnEpisodeClickListener {

    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SEASON = "season";
    public static final String EXTRA_EPISODE = "episode";

    SeasonDetailsPresenter mPresenter;
    EpisodeListAdapter adapter;
    String mShowSlug = "";
    Long mSeasonNumber = 1l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);

        View header = LayoutInflater.from(this).inflate(R.layout.season_details_header, null);

        ListView view = (ListView) findViewById(R.id.season_details_listview);
        view.addHeaderView(header, null, false);

        adapter = new EpisodeListAdapter(this, this);
        view.setAdapter(adapter);

        configureToolbar();
        showLoading();

        loadBundle();
    }

    public void loadBundle() {
        Bundle bundle = getIntent().getExtras();
        mShowSlug = bundle.getString(EXTRA_SHOW);
        mSeasonNumber = (Long) bundle.get(EXTRA_SEASON);

        mPresenter = new SeasonDetailsPresenter(this);
        mPresenter.loadEpisodesFromSeason(mShowSlug, mSeasonNumber);

        getSupportActionBar().setTitle(MessageFormat.format("Season {0}",mSeasonNumber));
    }

    @Override
    public void onSeasonLoaded(ArrayList<Episode> listEpisode) {
        adapter.addAll(listEpisode);
        adapter.notifyDataSetChanged();
        ImageView seasonScreenshot = (ImageView) findViewById(R.id.season_details_screenshot);
        //Load season image? Glide.with(this).load()
        hideLoading();
    }

    @Override
    public void episodeClick(Episode ep) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, mShowSlug);
        intent.putExtra(EXTRA_SEASON, mSeasonNumber);
        intent.putExtra(EXTRA_EPISODE, ep.number());
        startActivity(intent);
    }
}
