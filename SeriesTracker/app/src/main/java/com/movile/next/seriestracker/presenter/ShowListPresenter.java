package com.movile.next.seriestracker.presenter;

import com.movile.next.seriestracker.callbacks.ShowListCallback;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.ApiConfiguration;
import com.movile.next.seriestracker.remote.client.FetchRemoteShowListClient;
import com.movile.next.seriestracker.view.ShowListView;

import java.util.ArrayList;

/**
 * Created by movile on 27/06/15.
 */
public class ShowListPresenter implements ShowListCallback{

    private ShowListView mView;

    public ShowListPresenter(ShowListView view){
        mView = view;
    }

    public void loadShowList() {
        new FetchRemoteShowListClient(ApiConfiguration.API_URL_BASE, this)
                .getShowList();
    }

    @Override
    public void onShowListLoadedCallback(ArrayList<Show> showList) {
        mView.onShowListLoaded(showList);
    }
}
