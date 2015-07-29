package com.movile.next.seriestracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Show;

public class ShowInformationFragment extends Fragment {

    private Show mShow;

    public ShowInformationFragment() {
        //Empty constructor required
    }

    public ShowInformationFragment(Show show) {
        mShow = show;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_information_fragment, container, false);
        setShowDetailsInfo(view);
        return view;

    }

    private void setShowDetailsInfo(View view) {
        TextView textView = (TextView) view.findViewById(R.id.show_summary_text);
        textView.setText(mShow.overview());

        textView = (TextView) view.findViewById(R.id.show_genre_text);
        textView.setText(mShow.genres()[0]);

        textView = (TextView) view.findViewById(R.id.show_network_text);
        textView.setText(mShow.network());

        textView = (TextView) view.findViewById(R.id.show_status_text);
        textView.setText(mShow.status());

        textView = (TextView) view.findViewById(R.id.show_episodes_text);
        textView.setText(String.valueOf(mShow.airedEpisodes()));

        textView = (TextView) view.findViewById(R.id.show_year_text);
        textView.setText(String.valueOf(mShow.year()));

        textView = (TextView) view.findViewById(R.id.show_country_text);
        textView.setText(mShow.country().toUpperCase());

        textView = (TextView) view.findViewById(R.id.show_language_text);
        textView.setText(mShow.language());

    }

}
