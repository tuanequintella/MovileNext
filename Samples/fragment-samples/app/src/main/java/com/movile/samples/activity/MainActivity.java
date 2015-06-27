package com.movile.samples.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.movile.samples.R;
import com.movile.samples.adapter.ContentPageAdapter;
import com.movile.samples.fragment.FirstContentFragment;
import com.movile.samples.fragment.NavigationFragment;
import com.movile.samples.fragment.SecondContentFragment;

public class MainActivity extends AppCompatActivity implements NavigationFragment.OnNavigationOptionSelected {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        configureContentPager();
    }

    private void configureContentPager() {
        ViewPager pager = (ViewPager) findViewById(R.id.main_pager);
        pager.setAdapter(new ContentPageAdapter(getSupportFragmentManager(), this));
    }

    @Override
    public void onFirstOptionSelected() {
        changeContent(new FirstContentFragment());
    }

    @Override
    public void onSecondOptionSelected() {
        changeContent(new SecondContentFragment());
    }

    @Override
    public void onClearOptionSelected() {
        if (mCurrentFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(mCurrentFragment);
            transaction.commit();
        }
    }

    private void changeContent(Fragment contentFragment) {
        mCurrentFragment = contentFragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_content, contentFragment);
        transaction.commit();
    }

}
