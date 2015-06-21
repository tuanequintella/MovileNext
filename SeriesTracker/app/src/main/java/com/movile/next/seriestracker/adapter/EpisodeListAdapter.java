package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.listener.OnEpisodeClickListener;
import com.movile.next.seriestracker.model.Episode;

public class EpisodeListAdapter extends ArrayAdapter<Episode> {

    private OnEpisodeClickListener mListener;

    public EpisodeListAdapter(Context context, OnEpisodeClickListener listener) {
        super(context, R.layout.season_item_list, 0);

        mListener = listener;
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

        final Episode ep = getItem(position);

        if (ep != null)
        {
            holder.mEpisodeTitle.setText(ep.title());
            holder.mEpisodeNumber.setText(ep.number().toString());
            holder.mRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.episodeClick(ep);
                }
            });
        }

    }

    public static class ViewHolder {

        View mRoot;
        TextView mEpisodeNumber;
        TextView mEpisodeTitle;

        public ViewHolder(View root) {
            mRoot = root;
            mEpisodeNumber = (TextView) root.findViewById(R.id.episode_number_list);
            mEpisodeTitle = (TextView) root.findViewById(R.id.episode_title_list);
        }
    }
}
