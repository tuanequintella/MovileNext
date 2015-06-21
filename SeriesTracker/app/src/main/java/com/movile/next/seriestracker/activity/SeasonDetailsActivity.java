package com.movile.next.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

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

    SeasonDetailsPresenter mPresenter;
    EpisodeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);

        View header = LayoutInflater.from(this).inflate(R.layout.season_details_header, null);

        ListView view = (ListView) findViewById(R.id.season_listview);
        view.addHeaderView(header, null, false);

        adapter = new EpisodeListAdapter(this, this);
        view.setAdapter(adapter);

        mPresenter = new SeasonDetailsPresenter(this);
        mPresenter.loadEpisodesFromSeason("game-of-thrones", (long) 1);
        //mPresenter.mockLoadEpisodesFromSeason();

        //
        configureToolbar();
        getSupportActionBar().setTitle(MessageFormat.format("Season {0}",1));
        showLoading();
    }

    @Override
    public void onSeasonLoaded(ArrayList<Episode> listEpisode) {
        adapter.addAll(listEpisode);
        adapter.notifyDataSetChanged();

        hideLoading();
    }

    @Override
    public void episodeClick(Episode ep) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, "game-of-thrones");
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SEASON, (long)1);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODE, ep.number());
        startActivity(intent);
    }
}
