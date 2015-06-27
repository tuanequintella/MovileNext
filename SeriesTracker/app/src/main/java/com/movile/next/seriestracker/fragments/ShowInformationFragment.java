package com.movile.next.seriestracker.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Show;

public class ShowInformationFragment extends Fragment {

    private Show mShow;

    public ShowInformationFragment(Show show) {
        mShow = show;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.show_information_fragment, container, false);
    }

}
