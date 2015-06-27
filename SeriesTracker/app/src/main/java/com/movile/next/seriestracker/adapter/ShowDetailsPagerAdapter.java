package com.movile.next.seriestracker.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.next.seriestracker.fragments.ShowInformationFragment;
import com.movile.next.seriestracker.fragments.ShowSeasonsFragment;
import com.movile.next.seriestracker.model.Show;

public class ShowDetailsPagerAdapter extends FragmentPagerAdapter {

    public static final int INFO_TAB = 0;
    public static final int SEASONS_TAB = 1;

    private Show mShow;

    public ShowDetailsPagerAdapter(FragmentManager fragmentManager, Show show) {
        super(fragmentManager);
        mShow = show;
    }

    public CharSequence getPageTitle(int position) {

        if (position == INFO_TAB)
            return "INFO";
        else if (position == SEASONS_TAB)
            return "SEASONS";

        return "";
    }

    @Override
    public Fragment getItem(int position) {

        if (position == INFO_TAB)
            return new ShowInformationFragment();
        else if (position == SEASONS_TAB)
            return new ShowSeasonsFragment();

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
