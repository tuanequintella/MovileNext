package com.movile.next.seriestracker.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.business.FetchLocalEpisodeDetails;
import com.movile.next.seriestracker.business.FetchRemoteEpisodeLoaderCallback;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.util.FormatUtil;


public class EpisodeDetailsActivity extends Activity implements OnEpisodeLoadedListener {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
        // LOCAL FETCH
       // new FetchLocalEpisodeDetails(this).execute(this);

        // REMOTE FETCH
        getLoaderManager().initLoader(0, null, new FetchRemoteEpisodeLoaderCallback(this, this)).forceLoad();
    }


    public void onEpisodeLoaded(Episode episode){
        mTextView = (TextView) findViewById(R.id.episode_title);
        mTextView.setText(episode.title());

        mTextView = (TextView) findViewById(R.id.episode_summary);
        mTextView.setText(episode.overview());

        mTextView = (TextView) findViewById(R.id.episode_datetime);
        mTextView.setText(FormatUtil.formatDate(FormatUtil.formatDate(episode.firstAired())));
    }
}
