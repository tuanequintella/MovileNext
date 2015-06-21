package com.movile.next.seriestracker.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.next.seriestracker.util.FormatUtil;
import com.movile.next.seriestracker.view.EpisodeDetailsView;

public class EpisodeDetailsActivity extends Activity implements EpisodeDetailsView {

    EpisodeDetailsPresenter mPresenter;
    TextView mTextView;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        // RETROFIT FETCH
        mPresenter = new EpisodeDetailsPresenter(this);
        mPresenter.loadEpisode("game-of-thrones", Long.valueOf("3"), Long.valueOf("2"));
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
    }
}
