package com.movile.next.seriestracker.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.adapter.EpisodeListAdapter;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.util.ArrayList;

public class SeasonDetailsActivity extends Activity implements SeasonDetailsView {

    SeasonDetailsPresenter mPresenter;
    EpisodeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);

        View header = LayoutInflater.from(this).inflate(R.layout.season_details_header, null);

        ListView view = (ListView) findViewById(R.id.season_listview);
        view.addHeaderView(header, null, false);

        ArrayList<Episode> dataSource = new ArrayList<>();

        adapter = new EpisodeListAdapter(this, dataSource);
        view.setAdapter(adapter);

        mPresenter = new SeasonDetailsPresenter(this);
        mPresenter.loadEpisodesFromSeason("game-of-thrones", (long)3);
        //mPresenter.mockLoadEpisodesFromSeason();
    }

    @Override
    public void onSeasonLoaded(ArrayList<Episode> listEpisode) {
        adapter.addAll(listEpisode);
        adapter.notifyDataSetChanged();
    }
}
