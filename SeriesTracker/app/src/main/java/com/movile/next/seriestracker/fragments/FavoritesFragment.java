package com.movile.next.seriestracker.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.ShowDetailsActivity;
import com.movile.next.seriestracker.adapter.FavoritesAdapter;
import com.movile.next.seriestracker.listener.OnFavoriteClickListener;
import com.movile.next.seriestracker.presenter.FavoritesPresenter;
import com.movile.next.seriestracker.view.FavoritesView;

public class FavoritesFragment extends Fragment implements FavoritesView, OnFavoriteClickListener {
    FavoritesAdapter mAdapter;
    FavoritesPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment, container, false);
        configureFavoriteListView(view);
        return view;
    }

    private void configureFavoriteListView(View fragment) {
        ListView view = (ListView) fragment.findViewById(R.id.favorites_list_view);
        mAdapter = new FavoritesAdapter(getActivity(), this);
        view.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        populateFavoriteListView();
    }

    private void populateFavoriteListView() {
        //populate with favorites
        mPresenter = new FavoritesPresenter(getActivity(), this);
        mPresenter.loadFavorites(getLoaderManager());
    }

    @Override
    public void onFavoritesLoaded(Cursor favoritesCursor) {
        mAdapter.updateFavorites(favoritesCursor);
    }

    @Override
    public void favoriteClick(String showSlug) {
        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW, showSlug);
        getActivity().startActivity(intent);
    }
}
