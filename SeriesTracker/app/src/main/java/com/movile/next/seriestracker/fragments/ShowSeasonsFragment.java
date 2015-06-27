package com.movile.next.seriestracker.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.adapter.SeasonListAdapter;
import com.movile.next.seriestracker.listener.OnSeasonClickListener;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.SeasonListPresenter;
import com.movile.next.seriestracker.view.SeasonListView;

import java.util.List;

public class ShowSeasonsFragment extends Fragment implements SeasonListView {
    private SeasonListAdapter mAdapter;
    private Show mShow;
    private SeasonListPresenter mPresenter;
    private OnSeasonClickListener mListener;

    public ShowSeasonsFragment() {
        //Empty constructor
    };

    public ShowSeasonsFragment(Show show, OnSeasonClickListener listener) {
        mShow = show;
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_seasons_fragment, container, false);
        configureSeasonListView(view);
        return view;
    }

    private void configureSeasonListView(View fragment) {
        RecyclerView view = (RecyclerView) fragment.findViewById(R.id.show_seasons_list_view);
        view.setLayoutManager(new LinearLayoutManager(fragment.getContext(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new SeasonListAdapter(getActivity(), R.layout.show_seasons_list_item_fragment, mListener);
        view.setAdapter(mAdapter);

        populateSeasonListView();
    }

    private void populateSeasonListView() {
        //populate with seasons
        mPresenter = new SeasonListPresenter(this);
        mPresenter.loadShowSeasonList(mShow.ids().slug());
    }

    @Override
    public void onSeasonListLoaded(List<Season> seasonList) {
        mAdapter.updateSeasons(seasonList);
    }
}
