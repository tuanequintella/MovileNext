package com.movile.next.seriestracker.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowListAdapter;
import com.movile.next.seriestracker.listener.OnShowClickListener;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowListPresenter;
import com.movile.next.seriestracker.service.UpdatesService;
import com.movile.next.seriestracker.view.ShowListView;

import java.util.ArrayList;

public class ShowListActivity extends BaseNavigationToolbarActivity implements ShowListView, OnShowClickListener {

    ShowListPresenter mPresenter;
    ShowListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list_activity);

        GridView view = (GridView) findViewById(R.id.shows_grid_view);

        mAdapter = new ShowListAdapter(this, this);
        view.setAdapter(mAdapter);

        mPresenter = new ShowListPresenter(this);
        mPresenter.loadShowList();

        configureToolbar();

        showLoading();
    }

    @Override
    public void onShowListLoaded(ArrayList<Show> showList) {
        mAdapter.addAll(showList);
        mAdapter.notifyDataSetChanged();
        getSupportActionBar().setTitle(R.string.app_name);
        hideLoading();
    }

    @Override
    public void showClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, show.ids().slug());
        startActivity(intent);
    }
}
