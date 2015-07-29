package com.movile.next.seriestracker.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import com.movile.next.seriestracker.model.Images;
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
        mShow = show;

        ImageView showScreenshot = (ImageView) findViewById(R.id.show_screenshot);
        Glide.with(this).load(mShow.images().poster().get(Images.ImageSize.MEDIUM))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(showScreenshot);

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
        if(isFav) {
            mFavoriteView.setImageResource(R.drawable.show_details_favorite_on);
            mFavoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        } else {
            mFavoriteView.setImageResource(R.drawable.show_details_favorite_off);
            mFavoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_third));
        }
    }

    private void configureFavoriteButton() {
        mFavoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite_button);
        ObjectAnimator animationX1 = ObjectAnimator.ofFloat(mFavoriteView, "scaleX", 1, 0.6f);
        ObjectAnimator animationY1 = ObjectAnimator.ofFloat(mFavoriteView, "scaleY", 1, 0.6f);

        ObjectAnimator animationX2 = ObjectAnimator.ofFloat(mFavoriteView, "scaleX", 0.6f, 1);
        ObjectAnimator animationY2 = ObjectAnimator.ofFloat(mFavoriteView, "scaleY", 0.6f, 1);

        final AnimatorSet animationSet = new AnimatorSet();
        animationSet.playTogether(animationX1, animationY1);
        animationSet.playTogether(animationX2, animationY2);
        animationSet.playSequentially(animationX1, animationX2);
        animationSet.setDuration(100);

        mFavoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationSet.start();
                mPresenter.toggleFavoriteStatus(mShow);
            }
        });
    }
}
