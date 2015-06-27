package com.movile.samples.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.samples.R;
import com.movile.samples.fragment.FirstContentFragment;
import com.movile.samples.fragment.SecondContentFragment;

public class ContentPageAdapter extends FragmentPagerAdapter {

    public static final int POSITION_FIRST_CONTENT = 0;
    public static final int POSITION_SECOND_CONTENT = 1;

    private Context mContext;

    public ContentPageAdapter(FragmentManager manager, Context context) {
        super(manager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == POSITION_FIRST_CONTENT) {
            return new FirstContentFragment();
        }
        if (position == POSITION_SECOND_CONTENT) {
            return new SecondContentFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == POSITION_FIRST_CONTENT) {
            return mContext.getString(R.string.navigation_first_content_label);
        }
        if (position == POSITION_SECOND_CONTENT) {
            return mContext.getString(R.string.navigation_second_content_label);
        }
        return null;
    }
}
