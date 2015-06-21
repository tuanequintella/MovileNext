package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;

import java.util.ArrayList;

public class EpisodeListAdapter extends ArrayAdapter<Episode> {

    public EpisodeListAdapter(Context context, ArrayList<Episode> episodes) {
        super(context, R.layout.season_item_list, episodes);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            int resource = R.layout.season_item_list;

            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);

            holder = new ViewHolder(view);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, int position) {

        Episode ep = getItem(position);
        holder.mEpisodeTitle.setText(ep.title());
        holder.mEpisodeNumber.setText(ep.number().toString());

    }

    public static class ViewHolder {

        TextView mEpisodeNumber;
        TextView mEpisodeTitle;


        public ViewHolder(View root) {
            mEpisodeNumber = (TextView) root.findViewById(R.id.episode_number_list);
            mEpisodeTitle = (TextView) root.findViewById(R.id.episode_title_list);
        }

    }
}
