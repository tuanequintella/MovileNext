package com.movile.next.seriestracker.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
    FloatingActionButton mFavoriteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureFavoriteButton();
        loadBundle();
        configureToolbar();
        showLoading();
        mPresenter = new ShowDetailsPresenter(this, this);
        mPresenter.loadShowDetails(mShowSlug);
        mPresenter.checkFavoriteStatus(mShowSlug);
    }

    public void loadBundle() {
        Bundle bundle = getIntent().getExtras();
        mShowSlug = bundle.getString(EXTRA_SHOW);
    }

    @Override
    public void onShowLoaded(Show show) {
        hideLoading();
        getSupportActionBar().setTitle(show.title());
        ImageView showScreenshot = (ImageView) findViewById(R.id.show_screenshot);
        //Glide.with(this).load(show.images().poster()).centerCrop().into(showScreenshot);

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

    @Override
    public void setFavorite(boolean isFav) {
        FloatingActionButton favoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite_button);
        if(isFav) {
            favoriteView.setImageResource(R.drawable.show_details_favorite_on);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        } else {
            favoriteView.setImageResource(R.drawable.show_details_favorite_off);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_third));
        }
    }

    private void configureFavoriteButton() {
        mFavoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite_button);
        mFavoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toggleFavoriteStatus(mShow);
            }
        });
    }
}
