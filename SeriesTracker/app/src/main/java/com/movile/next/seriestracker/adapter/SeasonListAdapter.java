package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.listener.OnSeasonClickListener;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Season;

import java.util.List;

public class SeasonListAdapter extends RecyclerView.Adapter<SeasonListAdapter.ViewHolder> {

    private Context mContext;
    private List<Season> mSeasonList;
    private int mLayout;
    private OnSeasonClickListener mListener;

    public SeasonListAdapter(Context context, int layout, OnSeasonClickListener listener) {
        mContext = context;
        mLayout = layout;
        mListener = listener;
    }

    public void updateSeasons(List<Season> seasons) {
        mSeasonList = seasons;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Season season = mSeasonList.get(position);

        Glide.with(mContext)
                .load(season.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into(holder.seasonScreenshot());
        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.seasonClick(season);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeasonList != null ? mSeasonList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private ImageView mSeasonScreenshot;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mSeasonScreenshot = (ImageView) mRoot.findViewById(R.id.season_list_item_screenshot);
        }

        public View root() {
            return mRoot;
        }

        public ImageView seasonScreenshot() {
            return mSeasonScreenshot;
        }

    }

}
