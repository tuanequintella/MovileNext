package com.movile.next.seriestracker.activity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowDetailsPagerAdapter;
import com.movile.next.seriestracker.listener.OnSeasonClickListener;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.view.ShowDetailsView;

public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView, OnSeasonClickListener {

    private ShowDetailsPresenter mPresenter;
    private ViewPager mContentPager;
    private String show = "game-of-thrones";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        configureToolbar();
        showLoading();
        mPresenter = new ShowDetailsPresenter(this);
        mPresenter.loadShowDetails(show);
    }

    @Override
    public void onShowLoaded(Show show) {
        hideLoading();
        getSupportActionBar().setTitle(show.title());
        mContentPager = (ViewPager) findViewById(R.id.show_details_content);
        mContentPager.setAdapter(new ShowDetailsPagerAdapter(getSupportFragmentManager(), show, this));
    }

    @Override
    public void seasonClick(Season season) {
        //entrar na season
    }
}
