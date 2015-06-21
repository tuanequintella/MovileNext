package com.movile.next.seriestracker.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ViewPagerAdapter;
import com.movile.next.seriestracker.fragments.ShowInformationFragment;
import com.movile.next.seriestracker.fragments.ShowSeasonsFragment;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.view.ShowDetailsView;

public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView {

    private ShowDetailsPresenter mPresenter;
    private ViewPager mContentPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        configureToolbar();
        getSupportActionBar().setTitle("Game Of Thrones");
        //showLoading();

        //mPresenter = new ShowDetailsPresenter(this);

        // Mock
        Show mockShow = new Show();
        mockShow.setTitle("game-of-thrones");

        mContentPager = (ViewPager) findViewById(R.id.show_details_content);
        mContentPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mockShow));

        //mPresenter.loadShowDetails(mockShow);
    }

    @Override
    public void onShowLoaded(Show show) {
        hideLoading();
    }
}
