package com.movile.next.seriestracker.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.next.seriestracker.util.FormatUtil;
import com.movile.next.seriestracker.view.EpisodeDetailsView;

import java.text.MessageFormat;

public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView {

    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SEASON = "season";
    public static final String EXTRA_EPISODE = "episode";

    EpisodeDetailsPresenter mPresenter;
    TextView mTextView;
    ImageView mImageView;

    private String mShow = "";
    private Long mSeason = 1l;
    private Long mEpisode = 1l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        loadBundle();

        // RETROFIT FETCH
        mPresenter = new EpisodeDetailsPresenter(this);
        mPresenter.loadEpisode(mShow, mSeason, mEpisode);

        configureToolbar();
        showLoading();
    }

    public void loadBundle() {
        Bundle bundle = getIntent().getExtras();
        mShow = bundle.getString(EXTRA_SHOW);
        mSeason = bundle.getLong(EXTRA_SEASON);
        mEpisode = bundle.getLong(EXTRA_EPISODE);

        getSupportActionBar().setTitle(MessageFormat.format("Episode {0}", mEpisode));
    }

    public void onEpisodeLoaded(Episode episode){

        mTextView = (TextView) findViewById(R.id.episode_title);
        mTextView.setText(episode.title());

        mTextView = (TextView) findViewById(R.id.episode_summary);
        mTextView.setText(episode.overview());

        mTextView = (TextView) findViewById(R.id.episode_datetime);
        mTextView.setText(FormatUtil.formatDate(FormatUtil.formatDate(episode.firstAired())));

        mImageView = (ImageView) findViewById(R.id.episode_screenshot);
        Glide.with(this).load(episode.images().screenshot().get(Images.ImageSize.THUMB))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(mImageView);

        hideLoading();
    }

}
