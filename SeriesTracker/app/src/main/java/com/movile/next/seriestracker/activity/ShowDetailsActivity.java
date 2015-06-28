package com.movile.next.seriestracker.activity;

import android.content.Intent;
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

    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SEASON = "season";
    private ShowDetailsPresenter mPresenter;
    private ViewPager mContentPager;

    String mShowSlug = "";
    Show mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        loadBundle();
        configureToolbar();
        showLoading();
        mPresenter = new ShowDetailsPresenter(this);
        mPresenter.loadShowDetails(mShowSlug);
    }

    public void loadBundle() {
        Bundle bundle = getIntent().getExtras();
        mShowSlug = bundle.getString(EXTRA_SHOW);
    }

    @Override
    public void onShowLoaded(Show show) {
        hideLoading();
        getSupportActionBar().setTitle(show.title());
        //set show screenshot
        mShow = show;
        mContentPager = (ViewPager) findViewById(R.id.show_details_content);
        mContentPager.setAdapter(new ShowDetailsPagerAdapter(getSupportFragmentManager(), mShow, this));
    }

    @Override
    public void seasonClick(Season season) {
        Intent intent = new Intent(this, SeasonDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, mShowSlug);
        intent.putExtra(EXTRA_SEASON, season.number());
        startActivity(intent);
    }
}
